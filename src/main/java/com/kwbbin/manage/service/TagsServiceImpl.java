package com.kwbbin.manage.service;

import com.kwbbin.Vo.TagsVo;
import com.kwbbin.bean.*;
import com.kwbbin.dao.DraftTagsMapper;
import com.kwbbin.dao.TagsArticleMapper;
import com.kwbbin.dao.TagsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagsServiceImpl implements  TagsService {

    @Autowired
    TagsMapper tagsMapper;

    @Autowired
    TagsArticleMapper tagsArticleMapper;

    @Autowired
    DraftTagsMapper draftTagsMapper;

    @Override
    public List<DraftTagsKey> getAllDraftTags() {
        return draftTagsMapper.selectByExample(new DraftTagsExample());
    }


    @Override
    public List<Tags> getAllTags() {
        return tagsMapper.selectByExample(new TagsExample());
    }

    @Override
    public void updateTages(Tags tags) {
        tagsMapper.updateByPrimaryKeySelective(tags);
    }

    @Override
    public List<TagsArticle> getAllTagsArticleByArticleId(Long id) {
        TagsArticleExample tagsArticleExample = new TagsArticleExample();
        tagsArticleExample.createCriteria().andArticleIdEqualTo(id);
        return tagsArticleMapper.selectByExample(tagsArticleExample);
    }


    @Override
    public List<DraftTagsKey> getAllDraftTagsByDraftId(Long id) {
        DraftTagsExample draftTagsExample = new DraftTagsExample();
        draftTagsExample.createCriteria().andDraftIdEqualTo(id);
        return draftTagsMapper.selectByExample(draftTagsExample);
    }

    @Override
    public List<TagsVo> getAllTagsVo(Long ArticleId) {
        List<Tags> list = getAllTags();
        List<TagsArticle> list1 = getAllTagsArticleByArticleId(ArticleId);
        List<TagsVo> list3 = new ArrayList<>();

        for (Tags tags : list){
            boolean b=false;
            for (TagsArticle ta : list1){
                if(tags.getId() == ta.getTagsId()){
                    b=true;
                }
            }
            TagsVo tagsVo = new TagsVo();
            BeanUtils.copyProperties(tags,tagsVo);
            tagsVo.setIsChecked(b);
            list3.add(tagsVo);
        }
        return list3;
    }

    @Override
    public List<TagsVo> getAllDraftTagsVo(Long draftId) {
        List<Tags> list = getAllTags();
        List<DraftTagsKey> list1 = getAllDraftTagsByDraftId(draftId);
        List<TagsVo> list3 = new ArrayList<>();

        for (Tags tags : list){
            boolean b=false;
            for (DraftTagsKey ta : list1){
                if(tags.getId() == ta.getTagsId()){
                    b=true;
                }
            }
            TagsVo tagsVo = new TagsVo();
            BeanUtils.copyProperties(tags,tagsVo);
            tagsVo.setDraftIsChecked(b);
            list3.add(tagsVo);
        }
        return list3;
    }

    @Override
    public void deleteTages(Integer id) {
        tagsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addTages(String tagsName) {
        Tags tags = new Tags();
        tags.setTagName(tagsName);
        tagsMapper.insert(tags);
    }
}
