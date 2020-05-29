package com.kwbbin.dao;

import com.kwbbin.bean.LocalImage;
import com.kwbbin.bean.LocalImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LocalImageMapper {
    int countByExample(LocalImageExample example);

    int deleteByExample(LocalImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LocalImage record);

    int insertSelective(LocalImage record);

    List<LocalImage> selectByExample(LocalImageExample example);

    LocalImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LocalImage record, @Param("example") LocalImageExample example);

    int updateByExample(@Param("record") LocalImage record, @Param("example") LocalImageExample example);

    int updateByPrimaryKeySelective(LocalImage record);

    int updateByPrimaryKey(LocalImage record);
}