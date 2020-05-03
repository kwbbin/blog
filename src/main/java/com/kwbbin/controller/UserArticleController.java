package com.kwbbin.controller;

import com.alibaba.druid.sql.visitor.functions.Lpad;
import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.Page;
import com.kwbbin.service.UArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/getSortArticleVo")
    @ResponseBody
    public PageInfo getSortArticleVo(@RequestBody Page page){
        Integer id = page.getTypeId();
        Integer pageNum = page.getPageNum();
        if(id==null || pageNum==null){
            return new PageInfo();
        }
        PageInfo pageInfo = articleService.selectArticleByType(id,pageNum,8);
//        System.out.println(pageInfo);
        return pageInfo;
    }

    @RequestMapping("/getLabelArticles")
    @ResponseBody
    public PageInfo getLabelArticlesVo(@RequestBody Page page){
        Integer id = page.getTagsId();
        Integer pageNum = page.getPageNum();
        if(id==null || pageNum==null){
            return new PageInfo();
        }
        PageInfo pageInfo = articleService.getArticleListByLabelId(id,pageNum,8);
//        System.out.println(pageInfo);
        return pageInfo;
    }
}
