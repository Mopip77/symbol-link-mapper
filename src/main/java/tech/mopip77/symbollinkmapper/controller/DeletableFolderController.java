package tech.mopip77.symbollinkmapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.mopip77.symbollinkmapper.dto.ResultDTO;
import tech.mopip77.symbollinkmapper.model.DeletableFolder;
import tech.mopip77.symbollinkmapper.service.DeletableFolderService;

import java.util.List;

@RestController
@RequestMapping("/deletable_folder")
public class DeletableFolderController {

    @Autowired
    DeletableFolderService deletableFolderService;

    @GetMapping
    public ResultDTO list() {
        List<DeletableFolder> deletableFolders = deletableFolderService.listDeletableFolder();
        return ResultDTO.okOf(deletableFolders);
    }

    @PostMapping
    public ResultDTO add(@RequestParam("path") String path) {
        deletableFolderService.add(path);
        return ResultDTO.okOf();
    }

    @DeleteMapping
    public ResultDTO delete(@RequestParam("path") String path) {
        deletableFolderService.delete(path);
        return ResultDTO.okOf();
    }
}
