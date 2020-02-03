package tech.mopip77.symbollinkmapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.mopip77.symbollinkmapper.dto.FolderMappingConfigurationDTO;
import tech.mopip77.symbollinkmapper.dto.ResultDTO;
import tech.mopip77.symbollinkmapper.model.FolderMappingConfiguration;
import tech.mopip77.symbollinkmapper.service.FolderMappingService;

import java.util.List;

/**
 * 修改文件夹的controller，可修改下载文件夹目录，共享文件夹目录，以及其映射关系，和每个映射关系下的排除选项
 */
@RestController
@RequestMapping("/folder_mapping")
public class FolderMappingController {

    @Autowired
    private FolderMappingService folderMappingService;

    @PostMapping
    public ResultDTO create(@RequestBody FolderMappingConfiguration configuration) {
        folderMappingService.create(configuration);
        return ResultDTO.okOf();
    }

    @PutMapping
    public ResultDTO update(@RequestBody FolderMappingConfiguration configuration) {
        folderMappingService.update(configuration);
        return ResultDTO.okOf();
    }

    @DeleteMapping
    public ResultDTO delete(@RequestParam("id") int id) {
        folderMappingService.delete(id);
        return ResultDTO.okOf();
    }

    @GetMapping
    public ResultDTO<List<FolderMappingConfigurationDTO>> get() {
        List<FolderMappingConfigurationDTO>  allFSC = folderMappingService.getAll();
        return ResultDTO.okOf(allFSC);
    }
}
