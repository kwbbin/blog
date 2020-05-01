package com.kwbbin.Vo;

import com.kwbbin.bean.ArticleType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArticleTypeVo extends ArticleType {
    boolean selected;
}
