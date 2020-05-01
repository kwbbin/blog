package com.kwbbin.manage.service;

import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;

import java.util.List;

public interface ArticleService {
    List<ArticleVo> selectAllArticle();
    Article getArticleById(Long id);
    void updateArticleById(Article article);
    void deleteArticleById(Long id);
}
