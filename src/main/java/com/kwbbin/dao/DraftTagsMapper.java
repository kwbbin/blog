package com.kwbbin.dao;

import com.kwbbin.bean.DraftTagsExample;
import com.kwbbin.bean.DraftTagsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DraftTagsMapper {
    int countByExample(DraftTagsExample example);

    int deleteByExample(DraftTagsExample example);

    int deleteByPrimaryKey(DraftTagsKey key);

    int insert(DraftTagsKey record);

    int insertSelective(DraftTagsKey record);

    List<DraftTagsKey> selectByExample(DraftTagsExample example);

    int updateByExampleSelective(@Param("record") DraftTagsKey record, @Param("example") DraftTagsExample example);

    int updateByExample(@Param("record") DraftTagsKey record, @Param("example") DraftTagsExample example);
}