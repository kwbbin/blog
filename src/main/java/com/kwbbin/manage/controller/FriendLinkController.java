package com.kwbbin.manage.controller;

import com.kwbbin.bean.FriendLink;
import com.kwbbin.manage.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class FriendLinkController {

    @Autowired
    FriendLinkService friendLinkService;

    @RequestMapping("/getAllFriendLink")
    public ModelAndView selectAllFriendLink(){
        ModelAndView mv = new ModelAndView();
        List<FriendLink> list = friendLinkService.getAllFriendLink();
        mv.addObject("friendLink",list);
        mv.setViewName("/manage/friend-link");
        return mv;
    }

    @RequestMapping("/updateFriendLink")
    public ModelAndView updateFriendLink(String path,String name,Integer id){
        ModelAndView mv = new ModelAndView();
        FriendLink friendLink = new FriendLink();
        friendLink.setId(id);
        friendLink.setName(name);
        friendLink.setPath(path);
        friendLinkService.updateFriendLink(friendLink);
        mv.setViewName("redirect:/manage/getAllFriendLink");
        return mv;
    }

    @RequestMapping("/addFriendLink")
    public ModelAndView addFriendLink(String path,String name){
        ModelAndView mv = new ModelAndView();
        FriendLink friendLink = new FriendLink();
        friendLink.setName(name);
        friendLink.setPath(path);
        friendLinkService.addFriendLink(friendLink);
        mv.setViewName("redirect:/manage/getAllFriendLink");
        return mv;
    }

    @RequestMapping("/deleteFriendLink")
    public ModelAndView deleteFriendLink(Integer id){
        ModelAndView mv = new ModelAndView();
        friendLinkService.deleteFriendLink(id);
        mv.setViewName("redirect:/manage/getAllFriendLink");
        return mv;
    }
}
