package tech.mopip77.symbollinkmapper.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mopip77.symbollinkmapper.exception.CustomizeErrorCode;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;
import tech.mopip77.symbollinkmapper.mapper.DeletableFolderMapper;
import tech.mopip77.symbollinkmapper.model.DeletableFolder;
import tech.mopip77.symbollinkmapper.model.DeletableFolderExample;
import tech.mopip77.symbollinkmapper.utils.LoggerUtils;

import java.io.File;
import java.util.List;

@Service
public class DeletableFolderService {

    private static Logger logger = LoggerUtils.getGlobalLogger();

    @Autowired
    DeletableFolderMapper deletableFolderMapper;

    public List<DeletableFolder> listDeletableFolder() {
        List<DeletableFolder> deletableFolders = deletableFolderMapper.selectByExample(new DeletableFolderExample());
        return deletableFolders;
    }

    public void add(String path) {
        File deletableFolder = new File(path);
        if (!deletableFolder.isDirectory()) {
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_IS_NOT_FOLDER);
        }

        String absolutePath = deletableFolder.toPath().toAbsolutePath().toString();
        DeletableFolderExample example = new DeletableFolderExample();
        example.createCriteria()
                .andPathEqualTo(absolutePath);
        List<DeletableFolder> deletableFolders = deletableFolderMapper.selectByExample(example);
        if (deletableFolders.size() == 0) {
            DeletableFolder insertDeletableFolder = new DeletableFolder();
            insertDeletableFolder.setPath(absolutePath);
            deletableFolderMapper.insertSelective(insertDeletableFolder);
            logger.info("[添加可删除文件夹] " + absolutePath);
        }
    }

    public void delete(String path) {
        DeletableFolderExample example = new DeletableFolderExample();
        example.createCriteria()
                .andPathEqualTo(path);
        int deleteCount = deletableFolderMapper.deleteByExample(example);
        if (deleteCount == 0) {
            logger.info("[删除可删除文件夹 FAIL] " + path);
            throw new CustomizeException(CustomizeErrorCode.SOURCE_PATH_NOT_EXIST);
        }
        logger.info("[删除可删除文件夹] " + path);
    }
}
