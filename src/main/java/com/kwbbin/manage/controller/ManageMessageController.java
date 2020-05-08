package com.kwbbin.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.MyMessage;
import com.kwbbin.manage.service.MessageService;
import com.kwbbin.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageMessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    MessageUtil messageUtil;

    //本网站地址
    @Value("${localInfoPath}")
    String localInfoPath;

    @RequestMapping("/getArticleMessageById")
    public ModelAndView getArticleMessageById(Long id,Integer pageNum){
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = messageService.getMessageByArticleId(id,pageNum,8);
        mv.addObject("messageList",pageInfo);
        mv.setViewName("/manage/article-message2");
        mv.addObject("hrefStr","/manage/getArticleMessageById?id="+id);
        return mv;
    }



    @RequestMapping("/getAllArticleMessagePage")
    public ModelAndView getArticleMessage(Integer pageNum){
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = messageService.getAllMessage(pageNum,8);
        mv.addObject("messageList",pageInfo);
        mv.addObject("localInfoPath",localInfoPath);
        mv.setViewName("/manage/article-message");
        return mv;
    }


    @RequestMapping("/deleteMessageById")
    public ModelAndView deleteMessageById(Long id,Integer state){
        ModelAndView mv = new ModelAndView();
        messageService.deleteMessageByMId(id);
        if(state!=null&&state==-1){
            mv.setViewName("redirect:/manage/articleMessage2");
        }else{
            mv.setViewName("redirect:/manage/articleMessage");
        }

        return mv;
    }


    @RequestMapping("/replyArticleMessage")
    public ModelAndView replyComment(Long commentId, String content){
        ModelAndView mv = new ModelAndView();
        messageService.insertArticle(commentId,content);
        mv.setViewName("redirect:/manage/articleMessage");
        return mv;
    }
}
