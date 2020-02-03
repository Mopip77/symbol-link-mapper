package tech.mopip77.symbollinkmapper.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tech.mopip77.symbollinkmapper.model.FolderMappingConfiguration;
import tech.mopip77.symbollinkmapper.model.FolderMappingConfigurationExample;

public interface FolderMappingConfigurationMapper {
    long countByExample(FolderMappingConfigurationExample example);

    int deleteByExample(FolderMappingConfigurationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FolderMappingConfiguration record);

    int insertSelective(FolderMappingConfiguration record);

    List<FolderMappingConfiguration> selectByExample(FolderMappingConfigurationExample example);

    FolderMappingConfiguration selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FolderMappingConfiguration record, @Param("example") FolderMappingConfigurationExample example);

    int updateByExample(@Param("record") FolderMappingConfiguration record, @Param("example") FolderMappingConfigurationExample example);

    int updateByPrimaryKeySelective(FolderMappingConfiguration record);

    int updateByPrimaryKey(FolderMappingConfiguration record);
}