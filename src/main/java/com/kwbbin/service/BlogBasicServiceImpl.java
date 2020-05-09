package com.kwbbin.service;

import com.kwbbin.bean.BlogBasic;
import com.kwbbin.dao.BlogBasicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogBasicServiceImpl implements BlogBasicService {

    @Autowired
    BlogBasicMapper blogBasicMapper;

    @Override
    public String [] selectIndexMotto() {
        BlogBasic blogBasic =  blogBasicMapper.selectByPrimaryKey(1);
        String motto = blogBasic.getIndexMotto();
        String [] mottos = motto.split("@@");
        return mottos;
    }

    @Override
    public void updateMotto(String str) {
        BlogBasic blogBasic = new BlogBasic();
        blogBasic.setIndexMotto(str);
        blogBasic.setId(1);
        blogBasicMapper.updateByPrimaryKeySelective(blogBasic);
    }
}
