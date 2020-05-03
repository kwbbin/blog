package com.kwbbin.bean;

import lombok.ToString;

import java.util.Date;

@ToString
public class Article {
    private Long id;

    private String labels;

    private Integer articleOrigin;

    private Integer articleType;

    private Integer articleWay;

    private String title;

    private Date postedTime;

    private Integer good;

    private Byte state;

    private String imageurl;

    private Integer visits;

    private Integer guessYouLike;

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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getGuessYouLike() {
        return guessYouLike;
    }

    public void setGuessYouLike(Integer guessYouLike) {
        this.guessYouLike = guessYouLike;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}