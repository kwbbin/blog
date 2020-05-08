package com.kwbbin.bean;

import java.util.Date;

public class MyMessage {
    private Long id;

    private String content;

    private Integer good;

    private Date responsetime;

    private Long answertomessage;

    private String name;

    private Long articleid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Date getResponsetime() {
        return responsetime;
    }

    public void setResponsetime(Date responsetime) {
        this.responsetime = responsetime;
    }

    public Long getAnswertomessage() {
        return answertomessage;
    }

    public void setAnswertomessage(Long answertomessage) {
        this.answertomessage = answertomessage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }
}