package com.kwbbin.service;

import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.Article;

import java.util.List;

public interface UArticleService {
    PageInfo getArticleList();
    PageInfo getNewArticleList();
}
