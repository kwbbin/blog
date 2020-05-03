package com.kwbbin.controller;

import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.Tags;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.manage.service.ArticleService;
import com.kwbbin.manage.service.ArticleTypeService;
import com.kwbbin.service.UArticleService;
import com.kwbbin.service.UArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UPageController {

    @Autowired
    UArticleService uArticleService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UArticleTypeService uArticleTypeService;


    @RequestMapping(value = {"","/"})
    public ModelAndView DefultView(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        PageInfo<Article> pageInfo = uArticleService.getArticleList();
        PageInfo<Article> newArticle = uArticleService.getArticleList();
        List<ArticleVo> list = articleService.getAllGuessLike();
        List<Tags> tagsList = uArticleService.getAllTags();
        List<ArticleVo> radomArticle = uArticleService.randomArticles();
        List<ArticleType> articleTypes = uArticleService.selectAllArticleType();
        mv.addObject("hotArticle",pageInfo);
        mv.addObject("newArticle",newArticle);
        mv.addObject("guessLike",list);
        mv.addObject("tagsList",tagsList);
        mv.addObject("radomArticle",radomArticle);
        mv.addObject("articleTypes",articleTypes);
        mv.setViewName("index");
        return mv;
    }

//    @RequestMapping("/sort")
//    public ModelAndView MessageView(HttpServletRequest request){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("sort");
//        return mv;
//    }

    @GetMapping("/sort")
    public ModelAndView SortView(HttpServletRequest request,Integer id,Integer pageNum){
        ModelAndView mv = new ModelAndView();
        PageInfo pageArticleVo=null;
        if(id==null){
            mv.setViewName("redirect:/");
        }else if((id!=null)&&pageNum==null){
            pageArticleVo = uArticleService.selectArticleByType(id,1,8);
            mv.addObject("currentPage",1);
        }else{
            try {
                pageArticleVo = uArticleService.selectArticleByType(id,pageNum,8);
                mv.addObject("currentPage",pageNum);
            }catch (Exception e){
                mv.setViewName("redirect:/");
            }

        }

        PageInfo<Article> newArticle = uArticleService.getArticleList();
        List<Tags> tagsList = uArticleService.getAllTags();
        List<ArticleVo> radomArticle = uArticleService.randomArticles();
        ArticleType articleType = uArticleTypeService.getArticleTypeById(id);
        mv.setViewName("sort");
        mv.addObject("pageArticleVo",pageArticleVo);
        mv.addObject("articleType",articleType);
        mv.addObject("newArticle",newArticle);
        mv.addObject("tagsList",tagsList);
        mv.addObject("radomArticle",radomArticle);

        return mv;
    }

    @GetMapping("/LabelArticles")
    public ModelAndView LabelArticlesView(HttpServletRequest request,Integer id,Integer pageNum){
        ModelAndView mv = new ModelAndView();
        PageInfo pageArticleVo=null;
        if(id==null){
            mv.setViewName("redirect:/");
        }else if((id!=null)&&pageNum==null){
            pageArticleVo = uArticleService.getArticleListByLabelId(id,1,8);
            mv.addObject("currentPage",1);
        }else{
            try {
                pageArticleVo = uArticleService.getArticleListByLabelId(id,pageNum,8);
                mv.addObject("currentPage",pageNum);
            }catch (Exception e){
                mv.setViewName("redirect:/");
            }

        }

        PageInfo<Article> newArticle = uArticleService.getArticleList();
        List<Tags> tagsList = uArticleService.getAllTags();
        List<ArticleVo> radomArticle = uArticleService.randomArticles();
        Tags tags = uArticleService.getTagsById(id);
        mv.setViewName("label-articles");
        mv.addObject("pageArticleVo",pageArticleVo);
        mv.addObject("tags",tags);
        mv.addObject("newArticle",newArticle);
        mv.addObject("tagsList",tagsList);
        mv.addObject("radomArticle",radomArticle);

        return mv;
    }


    @RequestMapping("/article/detail")
    public ModelAndView ArticleView(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("article-detail");
        return mv;
    }


//    @RequestMapping("/article/random")
//    @ResponseBody
//    public List<Article> randomArticles(){
//        return uArticleService.randomArticles();
//    }
}
