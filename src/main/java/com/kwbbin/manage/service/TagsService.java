package com.kwbbin.manage.service;

import com.kwbbin.Vo.TagsVo;
import com.kwbbin.bean.DraftTagsKey;
import com.kwbbin.bean.Tags;
import com.kwbbin.bean.TagsArticle;

import java.util.List;

public interface TagsService {

    List<Tags> getAllTags();

    List<DraftTagsKey> getAllDraftTags();

    void updateTages(Tags tags);

    List<TagsArticle> getAllTagsArticleByArticleId(Long id);

    List<DraftTagsKey> getAllDraftTagsByDraftId(Long id);

    List<TagsVo> getAllTagsVo(Long ArticleId);

    List<TagsVo> getAllDraftTagsVo(Long draftId);

    void deleteTages(Integer id);

    void addTages(String tagsName);
}
