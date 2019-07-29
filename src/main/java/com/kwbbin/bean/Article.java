package com.kwbbin.bean;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Article {
    private Long id;

    private String labels;

    private Integer articleOrigin;

    private Integer articleType;

    private Integer articleWay;

    private Integer state;

    private String title;

    private Date postedTime;

    private Integer good;

    private String content;

}