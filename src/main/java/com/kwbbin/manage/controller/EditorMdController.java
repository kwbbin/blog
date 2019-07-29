package com.kwbbin.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.kwbbin.bean.Article;
import com.kwbbin.manage.service.ArticleService;
import com.kwbbin.util.RamdomNum;
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
import java.util.Date;

@Controller
@RequestMapping("/manage")
@PropertySource("classpath:localInfo.yml")
public class EditorMdController {

    //本网站地址
    @Value("${localInfoPath}")
    String localInfoPath;

    @Autowired
    ArticleService service;


    @RequestMapping("/uploadImage")
    @ResponseBody
    public JSONObject  uploadImage(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String trueFileName = file.getOriginalFilename();

        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

        String fileName = System.currentTimeMillis()+"_"+ RamdomNum.getIntegerNum()+suffix;

        String path = request.getSession().getServletContext().getRealPath("/static/manage/upload/");


        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 设置图片位置
        StringBuffer sb = new StringBuffer();
        sb.append(localInfoPath+"/static/manage/upload/");
        sb.append(fileName);

        JSONObject res = new JSONObject();

        res.put("url",sb);
        res.put("success", 1);
        res.put("message", "upload success!");
        return res;
    }

    // 前端提交按钮把 editor.md 里的内容提交过来
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam(value = "my-editormd-markdown-doc") String data,String state, String category, String tags, String original, String title, String visibility,
                       HttpServletRequest request) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date time=null;
        try {
            time=df.parse(df.format(new Date()));// new Date()为获取当前系统时间
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Article article=new Article();
        if(original!=null){
            try {
                article.setLabels(tags);
                article.setArticleOrigin(Integer.parseInt(original));
                article.setArticleType(Integer.parseInt(category));
                article.setArticleWay(Integer.parseInt(visibility));
                article.setTitle(title);
                article.setContent(data);
                article.setPostedTime(time);
                article.setGood(0);
                article.setState(Integer.parseInt(state));
            }catch (Exception e){
                System.out.println("保存article数据错误:");
                System.out.println("article="+article);
            }

        }

        if(article.getState()==1&&article.getId()!=null&&article.getId()!=0){

        }

        int num=service.insert(article);
        System.out.println("num="+num);
        return "success";
    }


    @RequestMapping("/show")
    public String show(Model model) {
        model.addAttribute("str","##你好\n" +
                "![](http://localhost:8080/static/manage/upload/1564111721134_274.gif)\n" +
                "\n" +
                "好看\n" +
                "null");
        return "manage/show-test";
    }

}
