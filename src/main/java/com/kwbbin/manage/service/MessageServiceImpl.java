package com.kwbbin.manage.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageSerializable;
import com.kwbbin.bean.Comment;
import com.kwbbin.bean.MyMessage;
import com.kwbbin.bean.MyMessageExample;
import com.kwbbin.dao.MyMessageMapper;
import com.kwbbin.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.MembershipKey;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MyMessageMapper myMessageMapper;

    @Autowired
    MessageUtil messageUtil;

    @Override
    public PageInfo getMessageByArticleId(Long id, Integer pageNum, Integer pageSize) {
        MyMessageExample myMessageExample = new MyMessageExample();
        myMessageExample.createCriteria().andArticleidEqualTo(id);
        PageHelper.startPage(pageNum,pageSize);
        List<MyMessage> list = myMessageMapper.selectByExample(myMessageExample);
        PageInfo<MyMessage> pageInfo = new PageInfo<>(list);
        list = messageUtil.messageListFiller(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public PageInfo getAllMessage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        MyMessageExample myMessageExample = new MyMessageExample();
        myMessageExample.setOrderByClause("responsetime desc");
        List<MyMessage> list = myMessageMapper.selectByExample(myMessageExample);
        PageInfo<MyMessage> pageInfo = new PageInfo(list);
        list = messageUtil.messageListFiller(list);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public void deleteMessageByMId(Long id) {
        myMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertArticle(Long id, String content) {
       MyMessage myMessage = new MyMessage();
       myMessage.setContent(content);
       myMessage.setResponsetime(new Date());
       myMessage.setAnswertomessage(id);
       myMessageMapper.insert(myMessage);
    }
}
