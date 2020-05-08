package com.kwbbin.dao;

import com.kwbbin.bean.MyMessage;
import com.kwbbin.bean.MyMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyMessageMapper {
    int countByExample(MyMessageExample example);

    int deleteByExample(MyMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MyMessage record);

    int insertSelective(MyMessage record);

    List<MyMessage> selectByExample(MyMessageExample example);

    MyMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MyMessage record, @Param("example") MyMessageExample example);

    int updateByExample(@Param("record") MyMessage record, @Param("example") MyMessageExample example);

    int updateByPrimaryKeySelective(MyMessage record);

    int updateByPrimaryKey(MyMessage record);
}