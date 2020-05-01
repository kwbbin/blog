package com.kwbbin.manage.service;

import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleExample;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.ArticleTypeExample;
import com.kwbbin.dao.ArticleMapper;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.util.ArticleUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Autowired
    ArticleMapper articleMapper;

    public List<ArticleType> selectAll(){
        return articleTypeMapper.selectByExample(new ArticleTypeExample());
    }

    public int insert(Article record){
        articleMapper.insert(record);
        return Integer.parseInt(record.getId()+"");
    }

    @Override
    public List<ArticleVo> selectAllArticle() {
        List<Article> listAt = articleMapper.selectByExampleWithBLOBs(new ArticleExample());
        List<ArticleVo> articleVoList = new ArrayList<>();
        for(Article at : listAt){
            ArticleVo articleVo = ArticleUtil.alterArticle(at,articleTypeMapper);
            if(articleVo.getContent().length()>60){
                articleVo.setContent(articleVo.getContent().substring(0,60));
            }
            articleVoList.add(articleVo);
        }
        return articleVoList;
    }

    @Override
    public Article getArticleById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateArticleById(Article article) {

    }

    @Override
    public void deleteArticleById(Long id) {
        articleMapper.deleteByPrimaryKey(id);
    }

}
