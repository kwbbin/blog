package com.kwbbin.bean;

import java.util.Date;

public class Message {
    private Long id;

    private Integer userid;

    private Integer good;

    private Date responsetime;

    private Integer answertouser;

    private Long answertomessage;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getAnswertouser() {
        return answertouser;
    }

    public void setAnswertouser(Integer answertouser) {
        this.answertouser = answertouser;
    }

    public Long getAnswertomessage() {
        return answertomessage;
    }

    public void setAnswertomessage(Long answertomessage) {
        this.answertomessage = answertomessage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}