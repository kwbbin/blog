package com.kwbbin.bean;

import java.util.Date;

public class Draft {
    private Long id;

    private String labels;

    private Integer articleOrigin;

    private Integer articleType;

    private Integer articleWay;

    private String title;

    private Date postedTime;

    private Integer good;

    private Integer state;

    private Long draftnum;

    private String imageurl;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getDraftnum() {
        return draftnum;
    }

    public void setDraftnum(Long draftnum) {
        this.draftnum = draftnum;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}