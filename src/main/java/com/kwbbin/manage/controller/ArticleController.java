package com.kwbbin.manage.controller;

import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleTypeExample;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.manage.service.ArticleService;
import com.kwbbin.manage.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    Integer pageSize = 6;

    @RequestMapping("/getAllArticleVo")
    @ResponseBody
    public List<ArticleVo> selectAllArticle(){
        return articleService.selectAllArticle();
    }

    @GetMapping("/getAllArticleVoPage")
    public ModelAndView selectAllArticlePage(Integer pageNum){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manage/search-article");
        modelAndView.addObject("articleList",articleService.selectAllArticle(pageNum,pageSize));
        return modelAndView;
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

    @RequestMapping("/findArticle")
    public ModelAndView searchArticleByCondition(String str,Integer range){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("articleList",articleService.searchArticleByCondition(str,range));
        modelAndView.setViewName("manage/article-search-condition");
        return modelAndView;
    }


    @RequestMapping("/addGuessLike")
    public ModelAndView addArticleToGuessLike(String id){
        ModelAndView modelAndView = new ModelAndView();
        Integer i = articleService.addArticleToGuessLike(id);
        if(i==-1){
            modelAndView.addObject("msg","填写格式错误或者该ID不存在");
            modelAndView.setViewName("manage/error");
        }else{
            modelAndView.setViewName("redirect:/manage/guessLike");
        }

        return modelAndView;
    }


    @RequestMapping("/cancelGuessLike")
    public ModelAndView cancelArticleToGuessLike(Long id){
        ModelAndView mv = new ModelAndView();
        articleService.cancelArticleToGuessLike(id);
        mv.setViewName("redirect:/manage/guessLike");
        return mv;
    }

}
