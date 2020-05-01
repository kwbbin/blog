package com.kwbbin.manage.controller;

import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.Tags;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.manage.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class SortController {

    @Autowired
    ArticleTypeService articleTypeService;

    @RequestMapping("/getAllArticleType")
    public List<ArticleType> getArticleTypeList(){
        return articleTypeService.getArticleTypeList();
    }

    @RequestMapping("/updateArticleType")
    public String updateArticleType(Integer sortId ,String title){
        ArticleType at = new ArticleType();
        at.setId(sortId);
        at.setName(title);
        articleTypeService.updateArticleType(at);
        return "redirect:/manage/sort";
    }



    @RequestMapping("/insertArticleType")
    public String insertArticleType(String title){
        ArticleType at = new ArticleType();
        at.setName(title);
        articleTypeService.insertArticleType(at);
        return "redirect:/manage/sort";
    }
}
