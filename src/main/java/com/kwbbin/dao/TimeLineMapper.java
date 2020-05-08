package com.kwbbin.dao;

import com.kwbbin.bean.TimeLine;
import com.kwbbin.bean.TimeLineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TimeLineMapper {
    int countByExample(TimeLineExample example);

    int deleteByExample(TimeLineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TimeLine record);

    int insertSelective(TimeLine record);

    List<TimeLine> selectByExample(TimeLineExample example);

    TimeLine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TimeLine record, @Param("example") TimeLineExample example);

    int updateByExample(@Param("record") TimeLine record, @Param("example") TimeLineExample example);

    int updateByPrimaryKeySelective(TimeLine record);

    int updateByPrimaryKey(TimeLine record);
}