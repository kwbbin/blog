package com.kwbbin.manage.service;

import com.github.pagehelper.PageInfo;
import com.kwbbin.Vo.ArticleVo;
import com.kwbbin.bean.Article;
import com.kwbbin.bean.LocalImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    List<ArticleVo> selectAllArticle();
    PageInfo selectAllArticle(Integer pageNum, Integer size);
    Article getArticleById(Long id);
    void updateArticleById(Article article);
    void deleteArticleById(Long id);
    List<ArticleVo> searchArticleByCondition(String str,Integer range);
    List<ArticleVo> getAllGuessLike();
    Integer addArticleToGuessLike(String id);
    void cancelArticleToGuessLike(Long id);
    List<LocalImage> getAllLocalImage();
    void deleteLocalImageById(String ids);
    void saveLocalImage(MultipartFile file, String name);
}
