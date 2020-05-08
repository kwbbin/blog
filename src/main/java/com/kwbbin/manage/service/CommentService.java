package com.kwbbin.manage.service;

import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.Comment;

public interface CommentService {
    PageInfo getAllComment(Integer pageNum,Integer pageSize);
    void deleteCommentById(Long id);
    void insertComment(Long id,String content);
}
