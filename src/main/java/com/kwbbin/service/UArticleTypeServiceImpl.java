package com.kwbbin.service;

import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.ArticleTypeExample;
import com.kwbbin.dao.ArticleMapper;
import com.kwbbin.dao.ArticleTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UArticleTypeServiceImpl implements  UArticleTypeService {
    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public ArticleType getArticleTypeById(Integer id) {
        return articleTypeMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<ArticleType> getAllArticleType() {
        return articleTypeMapper.selectByExample(new ArticleTypeExample());
    }
}
