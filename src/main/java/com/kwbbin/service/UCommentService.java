package com.kwbbin.service;

import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.Comment;

import javax.servlet.http.HttpServletRequest;

public interface UCommentService {
    PageInfo getCommentPage(Integer pageNum,Integer pageSize);
    Comment saveComment(String userName, String content, HttpServletRequest request);
    Integer addCommentGood(Long id, HttpServletRequest request);
}
