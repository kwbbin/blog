package com.kwbbin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.Vo.Page;
import com.kwbbin.bean.*;
import com.kwbbin.dao.ArticleMapper;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.dao.TagsArticleMapper;
import com.kwbbin.dao.TagsMapper;
import com.kwbbin.manage.service.ArticleService;
import com.kwbbin.util.ArticleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UArticleServiceImpl implements UArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Autowired
    TagsMapper tagsMapper;

    @Autowired
    TagsArticleMapper tagsArticleMapper;


    @Override
    public PageInfo getArticleList() {
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("good Desc");
        PageHelper.startPage(1, 6);
        List<Article> articleList = articleMapper.selectByExampleWithBLOBs(articleExample);
        List<Article> articleList1 = new ArrayList<>();
        for (Article article : articleList){
            article.setContent(article.getContent().replace("#",""));
            articleList1.add(article);
        }
        PageInfo pageInfo = new PageInfo(articleList1);
        return pageInfo;
    }

    @Override
    public PageInfo getNewArticleList() {
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("posted_time Desc");
        PageHelper.startPage(1, 6);
        List<Article> articleList = articleMapper.selectByExampleWithBLOBs(articleExample);
        List<Article> articleList1 = new ArrayList<>();
        for (Article article : articleList){
            article.setContent(article.getContent().replace("#",""));
            articleList1.add(article);
        }
        PageInfo pageInfo = new PageInfo(articleList1);
        return pageInfo;
    }

    @Override
    public List<Tags> getAllTags() {
        return tagsMapper.selectByExample(new TagsExample());
    }

    @Override
    public List<ArticleVo> randomArticles() {

        List<Article> articleList = articleMapper.selectByExampleWithBLOBs(new ArticleExample());
        List<ArticleVo> list = new ArrayList<>();
        Set<Article> set = new HashSet();
        for (Article article : articleList){
            set.add(article);
        }

        int i= 0;
        for (Article article : set){
            if (i<6){
                ArticleVo articleVo = ArticleUtil.alterArticle(article,articleTypeMapper);
                list.add(articleVo);
                i++;
            }
        }
        return list;
    }

    @Override
    public List<ArticleType> selectAllArticleType() {
        return articleTypeMapper.selectByExample(new ArticleTypeExample());
    }

    @Override
    public PageInfo<ArticleVo> selectArticleByType(Integer id,Integer pageNum,Integer pageSize) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andArticleTypeEqualTo(id);
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list = articleMapper.selectByExampleWithBLOBs(articleExample);
        PageInfo<Article> pageInfo = new PageInfo(list);
        List<ArticleVo> list1 = new ArrayList<>();
        for(Article article : pageInfo.getList()){
            if(article.getContent().length()>61){
                article.setContent(article.getContent().substring(0,60));
            }
            ArticleVo articleVo = ArticleUtil.alterArticle(article,articleTypeMapper);
            articleVo.setDateLong(article.getPostedTime().getTime());
            list1.add(articleVo);
        }
        PageInfo<ArticleVo> pageInfo1 = new PageInfo<>(list1);
        ArticleUtil.PageInfoTranslate(pageInfo,pageInfo1);
        return pageInfo1;
    }

    @Override
    public Article getArticleById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Tags getTagsById(Integer id) {
        return tagsMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo getArticleListByLabelId(Integer id,Integer pageNum,Integer pageSize) {
        TagsArticleExample tagsArticleExample = new TagsArticleExample();
        tagsArticleExample.createCriteria().andTagsIdEqualTo(id);
        PageHelper.startPage(pageNum,pageSize);
        List<TagsArticle> tags = tagsArticleMapper.selectByExample(tagsArticleExample);
        PageInfo pageInfo = new PageInfo(tags);
        List<ArticleVo> articleVoList = new ArrayList<>();
        for( TagsArticle ta : tags){
            Article article = getArticleById(ta.getArticleId());
            if(article.getContent().length()>61){
                article.setContent(article.getContent().substring(0,60).replace("#",""));
            }
            ArticleVo articleVo = ArticleUtil.alterArticle(article,articleTypeMapper);
            articleVoList.add(articleVo);
        }
        PageInfo<ArticleVo> pageInfo1 = new PageInfo<>();
        pageInfo1.setList(articleVoList);
        ArticleUtil.PageInfoTranslate(pageInfo,pageInfo1);
        return pageInfo1;
    }

}
