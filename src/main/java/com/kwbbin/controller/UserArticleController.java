package com.kwbbin.controller;

import com.alibaba.druid.sql.visitor.functions.Lpad;
import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.Page;
import com.kwbbin.bean.Article;
import com.kwbbin.service.UArticleService;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping("/getSearchArticles")
    @ResponseBody
    public PageInfo getSearchArticlesVo(@RequestBody Page page){
        String search  = page.getSearch();
        Integer pageNum = page.getPageNum();
        PageInfo pageInfo = articleService.searchArticleByCondition(search,pageNum,8);
//        System.out.println(pageInfo);
        return pageInfo;
    }

    @RequestMapping("/addGood")
    @ResponseBody
    public Page addGood(@RequestBody Article article, HttpServletRequest request){
        Page page = new Page();
        try {
            if("true".equals(request.getSession().getAttribute("good"+article.getId()))){
                page.setResponseStr("already");
                return page;
            }else{
                articleService.addArticleGood(article.getId());
                request.getSession().setAttribute("good"+article.getId(),"true");
            }
        }catch (Exception e){
            page.setResponseStr("false");
            return page;
        }
        page.setResponseStr("true");
        return page;
    }

    @RequestMapping("/addVisit")
    @ResponseBody
    public String addVisit(@RequestBody Article article){
        try {
            articleService.addArticleVisit(article.getId());
        }catch (Exception e){
            return "false";
        }
        return "true";
    }


    @GetMapping("/getAboutArticle")
    @ResponseBody
    public List<Article> getAboutArticle(Long articleId){
        return articleService.getAboutArticle(articleId);
    }
}
