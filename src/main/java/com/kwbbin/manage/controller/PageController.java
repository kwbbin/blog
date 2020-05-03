package com.kwbbin.manage.controller;

import com.kwbbin.bean.Tags;
import com.kwbbin.manage.service.ArticleServiceImpl;
import com.kwbbin.manage.service.ArticleTypeService;
import com.kwbbin.manage.service.DraftService;
import com.kwbbin.manage.service.TagsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class PageController {

    @Autowired
    ArticleServiceImpl service;

    @Autowired
    DraftService draftService;

    @Autowired
    ArticleTypeService articleTypeService;

    @Autowired
    TagsService tagsService;

    @RequestMapping("/draft")
    public String articleHtml(Model model){
        model.addAttribute("draftList",draftService.selectAllDraft());
        return "/manage/draft";
    }

    @RequestMapping("/searchAllArticle")
    public String selectAllArticleHtml(Model model){
        model.addAttribute("articleList",service.selectAllArticle(1,6));
        return "/manage/search-article";
    }

    @RequestMapping("/guessLike")
    public String guessLikeHtml(Model model){
        model.addAttribute("articleList",service.getAllGuessLike());
        return "/manage/guess-like";
    }

    @RequestMapping("/statistics")
    public String testmain(){
        return "/manage/statistics";
    }

    @RequestMapping("/sort")
    public String sortHtml(Model model){
        model.addAttribute("articleTypeList",articleTypeService.getArticleTypeList());
        return "/manage/sort";
    }

    @RequestMapping("/tags")
    public String tagsHtml(Model model){
        List<Tags> tags = tagsService.getAllTags();
        model.addAttribute("tags",tags);
        return "/manage/tags";
    }

    @RequestMapping("/addSort")
    public String insertSortHtml(Model model){
        return "/manage/sort-add";
    }

    @RequestMapping("/AddArticle")
    public String articleAddHtml(Model model){
        model.addAttribute("articleType",service.selectAll());
        model.addAttribute("tags",tagsService.getAllTags());
        return "/manage/add-article";
    }

    @RequestMapping("/searchArticleByCondition")
    public String searchArticleByConditionHtml(Model model){
        model.addAttribute("articleList",null);
        return "/manage/article-search-condition";
    }


}
