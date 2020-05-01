package com.kwbbin.manage.controller;

import com.kwbbin.bean.Tags;
import com.kwbbin.manage.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class TagsController {

    @Autowired
    TagsService tagsService;

    @RequestMapping("/updateTags")
    public String updateTags(Integer tagsId ,String tagsName){
        Tags tags = new Tags();
        tags.setId(tagsId);
        tags.setTagName(tagsName);
        tagsService.updateTages(tags);
        return "redirect:/manage/tags";
    }
}
