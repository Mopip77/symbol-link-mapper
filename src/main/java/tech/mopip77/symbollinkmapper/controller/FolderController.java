package tech.mopip77.symbollinkmapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.mopip77.symbollinkmapper.dto.FolderDTO;
import tech.mopip77.symbollinkmapper.dto.RenameResultDTO;
import tech.mopip77.symbollinkmapper.dto.ResultDTO;
import tech.mopip77.symbollinkmapper.exception.CustomizeErrorCode;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;
import tech.mopip77.symbollinkmapper.exception.SupplimentErrorCode;
import tech.mopip77.symbollinkmapper.model.DeletableFolder;
import tech.mopip77.symbollinkmapper.service.FolderService;
import tech.mopip77.symbollinkmapper.service.SymbolLinkService;

import javax.xml.transform.Result;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * offer api which belongs to folder operation, e.g. list files, delete file, rename file...
 */
@RestController
@RequestMapping("/folder")
public class FolderController {

    @Autowired
    FolderService folderService;

    @Autowired
    SymbolLinkService symbolLinkService;

    /**
     * find the symbol link references by path
     *
     * @param folderPath
     * @param pathType   0 -> source path, 1 -> dest path
     * @return
     */
    @GetMapping("specific")
    public ResultDTO<FolderDTO> list(@RequestParam(value = "path") String folderPath,
                                     @RequestParam(value = "type") Integer pathType) {
        FolderDTO folderDTO = folderService.getByPath(folderPath, pathType);
        return ResultDTO.okOf(folderDTO);
    }

    /**
     * get the folder content with symbol link
     *
     * @param folderPath must be a folder path otherwise throw exception
     * @param pathType   0 -> source path, 1 -> linked path
     * @return
     */
    @GetMapping
    public ResultDTO<List<FolderDTO>> listFolderContent(@RequestParam(value = "path") String folderPath,
                                                        @RequestParam(value = "type") Integer pathType) {
        List<FolderDTO> folderDTOS = folderService.listFolderContent(folderPath, pathType);
        return ResultDTO.okOf(folderDTOS);
    }

    @DeleteMapping
    public ResultDTO deleteRecursively(@RequestBody Map<String, String[]> map) {
        String[] deletingPaths = map.get("paths");
        Map<String, List<String>> failedDeletedPath = folderService.deleteRecursively(deletingPaths);
        return ResultDTO.okOf(failedDeletedPath);
    }

    /**
     * Rename file.
     * Only allow to rename path which is stored in db as dest path.
     * In order to avoid renaming the file under direct symbol link, which will rename source file.
     * e.g.
     * a          ===>       c -> a
     * |_1.txt               |_1.txt
     * <p>
     * We have a direct symbol link from c to a. If we rename c/1.txt which just means renaming a/1.txt.
     * It takes risk for pt download.
     *
     * @param path
     * @param newName
     * @return
     */
    @PutMapping("rename/specific")
    public ResultDTO rename(@RequestParam("path") String path,
                            @RequestParam("new_name") String newName) {
        folderService.rename(path, newName);
        return ResultDTO.okOf();
    }

    /**
     * the rename map is generated by `renameByRegxDryly`, for convenience I re-commit here with no secure check, this is very unsafe
     *
     * @param renameMap old absolute path - new absolute path
     * @return
     */
    @PutMapping("rename")
    public ResultDTO rename(@RequestBody Map<String, String> renameMap) {
        folderService.rename(renameMap);
        return ResultDTO.okOf();
    }

    @PutMapping("rename_dry")
    public ResultDTO renameByRegxDryly(@RequestParam("paths") String[] paths,
                                       @RequestParam("regx") String regx) {
        RenameResultDTO renameResultDTO = folderService.renameByRegxDryly(paths, regx);
        return ResultDTO.okOf(renameResultDTO);
    }
}
