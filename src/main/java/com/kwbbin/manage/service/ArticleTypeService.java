package com.kwbbin.manage.service;

import com.kwbbin.bean.ArticleType;

import java.util.List;

public interface ArticleTypeService {
    List<ArticleType> getArticleTypeList ();
    void updateArticleType(ArticleType articleType);
    void insertArticleType(ArticleType articleType);
    Integer deleteArticleType(Integer id);
}
