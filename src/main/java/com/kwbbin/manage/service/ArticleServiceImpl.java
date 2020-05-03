package com.kwbbin.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleExample;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.ArticleTypeExample;
import com.kwbbin.dao.ArticleMapper;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.dao.TagsArticleMapper;
import com.kwbbin.util.ArticleUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.x509.AVA;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagsArticleMapper tagsArticleMapper;

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
    public PageInfo selectAllArticle(Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<Article> listAt = articleMapper.selectByExampleWithBLOBs(new ArticleExample());
        PageInfo pageInfo = new PageInfo(listAt);
        List<ArticleVo> articleVoList = new ArrayList<>();
        for(Article at : listAt){
            ArticleVo articleVo = ArticleUtil.alterArticle(at,articleTypeMapper);
            if(articleVo.getContent().length()>60){
                articleVo.setContent(articleVo.getContent().substring(0,60));
            }
            articleVoList.add(articleVo);
        }
        PageInfo pageInfo1 = new PageInfo(articleVoList);
        ArticleUtil.PageInfoTranslate(pageInfo,pageInfo1);
        return pageInfo1;
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
        tagsArticleMapper.deleteByPrimaryKey(id);
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ArticleVo> searchArticleByCondition(String str,Integer range) {
        str = str.trim();
        ArticleExample articleExample = new ArticleExample();
        List<Article> list= null;
        if(range==null){
            range=-1;
        }
        if(range==0){
            //只查标题
            articleExample.createCriteria().andTitleLike("%"+str+"%");
            list = articleMapper.selectByExampleWithBLOBs(articleExample);
        }else if(range ==1){
            //只查内容
            list= new ArrayList<>();
            List<Article> list1 = articleMapper.selectByExampleWithBLOBs(articleExample);
            for (Article article : list1){
                if(article.getContent().contains(str)){
                    list.add(article);
                }
            }

        }else {
            //内容和标题都查
            articleExample.createCriteria().andTitleLike("%"+str+"%");
           list = articleMapper.selectByExampleWithBLOBs(articleExample);
            List<Article> list3 = articleMapper.selectByExampleWithBLOBs(new ArticleExample());
            for (Article article : list3){
                if(article.getContent().contains(str)){
                    boolean b = false;
                    for(Article a : list){
                        if (a.getId()==article.getId()){
                            b=true;
                        }
                    }

                    if(!b)
                    list.add(article);
                }
            }
        }

        List<ArticleVo> listVo = new ArrayList<>();
        for(Article article : list){
            if(article.getContent().length()>61){
                article.setContent(article.getContent().substring(0,60));
            }
            listVo.add(ArticleUtil.alterArticle(article,articleTypeMapper));
        }
        return listVo;
    }

    @Override
    public List<ArticleVo> getAllGuessLike() {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andGuessYouLikeEqualTo(0);
        List<Article> list = articleMapper.selectByExampleWithBLOBs(articleExample);
        List<ArticleVo> list1 = new ArrayList<>();
        for(Article article : list){
            if (article.getContent().length()>61){
                article.setContent(article.getContent().substring(0,60).replace("#",""));
            }
            ArticleVo articleVo = ArticleUtil.alterArticle(article,articleTypeMapper);
            list1.add(articleVo);
        }
        return list1;
    }

    @Override
    public Integer addArticleToGuessLike(String id) {
        Long articleId;
        Article article = null;
        try {
            if("".equals(id)||id==null){
                return -1;
            }else{
                articleId = Long.parseLong(id);
                article = articleMapper.selectByPrimaryKey(articleId);
                if(article==null){
                    return -1;
                }
                Article a = new Article();
                a.setGuessYouLike(0);
                a.setId(articleId);
                articleMapper.updateByPrimaryKeySelective(a);
                return 0;
            }
        }catch (Exception e){
            return -1;
        }

    }

    @Override
    public void cancelArticleToGuessLike(Long id) {
        Article article = new Article();
        article.setId(id);
        article.setGuessYouLike(1);
        articleMapper.updateByPrimaryKeySelective(article);
    }

}
