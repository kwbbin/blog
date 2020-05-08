package com.kwbbin.dao;

import com.kwbbin.bean.BlogBasic;
import com.kwbbin.bean.BlogBasicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogBasicMapper {
    int countByExample(BlogBasicExample example);

    int deleteByExample(BlogBasicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogBasic record);

    int insertSelective(BlogBasic record);

    List<BlogBasic> selectByExample(BlogBasicExample example);

    BlogBasic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogBasic record, @Param("example") BlogBasicExample example);

    int updateByExample(@Param("record") BlogBasic record, @Param("example") BlogBasicExample example);

    int updateByPrimaryKeySelective(BlogBasic record);

    int updateByPrimaryKey(BlogBasic record);
}