package com.kwbbin.bean;

import java.util.Date;

public class Article {
    private Long id;

    private String labels;

    private Integer articleOrigin;

    private Integer articleType;

    private Integer articleWay;

    private Integer isDraft;

    private String title;

    private Date postedTime;

    private Integer good;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels == null ? null : labels.trim();
    }

    public Integer getArticleOrigin() {
        return articleOrigin;
    }

    public void setArticleOrigin(Integer articleOrigin) {
        this.articleOrigin = articleOrigin;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public Integer getArticleWay() {
        return articleWay;
    }

    public void setArticleWay(Integer articleWay) {
        this.articleWay = articleWay;
    }

    public Integer getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Integer isDraft) {
        this.isDraft = isDraft;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(Date postedTime) {
        this.postedTime = postedTime;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}