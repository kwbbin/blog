package com.kwbbin.util;

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

}
