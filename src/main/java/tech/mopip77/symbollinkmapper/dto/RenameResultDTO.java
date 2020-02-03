package tech.mopip77.symbollinkmapper.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RenameResultDTO {
    private String regx;
    private Map<String, String> renameMap;
    private Integer exitCode;
    private String[] output;
}
