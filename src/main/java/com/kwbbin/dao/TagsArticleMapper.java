package com.kwbbin.dao;

import com.kwbbin.bean.TagsArticle;
import com.kwbbin.bean.TagsArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagsArticleMapper {
    int countByExample(TagsArticleExample example);

    int deleteByExample(TagsArticleExample example);

    int deleteByPrimaryKey(Long articleId);

    int insert(TagsArticle record);

    int insertSelective(TagsArticle record);

    List<TagsArticle> selectByExample(TagsArticleExample example);

    TagsArticle selectByPrimaryKey(Long articleId);

    int updateByExampleSelective(@Param("record") TagsArticle record, @Param("example") TagsArticleExample example);

    int updateByExample(@Param("record") TagsArticle record, @Param("example") TagsArticleExample example);

    int updateByPrimaryKeySelective(TagsArticle record);

    int updateByPrimaryKey(TagsArticle record);
}