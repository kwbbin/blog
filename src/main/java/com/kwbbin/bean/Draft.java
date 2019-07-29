package com.kwbbin.bean;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Draft {
    private Integer id;

    private String labels;

    private Integer articleOrigin;

    private Integer articleType;

    private Integer articleWay;

    private String title;

    private Date postedTime;

    private Integer good;

    private String content;


}