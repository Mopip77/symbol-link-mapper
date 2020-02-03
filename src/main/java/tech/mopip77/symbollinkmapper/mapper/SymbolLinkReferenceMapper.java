package tech.mopip77.symbollinkmapper.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tech.mopip77.symbollinkmapper.model.SymbolLinkReference;
import tech.mopip77.symbollinkmapper.model.SymbolLinkReferenceExample;

public interface SymbolLinkReferenceMapper {
    long countByExample(SymbolLinkReferenceExample example);

    int deleteByExample(SymbolLinkReferenceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SymbolLinkReference record);

    int insertSelective(SymbolLinkReference record);

    List<SymbolLinkReference> selectByExample(SymbolLinkReferenceExample example);

    SymbolLinkReference selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SymbolLinkReference record, @Param("example") SymbolLinkReferenceExample example);

    int updateByExample(@Param("record") SymbolLinkReference record, @Param("example") SymbolLinkReferenceExample example);

    int updateByPrimaryKeySelective(SymbolLinkReference record);

    int updateByPrimaryKey(SymbolLinkReference record);
}