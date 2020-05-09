package com.kwbbin.controller;

import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.Vo.MessageVo;
import com.kwbbin.Vo.Page;
import com.kwbbin.bean.*;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.dao.BlogBasicMapper;
import com.kwbbin.dao.TimeLineMapper;
import com.kwbbin.manage.service.*;
import com.kwbbin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


    //本网站地址
    @Value("${localInfoPath}")
    String localInfoPath;

    @Autowired
    UArticleService uArticleService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UArticleTypeService uArticleTypeService;

    @Autowired
    FriendLinkService friendLinkService;

    @Autowired
    TimeLineService timeLineService;

    @Autowired
    UMessageService uMessageService;

    @Autowired
    UCommentService uCommentService;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    BlogBasicService blogBasicService;


    @RequestMapping(value = {"","/"})
    public ModelAndView DefultView(HttpServletRequest request){
        statisticsService.addVisits(request);
        ModelAndView mv = new ModelAndView();
        PageInfo<Article> pageInfo = uArticleService.getArticleList();
        PageInfo<Article> newArticle = uArticleService.getArticleList();
        List<ArticleVo> list = articleService.getAllGuessLike();
        List<Tags> tagsList = uArticleService.getAllTags();
        List<ArticleVo> radomArticle = uArticleService.randomArticles();
        List<ArticleType> articleTypes = uArticleService.selectAllArticleType();
        List<FriendLink> flinkList = friendLinkService.getAllFriendLink();

        mv.addObject("hotArticle",pageInfo);
        mv.addObject("newArticle",newArticle);
        mv.addObject("guessLike",list);
        mv.addObject("tagsList",tagsList);
        mv.addObject("radomArticle",radomArticle);
        mv.addObject("articleTypes",articleTypes);
        mv.addObject("flinkList",flinkList);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/timeLine")
    public ModelAndView MessageView(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.addObject("timeLineList",timeLineService.getAllTimeLineVo());
        mv.setViewName("time-line");
        return mv;
    }

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
        mv.addObject("tagsDesc","这里展示是该标签的文章");
        mv.addObject("newArticle",newArticle);
        mv.addObject("tagsList",tagsList);
        mv.addObject("radomArticle",radomArticle);

        return mv;
    }

    @RequestMapping("/SearchArticles")
    public ModelAndView SearchArticlesView(HttpServletRequest request,String search,Integer pageNum){
        ModelAndView mv = new ModelAndView();
        PageInfo pageArticleVo=null;
        if(pageNum==null){
            try {
                pageArticleVo = uArticleService.searchArticleByCondition(search,1,8);
                mv.addObject("currentPage",1);
            }catch (Exception e){
                mv.setViewName("redirect:/");
            }
        }else{
            try {
                pageArticleVo = uArticleService.searchArticleByCondition(search,pageNum,8);
                mv.addObject("currentPage",pageNum);
            }catch (Exception e){
                mv.setViewName("redirect:/");
            }

        }
        if(pageArticleVo.getList().size()==0){
            mv.addObject("size",0);
        }
        PageInfo<Article> newArticle = uArticleService.getArticleList();
        List<Tags> tagsList = uArticleService.getAllTags();
        List<ArticleVo> radomArticle = uArticleService.randomArticles();
        mv.setViewName("search-articles");
        mv.addObject("pageArticleVo",pageArticleVo);
        mv.addObject("search",search);
        mv.addObject("title","搜索关键词："+search);
        mv.addObject("tagsDesc","这里展示搜索的文章");
        mv.addObject("newArticle",newArticle);
        mv.addObject("tagsList",tagsList);
        mv.addObject("radomArticle",radomArticle);

        return mv;
    }

//    /article/detail?id=37
    @GetMapping("/article/detail")
    public ModelAndView ArticleView(HttpServletRequest request,Long id){
        ModelAndView mv = new ModelAndView();
        ArticleVo article=null;
        if(id==null){
            mv.setViewName("redirect:/");
            return mv;
        }else{
            try {
                article = uArticleService.getArticleVoById(id);
                if(article==null){
                    mv.setViewName("redirect:/");
                    return mv;
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

        uArticleService.addArticleVisit(id);
        PageInfo<Article> newArticle = uArticleService.getArticleList();
        List<Tags> tagsList = uArticleService.getAllTags();
        List<ArticleVo> radomArticle = uArticleService.randomArticles();
        List<Tags> thisTagsList = uArticleService.getTagsListByArticleId(id);
        List<Article> aboutList = uArticleService.getAboutArticle(id);
        PageInfo<MessageVo> messageVoPage = uMessageService.getAllMyMessage(id,1,8);

        mv.addObject("messageVoPage",messageVoPage);
        mv.setViewName("article-detail");
        mv.addObject("newArticle",newArticle);
        mv.addObject("localInfoPath",localInfoPath);
        mv.addObject("article",article);
        mv.addObject("aboutList",aboutList);
        mv.addObject("tagsList",tagsList);
        mv.addObject("radomArticle",radomArticle);
        mv.addObject("articleTagsList",thisTagsList);
        return mv;
    }


    @RequestMapping("/comment")
    public ModelAndView comment(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        PageInfo<Comment> pageComment = uCommentService.getCommentPage(1,8);
        PageInfo<Article> newArticle = uArticleService.getArticleList();
        List<Tags> tagsList = uArticleService.getAllTags();
        List<ArticleVo> radomArticle = uArticleService.randomArticles();

        mv.setViewName("comment");
        mv.addObject("pageComment",pageComment);
        mv.addObject("newArticle",newArticle);
        mv.addObject("localInfoPath",localInfoPath);
        mv.addObject("tagsList",tagsList);
        mv.addObject("radomArticle",radomArticle);
        return mv;
    }

//    @RequestMapping("/article/random")
//    @ResponseBody
//    public List<Article> randomArticles(){
//        return uArticleService.randomArticles();
//    }
}
