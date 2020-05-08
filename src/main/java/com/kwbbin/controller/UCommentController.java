package com.kwbbin.controller;

import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.Page;
import com.kwbbin.bean.Comment;
import com.kwbbin.bean.MyMessage;
import com.kwbbin.service.UCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UCommentController {

    @Autowired
    UCommentService uCommentService;

    @RequestMapping("/getCommentPage")
    @ResponseBody
    public PageInfo getCommentPage(@RequestBody Page page){
        return uCommentService.getCommentPage(page.getPageNum(),8);
    }
    @RequestMapping("/saveComment")
    @ResponseBody
    public Comment saveMessage(@RequestBody Page page, HttpServletRequest request){
        Comment comment = uCommentService.saveComment(page.getUserName(),page.getCommentContent(),request);
        return comment;
    }

    @RequestMapping("/addCommentGood")
    @ResponseBody
    public Integer addMessageGood(@RequestBody Comment comment, HttpServletRequest request){
        Integer state = uCommentService.addCommentGood(comment.getId(),request);
        return state;
    }

}
