package com.kwbbin.controller;

import com.github.pagehelper.PageInfo;
import com.kwbbin.service.UArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserArticleController {

    @Autowired
    UArticleService articleService;

    @RequestMapping("/getHotArticle")
    @ResponseBody
    public PageInfo getHotArticleList(ModelAndView modelAndView){
        PageInfo pageInfo = articleService.getArticleList();
        return pageInfo;
    }

    @RequestMapping("/getNewArticleList")
    @ResponseBody
    public PageInfo getNewArticleList(ModelAndView modelAndView){
        PageInfo pageInfo = articleService.getNewArticleList();
        return pageInfo;
    }
}
