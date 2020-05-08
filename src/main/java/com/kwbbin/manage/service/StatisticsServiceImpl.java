package com.kwbbin.manage.service;

import com.kwbbin.bean.*;
import com.kwbbin.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    MyMessageMapper myMessageMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    FriendLinkMapper friendLinkMapper;

    @Autowired
    BlogBasicMapper blogBasicMapper;

    @Override
    public Integer totalArticle() {
        List<Article> list = articleMapper.selectByExample(new ArticleExample());
        return list.size();
    }

    @Override
    public Integer totalMyMessage() {
        List<MyMessage> list = myMessageMapper.selectByExample(new MyMessageExample());
        return list.size();
    }

    @Override
    public Integer totalComment() {
        List<Comment> list = commentMapper.selectByExample(new CommentExample());
        return list.size();
    }

    @Override
    public Integer FriendLink() {
        List<FriendLink> list = friendLinkMapper.selectByExample(new FriendLinkExample());
        return list.size();
    }

    @Override
    public Integer totalView() {
        return blogBasicMapper.selectByPrimaryKey(1).getVisits();
    }

    @Override
    public void addVisits(HttpServletRequest request) {
        if(!"true".equals(request.getSession().getAttribute("hasVisit"))){
            BlogBasic blogBasic = blogBasicMapper.selectByPrimaryKey(1);
            blogBasic.setVisits(blogBasic.getVisits()+1);
            blogBasicMapper.updateByPrimaryKeySelective(blogBasic);
            request.getSession().setAttribute("hasVisit","true");
        }
    }
}
