package com.kwbbin.manage.controller;

import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.Tags;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.manage.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class SortController {

    @Autowired
    ArticleTypeService articleTypeService;

    @RequestMapping("/getAllArticleType")
    public List<ArticleType> getArticleTypeList(){
        return articleTypeService.getArticleTypeList();
    }

    @RequestMapping("/updateArticleType")
    public String updateArticleType(Integer sortId ,String title,String path,String code,String desc){
        ArticleType at = new ArticleType();
        at.setId(sortId);
        at.setName(title);
        at.setCode(code);
        at.setPath(path);
        at.setTypeDesc(desc);
        articleTypeService.updateArticleType(at);
        return "redirect:/manage/sort";
    }

    @RequestMapping("/deleteArticleType")
    public ModelAndView deleteArticleType(Integer id){
        Integer i = articleTypeService.deleteArticleType(id);
        ModelAndView mv = new ModelAndView();
        if(i != -1){
            mv.setViewName("redirect:/manage/sort");
            return mv;
        }else {
            mv.setViewName("/manage/error");
            mv.addObject("msg","无法删除，该分类被文章引用！");
            return mv;
        }
    }

    @RequestMapping("/insertArticleType")
    public String insertArticleType(String title,String path,String code,String desc){
        ArticleType at = new ArticleType();
        at.setName(title);
        at.setPath(path);
        at.setCode(code);
        at.setTypeDesc(desc);
        articleTypeService.insertArticleType(at);
        return "redirect:/manage/sort";
    }
}
