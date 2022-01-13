package com.yjxxt.comment.bean;

public class UserSubject {
    private Integer id;

    private Integer userId;

    private Integer subId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    @Override
    public String toString() {
        return "UserSubject{" +
                "id=" + id +
                ", userId=" + userId +
                ", subId=" + subId +
                '}';
    }
}