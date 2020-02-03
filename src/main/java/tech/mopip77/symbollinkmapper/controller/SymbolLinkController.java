package tech.mopip77.symbollinkmapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.mopip77.symbollinkmapper.dto.ResultDTO;
import tech.mopip77.symbollinkmapper.service.SymbolLinkService;
import tech.mopip77.symbollinkmapper.utils.SymbolLinkUtils;

@RestController
public class SymbolLinkController {

    @Autowired
    private SymbolLinkService symbolLinkService;

    @PostMapping("/symbol_link")
    public ResultDTO create(@RequestParam(value = "source_path") String sourcePath,
                            @RequestParam(value = "dest_path") String destPath,
                            @RequestParam(value = "symbol_link_type", required = false, defaultValue = "0") Integer symbolLinkType,
                            @RequestParam(value = "allow_over_write", required = false, defaultValue = "false") boolean allowOverWrite) {
        symbolLinkService.createSymbolLink(sourcePath, destPath, symbolLinkType, allowOverWrite);
        return ResultDTO.okOf();
    }
}
