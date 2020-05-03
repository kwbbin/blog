package com.kwbbin.manage.service;

import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.Vo.DraftVo;
import com.kwbbin.bean.*;
import com.kwbbin.dao.ArticleTypeMapper;
import com.kwbbin.dao.DraftMapper;
import com.kwbbin.dao.DraftTagsMapper;
import com.kwbbin.util.ArticleUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DraftServiceImpl implements  DraftService {
    @Autowired
    DraftMapper draftMapper;

    @Autowired
    ArticleTypeMapper articleTypeMapper;

    @Autowired
    DraftTagsMapper draftTagsMapper ;

    @Override
    public List<DraftVo> selectAllDraft() {
        List<Draft> list = draftMapper.selectByExampleWithBLOBs(new DraftExample());
        List<DraftVo> draftList = new ArrayList<>();
        for (Draft draft : list){
            Article article = new Article();
            BeanUtils.copyProperties(draft,article);
            ArticleVo articleVo = ArticleUtil.alterArticle(article,articleTypeMapper);
            DraftVo draftVo = new DraftVo();
            BeanUtils.copyProperties(articleVo,draftVo);
            if (draftVo.getContent().length()>60)
            draftVo.setContent(articleVo.getContent().substring(0,60));
            draftList.add(draftVo);
        }
        return draftList;
    }



    @Override
    public Draft selectDraftById(Long id) {
        return draftMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteDraftById(Long id) {
        DraftTagsExample draftTagsExample = new DraftTagsExample();
        draftTagsExample.createCriteria().andDraftIdEqualTo(id);
        draftTagsMapper.deleteByExample(draftTagsExample);
        draftMapper.deleteByPrimaryKey(id);
    }
}
