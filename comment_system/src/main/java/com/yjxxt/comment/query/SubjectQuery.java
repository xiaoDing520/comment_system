package com.yjxxt.comment.query;

import com.yjxxt.comment.base.BaseQuery;

public class SubjectQuery extends BaseQuery {
    private String userName;
    private Integer userId;
    private Integer subName;
    private Integer state;

    public SubjectQuery() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubName() {
        return subName;
    }

    public void setSubName(Integer subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "SubjectQuery{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", subName=" + subName +
                ", state=" + state +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
