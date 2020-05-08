package com.kwbbin.manage.service;

import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.MyMessage;

import java.util.List;

public interface MessageService {
    PageInfo getMessageByArticleId(Long id, Integer pageNum, Integer pageSize);
    PageInfo getAllMessage(Integer pageNum,Integer pageSize);
    void deleteMessageByMId(Long id);
    void insertArticle(Long id,String content);
}
