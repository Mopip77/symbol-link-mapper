package tech.mopip77.symbollinkmapper.dto;

import lombok.Data;
import tech.mopip77.symbollinkmapper.enums.SymbolLinkType;

@Data
public class FolderDTO {
    private String sourcePath;
    private Boolean isFolder;
    private String[] destPath;
    private SymbolLinkType[] symbolLinkType;
}
