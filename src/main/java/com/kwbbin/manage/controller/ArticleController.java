package com.kwbbin.manage.controller;

import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleTypeExample;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.manage.service.ArticleService;
import com.kwbbin.manage.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Autowired
    TagsService tagsService;

    @RequestMapping("/getAllArticleVo")
    @ResponseBody
    public List<ArticleVo> selectAllArticle(){
        return articleService.selectAllArticle();
    }

    @RequestMapping("/getArticleById")
    public ModelAndView selectArticleById(Long id,Integer deleteState, ModelAndView modelAndView){
        if (deleteState == -1){
            articleService.deleteArticleById(id);
            modelAndView.setViewName("redirect:/manage/searchAllArticle");
            return modelAndView;
        }else{
            Article article = articleService.getArticleById(id);
            modelAndView.setViewName("manage/article-detail");
            modelAndView.addObject("article",article);
            modelAndView.addObject("articleType",articleTypeMapper.selectByExample(new ArticleTypeExample()));
            modelAndView.addObject("tags",tagsService.getAllTagsVo(id));
            return modelAndView;
        }

    }

    @RequestMapping("/updateArticle")
    public void updateArticleById(Article article){

    }
}
