package com.kwbbin.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kwbbin.manage.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class CommentController {

    @Autowired
    CommentService commentService;

    //本网站地址
    @Value("${localInfoPath}")
    String localInfoPath;

    @RequestMapping("/getAllCommentPage")
    public ModelAndView getComment(Integer pageNum){
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = commentService.getAllComment(pageNum,8);
        mv.addObject("commentList",pageInfo);
        mv.addObject("localInfoPath",localInfoPath);
        mv.setViewName("manage/comment");
        return mv;
    }


    @RequestMapping("/deleteCommentById")
    public ModelAndView deleteCommentById(Long id, Integer state){
        ModelAndView mv = new ModelAndView();
        commentService.deleteCommentById(id);
        mv.setViewName("redirect:/manage/comment");
        return mv;
    }


    @RequestMapping("/replyComment")
    public ModelAndView replyComment(Long commentId, String content){
        ModelAndView mv = new ModelAndView();
        commentService.insertComment(commentId,content);
        mv.setViewName("redirect:/manage/comment");
        return mv;
    }
}
