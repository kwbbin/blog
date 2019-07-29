package com.kwbbin.manage.dao;

import com.kwbbin.bean.ArticleType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTypeMapper {
    public List<ArticleType> selectAll();
}