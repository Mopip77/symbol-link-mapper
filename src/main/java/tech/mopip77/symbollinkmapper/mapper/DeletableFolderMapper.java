package tech.mopip77.symbollinkmapper.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tech.mopip77.symbollinkmapper.model.DeletableFolder;
import tech.mopip77.symbollinkmapper.model.DeletableFolderExample;

public interface DeletableFolderMapper {
    long countByExample(DeletableFolderExample example);

    int deleteByExample(DeletableFolderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeletableFolder record);

    int insertSelective(DeletableFolder record);

    List<DeletableFolder> selectByExample(DeletableFolderExample example);

    DeletableFolder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeletableFolder record, @Param("example") DeletableFolderExample example);

    int updateByExample(@Param("record") DeletableFolder record, @Param("example") DeletableFolderExample example);

    int updateByPrimaryKeySelective(DeletableFolder record);

    int updateByPrimaryKey(DeletableFolder record);
}