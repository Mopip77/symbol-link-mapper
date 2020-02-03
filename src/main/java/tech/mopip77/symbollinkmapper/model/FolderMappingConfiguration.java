package tech.mopip77.symbollinkmapper.model;

import java.util.Date;

public class FolderMappingConfiguration {
    private Integer id;

    private String sourcePath;

    private String destPath;

    private String excludedRegx;

    private Boolean autoReMapping;

    private Integer autoReMappingPeriod;

    private Date gmtLastMapping;

    private Integer symbolLinkType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath == null ? null : sourcePath.trim();
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath == null ? null : destPath.trim();
    }

    public String getExcludedRegx() {
        return excludedRegx;
    }

    public void setExcludedRegx(String excludedRegx) {
        this.excludedRegx = excludedRegx == null ? null : excludedRegx.trim();
    }

    public Boolean getAutoReMapping() {
        return autoReMapping;
    }

    public void setAutoReMapping(Boolean autoReMapping) {
        this.autoReMapping = autoReMapping;
    }

    public Integer getAutoReMappingPeriod() {
        return autoReMappingPeriod;
    }

    public void setAutoReMappingPeriod(Integer autoReMappingPeriod) {
        this.autoReMappingPeriod = autoReMappingPeriod;
    }

    public Date getGmtLastMapping() {
        return gmtLastMapping;
    }

    public void setGmtLastMapping(Date gmtLastMapping) {
        this.gmtLastMapping = gmtLastMapping;
    }

    public Integer getSymbolLinkType() {
        return symbolLinkType;
    }

    public void setSymbolLinkType(Integer symbolLinkType) {
        this.symbolLinkType = symbolLinkType;
    }
}