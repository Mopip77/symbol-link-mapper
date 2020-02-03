package tech.mopip77.symbollinkmapper.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
public class FolderMappingConfigurationDTO {
    private Integer id;

    private String sourcePath;

    private String destPath;

    private String excludedRegx;

    private Boolean autoReMapping;

    private Integer autoReMappingPeriod;

    private Integer symbolLinkType;

    private String gmtLastMapping;

}
