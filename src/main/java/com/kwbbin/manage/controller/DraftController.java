package com.kwbbin.manage.controller;

import com.kwbbin.Vo.ArticleTypeVo;
import com.kwbbin.Vo.DraftVo;
import com.kwbbin.bean.ArticleType;
import com.kwbbin.bean.Draft;
import com.kwbbin.manage.service.ArticleServiceImpl;
import com.kwbbin.manage.service.DraftService;
import com.kwbbin.manage.service.TagsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class DraftController {

    @Autowired
    DraftService draftService;

    @Autowired
    ArticleServiceImpl articleServiceImpl;

    @Autowired
    TagsService tagsService;

    @RequestMapping("/getAllDraft")
    @ResponseBody
    public List<DraftVo> selectAllDraft(){
        return draftService.selectAllDraft();
    }

    @RequestMapping("/getDraftById")
    public ModelAndView selectDraftById(Long id, ModelAndView modelAndView){
        Draft draft = draftService.selectDraftById(id);
        List<ArticleType> listAT = articleServiceImpl.selectAll();
        List<ArticleTypeVo> listATVo = new ArrayList<>();
        for (ArticleType at : listAT){
            ArticleTypeVo atVo = new ArticleTypeVo();
            BeanUtils.copyProperties(at,atVo);
            if (at.getId()==draft.getArticleType()){
                atVo.setSelected(true);
            }
            listATVo.add(atVo);
        }
        modelAndView.setViewName("/manage/draft-detail");
        modelAndView.addObject("draft",draft);
        modelAndView.addObject("articleTypeVo",listATVo);
        modelAndView.addObject("draftId",id);
        modelAndView.addObject("tags",tagsService.getAllDraftTagsVo(id));

        return  modelAndView;
    }

    @RequestMapping("/deleteDraft")
    public ModelAndView deleteDraft(Long id,ModelAndView modelAndView){
        draftService.deleteDraftById(id);
        modelAndView.setViewName("redirect:/manage/draft");
        return modelAndView;
    }

}
