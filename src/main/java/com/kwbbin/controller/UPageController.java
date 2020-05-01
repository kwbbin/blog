package com.kwbbin.controller;

import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.Article;
import com.kwbbin.manage.service.ArticleService;
import com.kwbbin.service.UArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UPageController {

    @Autowired
    UArticleService uArticleService;

    @RequestMapping(value = {"","/"})
    public ModelAndView DefultView(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        PageInfo<Article> pageInfo = uArticleService.getArticleList();
        PageInfo<Article> newArticle = uArticleService.getArticleList();
        mv.addObject("hotArticle",pageInfo);
        mv.addObject("newArticle",newArticle);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/message")
    public ModelAndView MessageView(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("message");
        return mv;
    }

    @RequestMapping("/article/detail")
    public ModelAndView ArticleView(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("article-detail");
        return mv;
    }
}
