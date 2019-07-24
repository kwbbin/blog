package com.kwbbin.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage")
public class PublishArticleController {
//    @RequestMapping("")
//    public String publishArticle(){
//        return "manage/index-manage";
//    }

    // 前端提交按钮把 editor.md 里的内容提交过来
    @RequestMapping("/save")
    @ResponseBody
    public String save(String data) {
        System.out.println(data);
        return "success";
    }

    @RequestMapping("/show")
    public String show(Model model) {
        model.addAttribute("str","## helloworld");
        return "manage/show-test";
    }


}
