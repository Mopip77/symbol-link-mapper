package tech.mopip77.symbollinkmapper.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.mopip77.symbollinkmapper.dto.FolderDTO;
import tech.mopip77.symbollinkmapper.dto.RenameResultDTO;
import tech.mopip77.symbollinkmapper.enums.SymbolLinkType;
import tech.mopip77.symbollinkmapper.exception.CustomizeErrorCode;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;
import tech.mopip77.symbollinkmapper.mapper.DeletableFolderMapper;
import tech.mopip77.symbollinkmapper.mapper.SymbolLinkReferenceMapper;
import tech.mopip77.symbollinkmapper.model.DeletableFolder;
import tech.mopip77.symbollinkmapper.model.DeletableFolderExample;
import tech.mopip77.symbollinkmapper.model.SymbolLinkReference;
import tech.mopip77.symbollinkmapper.model.SymbolLinkReferenceExample;
import tech.mopip77.symbollinkmapper.utils.FileSystemUtils;
import tech.mopip77.symbollinkmapper.utils.LoggerUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * to a common format, file and folder will be stored in db without the last slash
 */
@Service
public class FolderService {

    private static Logger logger = LoggerUtils.getGlobalLogger();

    @Autowired
    private DeletableFolderMapper deletableFolderMapper;

    @Autowired
    private SymbolLinkReferenceMapper symbolLinkReferenceMapper;

    @Autowired
    private SymbolLinkService symbolLinkService;

    /**
     * input source file, get the symbol link path linked to the source file map
     *
     * @param sourceFile
     * @return
     */
    private FolderDTO getBySourcePath(File sourceFile) {
        SymbolLinkReferenceExample example = new SymbolLinkReferenceExample();
        example.createCriteria()
                .andSourcePathEqualTo(sourceFile.getAbsolutePath());

        List<SymbolLinkReference> symbolLinkReferences = symbolLinkReferenceMapper.selectByExample(example);
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setSourcePath(sourceFile.getAbsolutePath());
        folderDTO.setIsFolder(sourceFile.isDirectory());
        if (symbolLinkReferences.size() != 0) {
            // dest path array
            List<String> collect = symbolLinkReferences.stream().map(SymbolLinkReference::getDestPath).collect(Collectors.toList());
            String[] destPathArray = new String[collect.size()];
            collect.toArray(destPathArray);
            folderDTO.setDestPath(destPathArray);

            // symbol link type array
            List<SymbolLinkType> collect1 = symbolLinkReferences.stream()
                    .map(reference -> SymbolLinkType.getByNumber(reference.getType())).collect(Collectors.toList());
            SymbolLinkType[] symbolLinkTypes = new SymbolLinkType[collect1.size()];
            collect1.toArray(symbolLinkTypes);
            folderDTO.setSymbolLinkType(symbolLinkTypes);
        }
        return folderDTO;
    }

    /**
     * input source files, get the symbol link path linked to the source file map list
     *
     * @param sourceFiles should be absolute path
     * @return
     */
    private List<FolderDTO> getBySourcePath(File[] sourceFiles) {
        return Arrays.stream(sourceFiles).map(this::getBySourcePath).collect(Collectors.toList());
    }

    /**
     * input dest file (symbol link), get the source file
     *
     * @param destFile
     * @return
     */
    private FolderDTO getByDestPath(File destFile) {
        SymbolLinkReferenceExample example = new SymbolLinkReferenceExample();
        example.createCriteria()
                .andDestPathEqualTo(destFile.getAbsolutePath());

        List<SymbolLinkReference> symbolLinkReferences = symbolLinkReferenceMapper.selectByExample(example);
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setDestPath(new String[]{destFile.getAbsolutePath()});
        folderDTO.setIsFolder(destFile.isDirectory());
        if (symbolLinkReferences.size() != 0) {
            folderDTO.setSourcePath(symbolLinkReferences.get(0).getSourcePath());
            folderDTO.setSymbolLinkType(new SymbolLinkType[]{SymbolLinkType.getByNumber(symbolLinkReferences.get(0).getType())});
        }
        return folderDTO;
    }

    /**
     * input dest files (symbol link), get the source file for each dest file
     *
     * @param destFiles
     * @return
     */
    private List<FolderDTO> getByDestPath(File[] destFiles) {
        return Arrays.stream(destFiles).map(this::getByDestPath).collect(Collectors.toList());
    }

    /**
     * Get symbol link reference by path.
     *
     * @param path     Because this will invoke filesystem, for convenience this must exists
     * @param pathType 0 -> source path, 1 -> dest path
     * @return
     */
    public FolderDTO getByPath(String path, Integer pathType) {
        if (!(pathType == 0 || pathType == 1)) {
            throw new CustomizeException(CustomizeErrorCode.REQUEST_PARAM_RANGE_ERROR);
        }

        File file = new File(path);
        if (!file.exists())
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_NOT_EXIST);

        if (pathType == 0)
            return getBySourcePath(file);
        else
            return getByDestPath(file);
    }

    /**
     * Get symbol link reference in the folder by path
     *
     * @param folderPath must be exist folder
     * @param pathType   0 -> source path, 1 -> dest path
     * @return
     */
    public List<FolderDTO> listFolderContent(String folderPath, Integer pathType) {
        if (!(pathType == 0 || pathType == 1)) {
            throw new CustomizeException(CustomizeErrorCode.REQUEST_PARAM_RANGE_ERROR);
        }

        File curFolder = new File(folderPath);
        if (!curFolder.exists()) {
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_NOT_EXIST);
        }

        if (!curFolder.isDirectory()) {
            throw new CustomizeException(CustomizeErrorCode.IS_NOT_A_FOLDER);
        }

        File[] filesInCurFolder = curFolder.listFiles();
        if (filesInCurFolder == null) {
            // have no permission
            throw new CustomizeException(CustomizeErrorCode.HAVE_NO_PERMISSION_IN_FOLDER);
        }

        Arrays.sort(filesInCurFolder, FileSystemUtils.comparator());

        if (pathType == 0)
            return getBySourcePath(filesInCurFolder);
        else
            return getByDestPath(filesInCurFolder);
    }

    /**
     * recursively delete, FS -> DB
     *
     * @param deletingPaths should exist in deletable_folder table itself or as child path, and should not end with /
     * @return return folder paths which can not be deleted
     */
    @Transactional
    public Map<String, List<String>> deleteRecursively(String[] deletingPaths) {
        logger.info("[删除文件夹] " + LoggerUtils.joinArray(deletingPaths, ","));
        if (deletingPaths == null || deletingPaths.length == 0) {
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_NOT_EXIST);
        }

        List<DeletableFolder> deletableFolders = deletableFolderMapper.selectByExample(new DeletableFolderExample());
        // should concat with '/' otherwise it will be allowed like /a -> /ab/
        boolean exists = Arrays.stream(deletingPaths).allMatch(deletingPath ->
                deletableFolders.stream()
                        .anyMatch(folder -> folder.getPath().equals(deletingPath) || StringUtils.startsWith(deletingPath, folder.getPath() + "/"))
        );

        if (!exists) {
            throw new CustomizeException(CustomizeErrorCode.DELETE_PATH_NOT_EXIST_IN_DELETABLE_PATH);
        }

        // it may input parent-child folders, so even though the path doesn't exist, pass it.
        CopyOnWriteArrayList<String> failDeletedPath = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<String> deletedPath = new CopyOnWriteArrayList<>();
        Arrays.stream(deletingPaths)
                .forEach(deletingPath -> {
                    File deletingFile = new File(deletingPath);
                    if (!FileSystemUtils.deleteRecursively(deletingFile)) {
                        failDeletedPath.add(deletingPath);
                    } else {
                        deletedPath.add(deletingPath);
                    }
                    symbolLinkService.deleteAllByPathInDB(deletingFile.getAbsolutePath());
                });


        if (failDeletedPath.size() > 0)
            logger.info("[删除文件夹失败] " + LoggerUtils.joinArray(failDeletedPath.toArray(), ","));

        Map<String, List<String>> result = new HashMap<>();
        result.put("fail", failDeletedPath);
        result.put("success", deletedPath);
        return result;
    }

    /**
     * Only allow to rename path which is stored in db as dest path, and new file must be in the same folder as old file.
     * For direct symbol link, it will only exists as dest path in db.
     * (even used as source, source path will trace to original path)
     * <p>
     * For recursive symbol link folder, it can appear as source and dest. But it is not common as source path,
     * so for sake of performance, update after nonempty selection.
     *
     * @param path    should exist and be absolute path
     * @param newName just file name
     */
    @Transactional
    public void rename(String path, String newName) {
        if (!FileSystemUtils.permittedFileName(newName))
            throw new CustomizeException(CustomizeErrorCode.FILE_NAME_NOT_PERMITTED);

        File file = new File(path);
        if (!file.exists() || !FileSystemUtils.isAbsolutePath(path))
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_NOT_EXIST);

        if (file.getName().equals(newName))
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_EQUALS_TO_DEST_PATH);

        SymbolLinkReferenceExample example = new SymbolLinkReferenceExample();
        example.createCriteria()
                .andDestPathEqualTo(file.getAbsolutePath());
        List<SymbolLinkReference> symbolLinkReferences = symbolLinkReferenceMapper.selectByExample(example);

        // if db contains there only be one result
        if (symbolLinkReferences.size() == 0)
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_NOT_EXIST);

        Path newPath = file.toPath().resolveSibling(newName);
        boolean renamed = file.renameTo(newPath.toFile());
        if (!renamed)
            throw new CustomizeException(CustomizeErrorCode.HAVE_NO_PERMISSION_IN_FOLDER);

        // update the dest path itself
        SymbolLinkReference reference = symbolLinkReferences.get(0);
        reference.setDestPath(newPath.toString());
        symbolLinkReferenceMapper.updateByPrimaryKeySelective(reference);

        if (SymbolLinkType.getByNumber(reference.getType()) == SymbolLinkType.RecursiveLink) {
            // should rename this mapper reference and sub mapper references as dest and source

            // dest sub path
            example.clear();
            example.createCriteria()
                    .andDestPathLike(file.getAbsolutePath() + "/%");
            List<SymbolLinkReference> subSymbolLinkReferences = symbolLinkReferenceMapper.selectByExample(example);
            subSymbolLinkReferences.forEach(ref -> {
                String destPath = ref.getDestPath();
                destPath = StringUtils.replaceOnce(destPath, file.getAbsolutePath(), newPath.toString());
                ref.setDestPath(destPath);
                symbolLinkReferenceMapper.updateByPrimaryKeySelective(ref);
            });
        }
        logger.info("[重命名] 原路径: " + path + " 新名称: " + newPath);
    }

    /**
     * Rename by rename map generated by `rename` command, but the dest file is absolute path, so we must extract file name.
     *
     * @param renameMap this will input by user, for convenience, i don't check it security
     */
    public void rename(Map<String, String> renameMap) {
        renameMap.forEach((key, value) -> {
            String[] splits = StringUtils.split(value, "/");
            String newName = splits[splits.length - 1];
            this.rename(key, newName);
        });
    }

    public RenameResultDTO renameByRegxDryly(String[] paths, String regx) {
        return FileSystemUtils.renameByRegxDryly(paths, regx);
    }
}
