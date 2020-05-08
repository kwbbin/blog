package com.kwbbin.controller;

import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.Page;
import com.kwbbin.bean.MyMessage;
import com.kwbbin.service.UMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MessageController {

    @Autowired
    UMessageService uMessageService;

    @RequestMapping("/saveMessage")
    @ResponseBody
    public MyMessage saveMessage(@RequestBody Page page,HttpServletRequest request){
        MyMessage myMessage = uMessageService.saveMessage(page.getUserName(),page.getCommentContent(),page.getId(),request);
        return myMessage;
    }

    @RequestMapping("/addMessageGood")
    @ResponseBody
    public Integer addMessageGood(@RequestBody MyMessage myMessage, HttpServletRequest request){
        Integer state = uMessageService.addMessageGood(myMessage.getId(),request);
        return state;
    }

    @RequestMapping("/getMessagePage")
    @ResponseBody
    public PageInfo getCommentPage(@RequestBody Page page){
        return uMessageService.getAllMyMessage(page.getId(),page.getPageNum(),8);
    }
}
