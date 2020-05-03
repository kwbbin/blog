package com.kwbbin.service;

import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.ArticleType;

import java.util.List;

public interface UArticleTypeService {
    ArticleType getArticleTypeById(Integer id);
    List<ArticleType> getAllArticleType();
}
