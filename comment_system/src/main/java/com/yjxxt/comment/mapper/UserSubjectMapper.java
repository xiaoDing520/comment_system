package com.yjxxt.comment.mapper;

import com.yjxxt.comment.base.BaseMapper;
import com.yjxxt.comment.bean.UserSubject;

import java.util.List;

public interface UserSubjectMapper extends BaseMapper<UserSubject,Integer> {
    public int addUserSubject(List<UserSubject> list);
    public int selectByUserId(Integer userId);
    int countUserSubByUserId(Integer userId);
    int deletUserSubByUserId(Integer userId);
    public int deleteByUserId(Integer userId);
}