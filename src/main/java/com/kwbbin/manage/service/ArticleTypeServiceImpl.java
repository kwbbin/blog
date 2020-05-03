package com.kwbbin.manage.service;

import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleExample;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.ArticleTypeExample;
import com.kwbbin.dao.ArticleMapper;
import com.kwbbin.dao.ArticleTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTypeServiceImpl implements  ArticleTypeService {
    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public List<ArticleType> getArticleTypeList() {
        return articleTypeMapper.selectByExample(new ArticleTypeExample());
    }

    @Override
    public void updateArticleType(ArticleType articleType) {
        articleTypeMapper.updateByPrimaryKeySelective(articleType);
    }

    @Override
    public void insertArticleType(ArticleType articleType) {
        articleTypeMapper.insert(articleType);

    }

    @Override
    public Integer deleteArticleType(Integer id) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andArticleTypeEqualTo(id);
        List<Article> list = articleMapper.selectByExample(articleExample);
        if(list.size()==0){
            articleTypeMapper.deleteByPrimaryKey(id);
            return 0;
        }else{
            return -1;
        }
    }
}
