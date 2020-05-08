package com.kwbbin.service;


import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.MessageVo;
import com.kwbbin.bean.MyMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UMessageService {
    MyMessage saveMessage(String userName, String content,Long id,HttpServletRequest request);
    Integer addMessageGood(Long id, HttpServletRequest request);
    PageInfo getAllMyMessage(Long articleId, Integer pageNum, Integer pageSize);
}
