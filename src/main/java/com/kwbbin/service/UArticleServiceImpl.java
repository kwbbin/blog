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

import java.util.*;

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
    public ArticleVo getArticleVoById(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleVo articleVo = ArticleUtil.alterArticle(article,articleTypeMapper);
        return articleVo;
    }

    @Override
    public Tags getTagsById(Integer id) {
        return tagsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tags> getTagsListByArticleId(Long id) {
        TagsArticleExample tagsArticleExample = new TagsArticleExample();
        tagsArticleExample.createCriteria().andArticleIdEqualTo(id);
        List<TagsArticle> tags = tagsArticleMapper.selectByExample(tagsArticleExample);
        List<Tags> tagsList = new ArrayList<>();
        for(TagsArticle tagsArticle : tags){
            Tags t = tagsMapper.selectByPrimaryKey(tagsArticle.getTagsId());
            tagsList.add(t);
        }
        return tagsList;
    }

    @Override
    public void addArticleGood(Long id) {
        Article a = getArticleById(id);
        Article article = new Article();
        article.setId(id);
        article.setGood(a.getGood()+1);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void addArticleVisit(Long id) {
        Article a = getArticleById(id);
        Article article = new Article();
        article.setId(id);
        article.setVisits(a.getVisits()+1);
        articleMapper.updateByPrimaryKeySelective(article);
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


    @Override
    public PageInfo searchArticleByCondition(String str,Integer pageNum,Integer pageSize) {
        str = str.trim();
        ArticleExample articleExample = new ArticleExample();
        List<Article> list = null;
        //内容和标题都查
        articleExample.createCriteria().andTitleLike("%" + str + "%");
        PageHelper.startPage(pageNum, pageSize);
        list = articleMapper.selectByExampleWithBLOBs(articleExample);
        PageInfo<Article> pageInfo = new PageInfo(list);
        PageInfo<ArticleVo> pageInfo1 = new PageInfo<>();
        List<ArticleVo> listVo = new ArrayList<>();
        for (Article article : pageInfo.getList()) {
            if (article.getContent().length()>60)
            article.setContent(article.getContent().substring(0,60));
            ArticleVo articleVo = ArticleUtil.alterArticle(article, articleTypeMapper);
            listVo.add(articleVo);
        }
        ArticleUtil.PageInfoTranslate(pageInfo, pageInfo1);
        pageInfo1.setList(listVo);
        return pageInfo1;
    }

    @Override
    public List<Article> getAboutArticle(Long id) {
        TagsArticleExample tagsArticleExample = new TagsArticleExample();
        tagsArticleExample.createCriteria().andArticleIdEqualTo(id);
        List<TagsArticle> list = tagsArticleMapper.selectByExample(tagsArticleExample);
        List<TagsArticle> listTATotal = new ArrayList<>();
        for (TagsArticle tagsArticle : list){
            TagsArticleExample tagsArticleExample1 = new TagsArticleExample();
            tagsArticleExample1.createCriteria().andTagsIdEqualTo(tagsArticle.getTagsId());
            List<TagsArticle> list1 = tagsArticleMapper.selectByExample(tagsArticleExample1);
            listTATotal.addAll(list1);
        }
        //保存该文章所有标签下的文章
        List<Article> listATotal = new ArrayList<>();
        for(TagsArticle ta: listTATotal){
            Article article = articleMapper.selectByPrimaryKey(ta.getArticleId());
            listATotal.add(article);
        }

        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andArticleTypeEqualTo(article.getArticleType());
        //保存该文章一样类型的文章
        List<Article> list2 = articleMapper.selectByExample(articleExample);

        listATotal.addAll(list2);

        for(int i = 0 ;i<listATotal.size();i++){
            if(listATotal.get(i).getId()==id){
                listATotal.remove(i);
                i--;
            }
        }

        Map<Long,Article> map = new HashMap<>();
        for(Article article1 : listATotal){
            map.put(article1.getId(),article1);
        }

        List<Article> list3 = new ArrayList<>();
        for(Map.Entry<Long, Article> a:map.entrySet()){
            list3.add(a.getValue());

        }

        Set<Article> set = new HashSet<>();
        for (Article article1 : list3){
            set.add(article1);
        }

        List<Article> list4 = new ArrayList<>();
        int i=0;
        for (Article article1 : set){
            i++;
            if (i<6){
                list4.add(article1);
            }
        }
        return list4;
    }
}
