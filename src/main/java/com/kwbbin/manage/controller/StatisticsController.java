package com.kwbbin.manage.controller;

import com.kwbbin.bean.*;
import com.kwbbin.manage.service.ArticleService;
import com.kwbbin.manage.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;


    public Integer totalArticle() {
       return statisticsService.totalArticle();
    }


    public Integer totalMyMessage() {
        return statisticsService.totalMyMessage();
    }


    public Integer totalComment() {
        return statisticsService.totalComment();
    }


    public Integer FriendLink() {
        return statisticsService.FriendLink();
    }

}
