package com.kwbbin.manage.controller;

import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.FriendLink;
import com.kwbbin.bean.MyMessage;
import com.kwbbin.bean.Tags;
import com.kwbbin.bean.TimeLine;
import com.kwbbin.manage.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    TimeLineService timeLineService;

    @Autowired
    MessageService messageService;

    @Autowired
    CommentService commentService;

    @Autowired
    StatisticsService statisticsService;

    //本网站地址
    @Value("${localInfoPath}")
    String localInfoPath;

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
    public ModelAndView testmain(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("totalArticle",statisticsService.totalArticle());
        modelAndView.addObject("totalMessage",statisticsService.totalMyMessage());
        modelAndView.addObject("totalFriendLink",statisticsService.FriendLink());
        modelAndView.addObject("totalComment",statisticsService.totalComment());
        modelAndView.addObject("vists",statisticsService.totalView());
        modelAndView.setViewName("/manage/statistics");
        return modelAndView;
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

    @RequestMapping("/timeLine")
    public ModelAndView timeLine(Model model){
        ModelAndView mv = new ModelAndView();
        List<TimeLine> list = timeLineService.getAllTimeline();
        mv.addObject("timeLine",list);
        mv.setViewName("/manage/time-line");
        return mv;
    }

    @RequestMapping("/articleMessage")
    public ModelAndView ArticleMessage(Model model){
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = messageService.getAllMessage(1,8);
        mv.addObject("messageList",pageInfo);
        mv.addObject("localInfoPath",localInfoPath);
        mv.setViewName("/manage/article-message");
        return mv;
    }

    @RequestMapping("/articleMessage2")
    public ModelAndView ArticleMessageById(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/manage/article-message2");
        mv.addObject("messageList",new PageInfo<>());
        return mv;
    }

    @RequestMapping("/comment")
    public ModelAndView comment(Model model){
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = commentService.getAllComment(1,8);
        mv.addObject("commentList",pageInfo);
        mv.addObject("localInfoPath",localInfoPath);
        mv.setViewName("/manage/comment");
        return mv;
    }


}
