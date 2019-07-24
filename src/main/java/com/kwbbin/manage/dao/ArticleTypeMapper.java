package com.kwbbin.manage.dao;

import com.kwbbin.bean.ArticleType;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);
}