package com.kwbbin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleExample;
import com.kwbbin.dao.ArticleMapper;
import com.kwbbin.manage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UArticleServiceImpl implements UArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public PageInfo getArticleList() {
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("good Desc");
        PageHelper.startPage(1, 6);
        List<Article> articleList = articleMapper.selectByExampleWithBLOBs(articleExample);
        PageInfo pageInfo = new PageInfo(articleList);
        return pageInfo;
    }

    @Override
    public PageInfo getNewArticleList() {
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("posted_time Desc");
        PageHelper.startPage(1, 6);
        List<Article> articleList = articleMapper.selectByExampleWithBLOBs(articleExample);
        PageInfo pageInfo = new PageInfo(articleList);
        return pageInfo;
    }
}
