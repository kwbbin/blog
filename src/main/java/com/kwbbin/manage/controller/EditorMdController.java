package com.kwbbin.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.kwbbin.bean.*;
import com.kwbbin.dao.*;
import com.kwbbin.manage.service.ArticleServiceImpl;
import com.kwbbin.util.FileUpload;
import com.kwbbin.util.RamdomNum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/manage")
//@PropertySource("classpath:localInfo.yml")
public class EditorMdController {

    //本网站地址
    @Value("${localInfoPath}")
    String localInfoPath;

    @Value("${imageUrl}")
    String imageUrl;

    @Autowired
    ArticleServiceImpl service;

    @Autowired
    DraftMapper draftMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagsArticleMapper tagsArticleMapper;


    @Autowired
    DraftTagsMapper draftTagsMapper;


    @RequestMapping("/uploadImage")
    @ResponseBody
    public JSONObject  uploadImage(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        StringBuffer str = FileUpload.FileUpload(file,imageUrl);
        // 设置图片位置
        StringBuffer sb = new StringBuffer();
        sb.append(localInfoPath).append(str);

        JSONObject res = new JSONObject();

        res.put("url",sb);
        res.put("success", 1);
        res.put("message", "upload success!");
        return res;
    }

    // 前端提交按钮把 editor.md 里的内容提交过来
    @RequestMapping("/save")
    public String save(@RequestParam(value = "my-editormd-markdown-doc") String data,@RequestParam("file")MultipartFile file,String localImage,String state,Long articleId, String category, Long draftId , String original, String title, String visibility,
                       HttpServletRequest request) {
        Enumeration<String> parm = request.getParameterNames();
        List<Integer> listTags = new ArrayList<>();
        while(parm.hasMoreElements()){
            String name=(String)parm.nextElement();
            String value=request.getParameter(name);
            if("on".equals(value)){
                listTags.add(Integer.parseInt(name));
            }

        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date time=null;
        try {
            time=df.parse(df.format(new Date()));// new Date()为获取当前系统时间
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        StringBuffer sb = FileUpload.FileUpload(file,imageUrl);

        StringBuffer str = new StringBuffer();
        str.append(localInfoPath).append(sb);

        Article article=new Article();
        try {
//            article.setLabels(tags);
            article.setArticleOrigin(Integer.parseInt(original));
            article.setArticleType(Integer.parseInt(category));
            article.setArticleWay(Integer.parseInt(visibility));
            if(!"".equals(localImage)&&localImage!=null){
                article.setImageurl(localImage);
            }
            article.setTitle(title);
            if(!"".equals(sb.toString())){
                article.setImageurl(str.toString());
            }
            article.setContent(data);
            article.setGood(0);
        }catch (Exception e){
            System.out.println("保存article数据错误:");
            System.out.println("article="+article);
        }


        //如果state=-1则该文章是草稿
        if("-1".equals(state)){
            Draft draft  = new Draft();
            article.setPostedTime(time);
            BeanUtils.copyProperties(article,draft);
            draftMapper.insert(draft);

            if(listTags.size()>0) {
//                DraftTagsExample draftTagsExample = new DraftTagsExample();
//                draftTagsExample.createCriteria().andDraftIdEqualTo(draftId);
//                draftTagsMapper.deleteByExample(draftTagsExample);

                for (Integer tId : listTags) {
                    try {
                        DraftTagsKey draftTagsKey = new DraftTagsKey();
                        draftTagsKey.setDraftId(draft.getId());
                        draftTagsKey.setTagsId(tId);
                        draftTagsMapper.insert(draftTagsKey);
                    } catch (Exception e) {
                        System.out.println("插入标签失败");
                    }

                }
            }

        }else if("-2".equals(state)) {
//            修改草稿
            Draft draft  = new Draft();
            article.setPostedTime(time);
            BeanUtils.copyProperties(article,draft);
            draft.setId(draftId);
            if((!"".equals(localImage))&&localImage!=null){
                draft.setImageurl(localImage);
            }
            DraftExample draftExample = new DraftExample();
            draftExample.createCriteria().andIdEqualTo(draftId);
            draftMapper.updateByPrimaryKeySelective(draft);

            if(listTags.size()>0) {
                DraftTagsExample draftTagsExample = new DraftTagsExample();
                draftTagsExample.createCriteria().andDraftIdEqualTo(draftId);
                draftTagsMapper.deleteByExample(draftTagsExample);

                for (Integer tId : listTags) {
                    try {
                        DraftTagsKey draftTagsKey = new DraftTagsKey();
                        draftTagsKey.setDraftId(draft.getId());
                        draftTagsKey.setTagsId(tId);
                        draftTagsMapper.insert(draftTagsKey);
                    } catch (Exception e) {
                        System.out.println("插入标签失败");
                    }

                }
            }

        }else if("-3".equals(state)) {
//           二次编辑文章
            article.setState(Byte.parseByte(0+""));
            article.setId(articleId);
            String url = articleMapper.selectByPrimaryKey(articleId).getImageurl();
            if ("".equals(localImage)&&"".equals(sb.toString())&&("".equals(url)||url==null)){
                article.setImageurl(localInfoPath+"/blog/file/default/default.png");
            }
            articleMapper.updateByPrimaryKeySelective(article);

            if(listTags.size()>0) {
                TagsArticleExample tagsArticleExample = new TagsArticleExample();
                tagsArticleExample.createCriteria().andArticleIdEqualTo(articleId);
                tagsArticleMapper.deleteByExample(tagsArticleExample);

                for (Integer tId : listTags) {
                    try {
                        TagsArticle ta = new TagsArticle();
                        ta.setArticleId(article.getId());
                        ta.setTagsId(tId);
                        tagsArticleMapper.insert(ta);
                    } catch (Exception e) {
                        System.out.println("插入标签失败");
                    }

                }
            }
        }else{
            if(!(draftId==null||"".equals(draftId))){
                if("".equals(sb.toString())){
                    String url = draftMapper.selectByPrimaryKey(draftId).getImageurl();
                    article.setImageurl(url);
                }
                if((!"".equals(localImage))&&localImage!=null){
                    article.setImageurl(localImage);
                }
                draftMapper.deleteByPrimaryKey(draftId);
            }

            article.setPostedTime(time);
            article.setState(Byte.parseByte(state));
            article.setVisits(0);
            article.setGuessYouLike(1);
            if ("".equals(article.getImageurl())||article.getImageurl()==null){
                article.setImageurl(localInfoPath+"/blog/file/default/default.png");
            }
            articleMapper.insert(article);


            if(listTags.size()>0){
                for (Integer tId :listTags){
                    try {
                        TagsArticle ta = new TagsArticle();
                        ta.setArticleId(article.getId());
                        ta.setTagsId(tId);
                        tagsArticleMapper.insert(ta);
                    }catch (Exception e){
                        System.out.println("插入标签失败");
                    }

                }
            }

        }

        return "redirect:/manage/AddArticle";
    }



}
