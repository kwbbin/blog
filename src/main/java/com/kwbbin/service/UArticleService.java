package com.kwbbin.service;

import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.Tags;

import java.util.List;

public interface UArticleService {
    PageInfo getArticleList();
    PageInfo<ArticleVo> getArticleListByLabelId(Integer id,Integer pageNum,Integer pageSize);
    PageInfo getNewArticleList();
    List<Tags> getAllTags();
    List<ArticleVo> randomArticles();
    List<ArticleType> selectAllArticleType();
    PageInfo<ArticleVo> selectArticleByType(Integer id,Integer pageNum,Integer pageSize);
    Article getArticleById(Long id);
    Tags getTagsById(Integer id);
}
