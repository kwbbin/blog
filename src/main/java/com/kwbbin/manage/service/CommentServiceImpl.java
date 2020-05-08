package com.kwbbin.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kwbbin.bean.Comment;
import com.kwbbin.bean.CommentExample;
import com.kwbbin.bean.MyMessage;
import com.kwbbin.bean.MyMessageExample;
import com.kwbbin.dao.CommentMapper;
import com.kwbbin.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    MessageUtil messageUtil;



    @Override
    public PageInfo getAllComment(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("responseTime desc");
        List<Comment> list = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo(list);
        list = messageUtil.commentListFiller(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void deleteCommentById(Long id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertComment(Long id,String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setReplyto(id);
        comment.setResponsetime(new Date());
        commentMapper.insert(comment);
    }
}
