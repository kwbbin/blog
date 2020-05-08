package com.kwbbin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.MessageVo;
import com.kwbbin.bean.MyMessage;
import com.kwbbin.bean.MyMessageExample;
import com.kwbbin.dao.MyMessageMapper;
import com.kwbbin.util.ArticleUtil;
import com.kwbbin.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UMessageServiceImpl implements UMessageService {

    @Autowired
    MyMessageMapper messageMapper;

    @Autowired
    MessageUtil messageUtil;

    @Override
    public MyMessage saveMessage(String userName, String content,Long id,HttpServletRequest request) {
        Long date = System.currentTimeMillis();
        if (request.getSession().getAttribute("saveMessage"+id)!=null){
            Long oldDate = (Long) request.getSession().getAttribute("saveMessage"+id);
            if((date-oldDate)/1000>20){
                MyMessage message = new MyMessage();
                if(!"".equals(userName.trim())&&userName.trim()!=null){
                    message.setName(userName);
                }else{
                    message.setName("匿名网友");
                }
                message.setGood(0);
                message.setContent(content);
                message.setArticleid(id);
                message.setResponsetime(new Date());
                messageMapper.insert(message);
                request.getSession().setAttribute("saveMessage"+id,System.currentTimeMillis());
                return messageUtil.messageFiller(message);
            }else{
                return null;
            }
        }else{
            MyMessage message = new MyMessage();
            if(!"".equals(userName.trim())&&userName.trim()!=null){
                message.setName(userName);
            }else{
                message.setName("匿名网友");
            }
            message.setGood(0);
            message.setContent(content);
            message.setArticleid(id);
            message.setResponsetime(new Date());
            messageMapper.insert(message);
            request.getSession().setAttribute("saveMessage"+id,System.currentTimeMillis());
            return messageUtil.messageFiller(message);
        }

    }

    @Override
    public PageInfo getAllMyMessage(Long articleId, Integer pageNum, Integer pageSize) {
        MyMessageExample myMessageExample = new MyMessageExample();
        myMessageExample.setOrderByClause("responseTime desc");
        myMessageExample.createCriteria().andAnswertomessageIsNull().andArticleidEqualTo(articleId);
        PageHelper.startPage(pageNum,pageSize);
        List<MyMessage> list = messageMapper.selectByExample(myMessageExample);
        PageInfo<MyMessage> pageInfo = new PageInfo<>(list);
        list = messageUtil.messageListFiller(list);
        List<MessageVo> listFinal = new ArrayList<>();
        for (MyMessage myMessage :list){
            MessageVo messageVo = new MessageVo();
            messageVo.setMyMessage(myMessage);
            messageVo.setDateLong(myMessage.getResponsetime().getTime());
            MyMessageExample myMessageExample1 = new MyMessageExample();
            myMessageExample1.createCriteria().andAnswertomessageEqualTo(myMessage.getId());
            List<MyMessage> list1 = messageMapper.selectByExample(myMessageExample1);
            list1 = messageUtil.messageListFiller(list1);
            messageVo.setReplay(list1);
            listFinal.add(messageVo);
        }
        PageInfo<MessageVo> pageInfo1 = new PageInfo<>();
        pageInfo1.setList(listFinal);
        ArticleUtil.PageInfoTranslate(pageInfo,pageInfo1);
        return pageInfo1;
    }

    @Override
    public Integer addMessageGood(Long id, HttpServletRequest request) {
        if(!"true".equals(request.getSession().getAttribute("good"+id))){
            MyMessage myMessage = messageMapper.selectByPrimaryKey(id);
            MyMessage myMessage1 = new MyMessage();
            myMessage1.setGood(myMessage.getGood()+1);
            myMessage1.setId(id);
            messageMapper.updateByPrimaryKeySelective(myMessage1);
            request.getSession().setAttribute("good"+id,"true");
            return 0;
        }else{
            return -1;
        }

    }
}
