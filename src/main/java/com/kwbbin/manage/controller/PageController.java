package com.kwbbin.manage.controller;

import com.kwbbin.manage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class PageController {

    @Autowired
    ArticleService service;

    @RequestMapping("/article")
    public String articleHtml(){
        return "manage/article";
    }

    @RequestMapping("/AddArticle")
    public String articleAddHtml(Model model){
        model.addAttribute("articleType",service.selectAll());
        return "manage/add-article";
    }
}
