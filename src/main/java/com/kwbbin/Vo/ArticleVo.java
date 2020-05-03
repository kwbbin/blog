package com.kwbbin.Vo;

import com.kwbbin.bean.Article;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ArticleVo extends Article {
    String articleOriginStr;
    String articleWayStr;
    String articleTypeStr;
    Long dateLong;
}
