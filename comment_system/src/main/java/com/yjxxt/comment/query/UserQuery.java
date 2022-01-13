package com.yjxxt.comment.query;

import com.yjxxt.comment.base.BaseQuery;

public class UserQuery extends BaseQuery {
    //用户名
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "userName='" + userName + '\'' +
                '}';
    }
}