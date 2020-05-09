package com.kwbbin.bean;

public class BlogBasic {
    private Integer id;

    private Integer visits;

    private String indexMotto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public String getIndexMotto() {
        return indexMotto;
    }

    public void setIndexMotto(String indexMotto) {
        this.indexMotto = indexMotto == null ? null : indexMotto.trim();
    }
}