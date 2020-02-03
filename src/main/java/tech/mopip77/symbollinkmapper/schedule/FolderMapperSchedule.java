package tech.mopip77.symbollinkmapper.schedule;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.mopip77.symbollinkmapper.mapper.FolderMappingConfigurationMapper;
import tech.mopip77.symbollinkmapper.model.FolderMappingConfiguration;
import tech.mopip77.symbollinkmapper.service.FolderMappingService;
import tech.mopip77.symbollinkmapper.utils.LoggerUtils;

import java.util.Date;
import java.util.List;

@Service
public class FolderMapperSchedule {

    private static final int MILLSECONDSPERHOUR = 3600 * 1000;

    private static Logger logger = LoggerUtils.getGlobalLogger();

    @Autowired
    FolderMappingConfigurationMapper folderMappingConfigurationMapper;

    @Autowired
    FolderMappingService folderMappingService;

    private boolean needReMap(FolderMappingConfiguration configuration, Long nowTimestamp) {
        if (!configuration.getAutoReMapping())
            return false;

        if (configuration.getGmtLastMapping() == null)
            return true;

        Long lastMappingTimeStamp = configuration.getGmtLastMapping().getTime();
        Integer period = configuration.getAutoReMappingPeriod() * MILLSECONDSPERHOUR;

        return nowTimestamp >= lastMappingTimeStamp + period;
    }

    /**
     * Exec per 5 minute, but the folder mappers only remap for hours, so it won't exec too frequent indeed.
     */
    @Scheduled(cron = "0 */5 * * * *")
    public void autoFolderMapping() {
        Date now = new Date();
        long nowTimestamp = now.getTime();

        List<FolderMappingConfiguration> folderMappingConfigurations = folderMappingConfigurationMapper.selectByExample(null);
        folderMappingConfigurations.stream()
                .filter(configuration -> needReMap(configuration, nowTimestamp))
                .forEach(configuration -> {
                    logger.info("[执行自动映射] 原路径: " + configuration.getSourcePath() + " 目标路径: " + configuration.getDestPath());
                    configuration.setGmtLastMapping(now);
                    folderMappingService.autoReMapping(configuration);
                    folderMappingConfigurationMapper.updateByPrimaryKeySelective(configuration);
                });
    }
}
