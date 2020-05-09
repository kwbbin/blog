package com.kwbbin.controller;

import com.kwbbin.service.BlogBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogBasicController {
    @Autowired
    BlogBasicService blogBasicService;

    @RequestMapping("/getIndexMotto")
    @ResponseBody
    public String[] getMottos(){
        return blogBasicService.selectIndexMotto();
    }


    @RequestMapping("/manage/updateMotto")
    public ModelAndView updateMotto(String motto){
        ModelAndView modelAndView = new ModelAndView();
        blogBasicService.updateMotto(motto);
        modelAndView.setViewName("redirect:/manage/motto");
        return modelAndView;
    }
}
