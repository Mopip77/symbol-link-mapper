package tech.mopip77.symbollinkmapper.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mopip77.symbollinkmapper.enums.SymbolLinkType;
import tech.mopip77.symbollinkmapper.exception.CustomizeErrorCode;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;
import tech.mopip77.symbollinkmapper.mapper.SymbolLinkReferenceMapper;
import tech.mopip77.symbollinkmapper.model.FolderMappingConfiguration;
import tech.mopip77.symbollinkmapper.model.SymbolLinkReference;
import tech.mopip77.symbollinkmapper.model.SymbolLinkReferenceExample;
import tech.mopip77.symbollinkmapper.utils.FileSystemUtils;
import tech.mopip77.symbollinkmapper.utils.LoggerUtils;
import tech.mopip77.symbollinkmapper.utils.SymbolLinkUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class SymbolLinkService {

    private static Logger logger = LoggerUtils.getGlobalLogger();

    @Autowired
    private SymbolLinkReferenceMapper symbolLinkReferenceMapper;

    private void universalCheckForCreatingSymbolLink(String sourcePathUri, String destPathUri, int symbolLinkType) {
        if (!SymbolLinkType.isInRange(symbolLinkType)) {
            throw new CustomizeException(CustomizeErrorCode.REQUEST_PARAM_RANGE_ERROR);
        }

        if (!FileSystemUtils.areAbsolutePaths(sourcePathUri, destPathUri)) {
            throw new CustomizeException(CustomizeErrorCode.IS_NOT_ABSOLUTE_PATH);
        }

        File sourceFile = new File(sourcePathUri);
        if (!sourceFile.exists()) {
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_NOT_EXIST);
        }
    }

    private void createDirectSymbolLink(Path sourcePath, Path destPath) {
        logger.info("[新建直接映射] 原路径: " + sourcePath.toString() + " 目标路径: " + destPath.toString());
        // if fail to create, the function will throw exception
        SymbolLinkUtils.createDirectSymbolLink(sourcePath, destPath);

        SymbolLinkReference ref = new SymbolLinkReference();
        ref.setSourcePath(sourcePath.toString());
        ref.setDestPath(destPath.toString());
        ref.setType(SymbolLinkType.DirectLink.getNumber());
        ref.setIsFolder(sourcePath.toFile().isDirectory());
        symbolLinkReferenceMapper.insertSelective(ref);
    }

    /**
     * Create recursively symbol link tree, means create real folders but all files will be created as symbol link.
     * Besides the destPath is the symbol link path, so it has the file (folder) name of source path, it is not the parent path
     *
     * @param sourcePath folder or file (should exists)
     * @param destPath   non exists path (even parent path)
     */
    private void createSymbolLinkRecursively(Path sourcePath, Path destPath) {
        logger.info("[新建递归映射] 原路径: " + sourcePath.toString() + " 目标路径: " + destPath.toString());
        SymbolLinkReference ref = new SymbolLinkReference();
        ref.setSourcePath(sourcePath.toString());
        ref.setDestPath(destPath.toString());
        ref.setIsFolder(sourcePath.toFile().isDirectory());

        if (!sourcePath.toFile().isDirectory()) {
            // if fail to create, the function will throw exception
            SymbolLinkUtils.createDirectSymbolLink(sourcePath, destPath);
            ref.setType(SymbolLinkType.DirectLink.getNumber());
            symbolLinkReferenceMapper.insertSelective(ref);
            return;
        }

        // the source path is folder
        ref.setType(SymbolLinkType.RecursiveLink.getNumber());
        boolean created = destPath.toFile().mkdirs();
        if (!created) {
            // if dest folder can not be created, the sub folders will not be created and mapped
            return;
        }
        symbolLinkReferenceMapper.insertSelective(ref);

        File[] filesInSourceFolder = sourcePath.toFile().listFiles();
        if (filesInSourceFolder == null || filesInSourceFolder.length == 0)
            return;
        Arrays.sort(filesInSourceFolder, FileSystemUtils.comparator());
        String sourcePathUri = sourcePath.toString();
        String destPathUri = destPath.toString();
        Arrays.stream(filesInSourceFolder).map(File::getName).forEach(fileName -> {
            createSymbolLinkRecursively(Paths.get(sourcePathUri, fileName), Paths.get(destPathUri, fileName));
        });
    }

    /**
     * delete path and all sub path as source path and dest path
     * @param deletingPath should NOT end with '/'
     */
    public void deleteAllByPathInDB(String deletingPath) {
        SymbolLinkReferenceExample example = new SymbolLinkReferenceExample();
        example.createCriteria()
                .andDestPathEqualTo(deletingPath);
        example.or()
                .andSourcePathEqualTo(deletingPath);
        example.or()
                .andDestPathLike(deletingPath + "/%");
        example.or()
                .andSourcePathLike(deletingPath + "/%");
        symbolLinkReferenceMapper.deleteByExample(example);
    }

    /**
     * Create symbol link by symbol type.
     * If the source path is symbol link, trans it to original path first.
     * Besides, the source path can not appear in db as dest path, it may raise circle problem, which refers to the
     * the symbol link folder create with recursive type.
     *
     * @param sourcePathUri absolute
     * @param destPathUri absolute
     * @param symbolLinkType
     * @param allowOverWrite
     */
    public void createSymbolLink(String sourcePathUri, String destPathUri, int symbolLinkType, boolean allowOverWrite) {
        universalCheckForCreatingSymbolLink(sourcePathUri, destPathUri, symbolLinkType);

        // convert to absolute path
        Path sourcePath = Paths.get(sourcePathUri);
        Path destPath = Paths.get(destPathUri);

        try {
            // this will trace to origin file if the path is symbol link
            sourcePath = sourcePath.toRealPath();
        } catch (IOException e) {
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_HAS_NO_ORIGIN_PATH);
        }
        destPath = destPath.toAbsolutePath();

        // dest path check
        // is a folder -> append the file name
        // is not a folder -> if allow over write -> delete and recreate
        if (destPath.toFile().isDirectory()) {
            // because of the "Files.createSymbolicLink" function needs a file name even dest path is a folder
            // so we need to add the file name after the folder
            Path fileName = sourcePath.getFileName();
            destPath = Paths.get(destPath.toString(), fileName.toString());
        }

        // check if source path equals to dest path after dest path file name accomplishment
        if (sourcePath.toString().equals(destPath.toString()))
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_EQUALS_TO_DEST_PATH);

        // check if source path appears in db as dest path
        SymbolLinkReferenceExample example = new SymbolLinkReferenceExample();
        example.createCriteria()
                .andDestPathEqualTo(sourcePath.toString());
        List<SymbolLinkReference> symbolLinkReferences = symbolLinkReferenceMapper.selectByExample(example);
        if (symbolLinkReferences.size() != 0)
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_CANNOT_BE_RECURSIVE_FOLDER);

        if (destPath.toFile().exists()) {
            if (allowOverWrite) {
                boolean isFolder = destPath.toFile().isDirectory();
                // recursively delete on FS
                boolean deleted = FileSystemUtils.deleteRecursively(destPath.toFile());
                if (!deleted) {
                    throw new CustomizeException(CustomizeErrorCode.DEST_FILE_DELETE_FAIL);
                }
            } else {
                throw new CustomizeException(CustomizeErrorCode.DEST_PATH_EXIST);
            }
        }

        // avoiding the dest file or folder was deleted by purpose, delete db first
        deleteAllByPathInDB(destPath.toString());

        SymbolLinkType type = SymbolLinkType.getByNumber(symbolLinkType);
        if (type == SymbolLinkType.DirectLink)
            createDirectSymbolLink(sourcePath, destPath);
        else if (type == SymbolLinkType.RecursiveLink)
            createSymbolLinkRecursively(sourcePath, destPath);
    }
}
