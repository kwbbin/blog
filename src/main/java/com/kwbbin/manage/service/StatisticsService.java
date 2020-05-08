package com.kwbbin.manage.service;

import javax.servlet.http.HttpServletRequest;

public interface StatisticsService {
    Integer totalArticle();
    Integer totalMyMessage();
    Integer totalComment();
    Integer FriendLink();
    Integer totalView();
    void addVisits(HttpServletRequest request);
}
