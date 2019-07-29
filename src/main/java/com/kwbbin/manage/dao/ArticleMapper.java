package com.kwbbin.manage.dao;

import com.kwbbin.bean.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper {
    int deleteById(Long id);

    int insert(Article record);

    Article selectById(Long id);

    int updateById(Article record);
}