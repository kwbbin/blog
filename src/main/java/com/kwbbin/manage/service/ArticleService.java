package com.kwbbin.manage.service;

import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.manage.dao.ArticleMapper;
import com.kwbbin.manage.dao.ArticleTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleTypeMapper dao;

    @Autowired
    ArticleMapper amdao;

    public List<ArticleType> selectAll(){
        return dao.selectAll();
    }

    public int insert(Article record){
        return amdao.insert(record);
    }

    public int deleteById(Long id){

        return amdao.deleteById(id);
    }

    public Article selectById(Long id){
        return amdao.selectById(id);
    };

    public int updateById(Article record){
        return amdao.updateById(record);
    };
}
