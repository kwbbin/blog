package com.kwbbin.dao;

import com.kwbbin.bean.Draft;
import com.kwbbin.bean.DraftExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DraftMapper {
    int countByExample(DraftExample example);

    int deleteByExample(DraftExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Draft record);

    int insertSelective(Draft record);

    List<Draft> selectByExampleWithBLOBs(DraftExample example);

    List<Draft> selectByExample(DraftExample example);

    Draft selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Draft record, @Param("example") DraftExample example);

    int updateByExampleWithBLOBs(@Param("record") Draft record, @Param("example") DraftExample example);

    int updateByExample(@Param("record") Draft record, @Param("example") DraftExample example);

    int updateByPrimaryKeySelective(Draft record);

    int updateByPrimaryKeyWithBLOBs(Draft record);

    int updateByPrimaryKey(Draft record);
}