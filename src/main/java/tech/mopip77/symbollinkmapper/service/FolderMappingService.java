package tech.mopip77.symbollinkmapper.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.mopip77.symbollinkmapper.dto.FolderMappingConfigurationDTO;
import tech.mopip77.symbollinkmapper.enums.SymbolLinkType;
import tech.mopip77.symbollinkmapper.exception.CustomizeErrorCode;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;
import tech.mopip77.symbollinkmapper.mapper.FolderMappingConfigurationMapper;
import tech.mopip77.symbollinkmapper.mapper.SymbolLinkReferenceMapper;
import tech.mopip77.symbollinkmapper.model.FolderMappingConfiguration;
import tech.mopip77.symbollinkmapper.model.FolderMappingConfigurationExample;
import tech.mopip77.symbollinkmapper.model.SymbolLinkReference;
import tech.mopip77.symbollinkmapper.model.SymbolLinkReferenceExample;
import tech.mopip77.symbollinkmapper.utils.FileSystemUtils;
import tech.mopip77.symbollinkmapper.utils.LoggerUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderMappingService {

    private static Logger logger = LoggerUtils.getGlobalLogger();

    @Autowired
    private FolderMappingConfigurationMapper folderMappingConfigurationMapper;

    @Autowired
    private SymbolLinkReferenceMapper symbolLinkReferenceMapper;

    @Autowired
    private SymbolLinkService symbolLinkService;

    /**
     * ancestor check, avoid source folder is ancestor and dest folder is child, while using recursively symbol link
     * it results in endless loop
     *
     * @param sourcePath should be absolute path
     * @param destPath   should be absolute path
     * @return they have Ancestor relationship
     */
    private boolean ancestorCheck(String sourcePath, String destPath) {
        if (sourcePath.length() > destPath.length()) {
            return false;
        }

        sourcePath = sourcePath + "/";
        destPath = destPath + "/";
        return StringUtils.startsWith(destPath, sourcePath);
    }

    public void create(FolderMappingConfiguration configuration) {
        // range check
        if (!SymbolLinkType.isInRange(configuration.getSymbolLinkType())) {
            throw new CustomizeException(CustomizeErrorCode.REQUEST_PARAM_RANGE_ERROR);
        }

        // path validity check
        if (!FileSystemUtils.areAllFolder(configuration.getDestPath(), configuration.getSourcePath())) {
            throw new CustomizeException(CustomizeErrorCode.IS_NOT_A_FOLDER);
        }

        // use absolute path change previous path, now that there are all folders, toRealPath() will not throw exception
        try {
            configuration.setSourcePath(Paths.get(configuration.getSourcePath()).toRealPath().toString());
            configuration.setDestPath(Paths.get(configuration.getDestPath()).toRealPath().toString());
        } catch (IOException e) {
            throw new CustomizeException(CustomizeErrorCode.IS_NOT_ABSOLUTE_PATH);
        }

        // ancestor check
        if (ancestorCheck(configuration.getSourcePath(), configuration.getDestPath())) {
            throw new CustomizeException(CustomizeErrorCode.FOLDER_MAPPER_HAS_ANCESTOR_RELATIONSHIP);
        }

        // path uniqueness check
        FolderMappingConfigurationExample example = new FolderMappingConfigurationExample();
        example.createCriteria()
                .andSourcePathEqualTo(configuration.getSourcePath())
                .andDestPathEqualTo(configuration.getDestPath());
        List<FolderMappingConfiguration> results = folderMappingConfigurationMapper.selectByExample(example);
        if (results.size() > 0) {
            throw new CustomizeException(CustomizeErrorCode.HAS_SAME_ITEM);
        }

        folderMappingConfigurationMapper.insertSelective(configuration);
        logger.info("[添加文件夹映射] 原路径: " + configuration.getSourcePath() + " 映射路径: " + configuration.getDestPath());
    }

    public void update(FolderMappingConfiguration configuration) {
        // range check
        if (!SymbolLinkType.isInRange(configuration.getSymbolLinkType())) {
            throw new CustomizeException(CustomizeErrorCode.REQUEST_PARAM_RANGE_ERROR);
        }

        // path validity check
        if (!FileSystemUtils.areAllFolder(configuration.getDestPath(), configuration.getSourcePath())) {
            throw new CustomizeException(CustomizeErrorCode.IS_NOT_A_FOLDER);
        }

        // use absolute path change previous path, now that there are all folders, toRealPath() will not throw exception
        try {
            configuration.setSourcePath(Paths.get(configuration.getSourcePath()).toRealPath().toString());
            configuration.setDestPath(Paths.get(configuration.getDestPath()).toRealPath().toString());
        } catch (IOException e) {
            throw new CustomizeException(CustomizeErrorCode.IS_NOT_ABSOLUTE_PATH);
        }

        // ancestor check
        if (ancestorCheck(configuration.getSourcePath(), configuration.getDestPath())) {
            throw new CustomizeException(CustomizeErrorCode.FOLDER_MAPPER_HAS_ANCESTOR_RELATIONSHIP);
        }

        // path check
        FolderMappingConfigurationExample example = new FolderMappingConfigurationExample();
        example.createCriteria()
                .andSourcePathEqualTo(configuration.getSourcePath())
                .andDestPathEqualTo(configuration.getDestPath());
        List<FolderMappingConfiguration> results = folderMappingConfigurationMapper.selectByExample(example);
        if (results.size() != 1) {
            throw new CustomizeException(CustomizeErrorCode.HAS_NOT_THIS_ITEM);
        }

        configuration.setId(results.get(0).getId());
        folderMappingConfigurationMapper.updateByPrimaryKeySelective(configuration);
        logger.info("[更新文件夹映射] 原路径: " + configuration.getSourcePath() + " 映射路径: " + configuration.getDestPath());
    }

    public void delete(int id) {
        FolderMappingConfiguration configuration = folderMappingConfigurationMapper.selectByPrimaryKey(id);
        if (configuration != null) {
            int deleteCount = folderMappingConfigurationMapper.deleteByPrimaryKey(id);
            if (deleteCount == 0) {
                throw new CustomizeException(CustomizeErrorCode.HAS_NOT_THIS_ITEM);
            }
            logger.info("[删除文件夹映射] 原路径: " + configuration.getSourcePath() + " 映射路径: " + configuration.getDestPath());
        }
    }

    public List<FolderMappingConfigurationDTO> getAll() {
        List<FolderMappingConfiguration> folderMappingConfigurations = folderMappingConfigurationMapper.selectByExample(new FolderMappingConfigurationExample());
        List<FolderMappingConfigurationDTO> collects = folderMappingConfigurations.stream().map(configuration -> {
            FolderMappingConfigurationDTO dto = new FolderMappingConfigurationDTO();
            BeanUtils.copyProperties(configuration, dto);
            return dto;
        }).collect(Collectors.toList());
        return collects;
    }

    /**
     * Automatically remap sub-files between source path and dest path.
     * In case of file A in source path and it had already create a symbol link in dest path,
     * but we renamed symbol link A in dest path to symbol link B,
     * so we can not check if it is mapped by file name.
     * <p>
     * We will check all source path of sub-files in dest folder in DB. Check if they are in source folder.
     * Then create symbol links in dest folder for those have not created.
     *
     * @param configuration need to remap configuration
     */
    @Transactional
    public void autoReMapping(FolderMappingConfiguration configuration) {
        String destPath = configuration.getDestPath();
        String sourcePath = configuration.getSourcePath();

        // check if they have being deleted, if not delete
        if (!FileSystemUtils.areAllFolder(destPath, sourcePath)) {
            logger.info("[删除无效文件夹映射] 原路径: " + sourcePath + " 映射路径: " + destPath);
            folderMappingConfigurationMapper.deleteByPrimaryKey(configuration.getId());
            return;
        }

        File destFolder = new File(destPath);
        File sourceFolder = new File(sourcePath);
        File[] destFiles = destFolder.listFiles();
        File[] sourceFiles = sourceFolder.listFiles();
        List<String> mappedFilePaths = new ArrayList<>();

        if (sourceFiles == null || sourceFiles.length == 0)
            return;

        String sourceFolderPattern = sourcePath + "/";
        if (destFiles != null && destFiles.length != 0) {
            Arrays.stream(destFiles)
                    .forEach(destFile -> {
                        SymbolLinkReferenceExample example = new SymbolLinkReferenceExample();
                        example.createCriteria()
                                .andDestPathEqualTo(destFile.getAbsolutePath());
                        List<SymbolLinkReference> symbolLinkReferences = symbolLinkReferenceMapper.selectByExample(example);
                        // search by dest path, so the result at most 1 result
                        if (symbolLinkReferences.size() > 0 && StringUtils.startsWith(symbolLinkReferences.get(0).getSourcePath(), sourceFolderPattern)) {
                            mappedFilePaths.add(symbolLinkReferences.get(0).getSourcePath());
                        }
                    });
        }

        mappedFilePaths.sort(String::compareTo);
        String[] sortedMappedFilePaths = mappedFilePaths.toArray(new String[0]);
        Arrays.stream(sourceFiles)
                .filter(sourceFile -> Arrays.binarySearch(sortedMappedFilePaths, sourceFile.getAbsolutePath()) < 0)
                .forEach(sourceFile -> {
                    try {
                        symbolLinkService.createSymbolLink(sourceFile.getAbsolutePath(), destPath + "/" + sourceFile.getName(), configuration.getSymbolLinkType(), false);
                    } catch (Exception e) {
                        // if there has an exception, it will throw out, we should catch here, otherwise all the map will not execute
                        logger.error("[建立软连接失败] 原路径: " + sourceFile.getAbsolutePath() + " 映射路径: " + destPath + "/" + sourceFile.getName());
                    }
                });
    }
}
