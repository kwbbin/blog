package com.kwbbin.service;

import com.alibaba.druid.sql.PagerUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageSerializable;
import com.kwbbin.Vo.CommentVo;
import com.kwbbin.Vo.MessageVo;
import com.kwbbin.bean.Comment;
import com.kwbbin.bean.CommentExample;
import com.kwbbin.bean.MyMessage;
import com.kwbbin.bean.MyMessageExample;
import com.kwbbin.dao.CommentMapper;
import com.kwbbin.util.ArticleUtil;
import com.kwbbin.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UCommentServiceImpl implements UCommentService{

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    MessageUtil messageUtil;

    @Override
    public PageInfo getCommentPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        CommentExample commentExample1 = new CommentExample();
        commentExample1.setOrderByClause("responseTime desc");
        commentExample1.createCriteria().andReplytoIsNull();
        List<Comment> list = commentMapper.selectByExample(commentExample1);
        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        list = messageUtil.commentListFiller(list);

        List<CommentVo> listFinal = new ArrayList<>();
        for (Comment comment :list){
            CommentVo commentVo = new CommentVo();
            commentVo.setComment(comment);
            commentVo.setDateLong(comment.getResponsetime().getTime());
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria().andReplytoEqualTo(comment.getId());
            List<Comment> list1 = commentMapper.selectByExample(commentExample);
            list1 = messageUtil.commentListFiller(list1);
            commentVo.setReplay(list1);
            listFinal.add(commentVo);
        }
        PageInfo<CommentVo> pageInfoVo = new PageInfo<>();
        ArticleUtil.PageInfoTranslate(pageInfo,pageInfoVo);
        pageInfoVo.setList(listFinal);
        return pageInfoVo;
    }

    @Override
    public Comment saveComment(String userName, String content, HttpServletRequest request) {
        Long date = System.currentTimeMillis();
        if (request.getSession().getAttribute("saveComment")!=null){
            Long oldDate = (Long) request.getSession().getAttribute("saveComment");
            if((date-oldDate)/1000>20){
                Comment comment = new Comment();
                if(!"".equals(userName.trim())&&userName.trim()!=null){
                    comment.setName(userName);
                }else{
                    comment.setName("匿名网友");
                }
                comment.setGood(0);
                comment.setContent(content);
                comment.setResponsetime(new Date());
                commentMapper.insert(comment);
                request.getSession().setAttribute("saveComment",System.currentTimeMillis());
                return messageUtil.commentFiller(comment);
            }else{
                return null;
            }
        }else{
            Comment comment = new Comment();
            if(!"".equals(userName.trim())&&userName.trim()!=null){
                comment.setName(userName);
            }else{
                comment.setName("匿名网友");
            }
            comment.setGood(0);
            comment.setContent(content);
            comment.setResponsetime(new Date());
            commentMapper.insert(comment);
            request.getSession().setAttribute("saveComment",System.currentTimeMillis());
            return messageUtil.commentFiller(comment);
        }
    }

    @Override
    public Integer addCommentGood(Long id, HttpServletRequest request) {
        if(!"true".equals(request.getSession().getAttribute("good"+id))){
            Comment comment = commentMapper.selectByPrimaryKey(id);
            Comment comment1 = new Comment();
            comment1.setGood(comment.getGood()+1);
            comment1.setId(id);
            commentMapper.updateByPrimaryKeySelective(comment1);
            request.getSession().setAttribute("good"+id,"true");
            return 0;
        }else{
            return -1;
        }
    }
}
