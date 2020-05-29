package com.kwbbin.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleTypeExample;
import com.kwbbin.bean.LocalImage;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.manage.service.ArticleService;
import com.kwbbin.manage.service.TagsService;
import com.kwbbin.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    //本网站地址
    @Value("${localInfoPath}")
    String localInfoPath;


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
            modelAndView.addObject("localImages",articleService.getAllLocalImage());
            modelAndView.addObject("localInfoPath",localInfoPath);
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

    @RequestMapping("/deleteLocalImage")
    public ModelAndView deleteLocalImage(String ids){
        ModelAndView mv = new ModelAndView();
        articleService.deleteLocalImageById(ids);
        mv.setViewName("redirect:/manage/localImage");
        return mv;
    }



    @RequestMapping("/uploadLocalImage")
    public String uploadLocalImage(@RequestParam(value = "file", required = true) MultipartFile file,String name, HttpServletRequest request, HttpServletResponse response){
       articleService.saveLocalImage(file, name);
        return "redirect:/manage/localImage";
    }

}
