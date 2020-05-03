package com.kwbbin.util;

import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.dao.ArticleMapper;
import com.kwbbin.dao.ArticleTypeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ArticleUtil {

    public static ArticleVo alterArticle(Article article,ArticleTypeMapper articleTypeMapper){
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);

        if(article.getArticleOrigin()==0){
            articleVo.setArticleOriginStr("原创");
        }else if(article.getArticleOrigin()==1){
            articleVo.setArticleOriginStr("转载");
        }else if(article.getArticleOrigin()==2){
            articleVo.setArticleOriginStr("翻译");
        }
        if(article.getArticleWay()==0){
            articleVo.setArticleWayStr("任何人可见");
        }else if(article.getArticleWay()==1){
            articleVo.setArticleWayStr("仅自己可见");
        }
        ArticleType articleType = articleTypeMapper.selectByPrimaryKey(article.getArticleType());
        articleVo.setArticleTypeStr(articleType.getName());

        return articleVo;
    }


    public static void PageInfoTranslate(PageInfo p1,PageInfo p2){
        p2.setTotal(p1.getTotal());
        p2.setPageNum(p1.getPageNum());
        p2.setPageSize(p1.getPageSize());
        p2.setStartRow(p1.getStartRow());
        p2.setSize(p1.getSize());
        p2.setEndRow(p1.getEndRow());
        p2.setPages(p1.getPages());
        p2.setPrePage(p1.getPrePage());
        p2.setNextPage(p1.getNextPage());
        p2.setIsFirstPage(p1.isIsFirstPage());
        p2.setIsLastPage(p1.isIsLastPage());
        p2.setHasNextPage(p1.isHasNextPage());
        p2.setHasPreviousPage(p1.isHasPreviousPage());
        p2.setNavigatePages(p1.getNavigatePages());
        p2.setNavigatepageNums(p1.getNavigatepageNums());
        p2.setNavigateFirstPage(p1.getNavigateFirstPage());
        p2.setNavigateLastPage(p1.getNavigateLastPage());
    }
}
