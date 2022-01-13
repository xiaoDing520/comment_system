package com.yjxxt.comment.mapper;

import com.yjxxt.comment.base.BaseMapper;
import com.yjxxt.comment.bean.User;
import com.yjxxt.comment.query.UserQuery;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {
    User queryUserByUserName(String userName);
    List<User> selectByParams(UserQuery userQuery);
    List<User> selectByParams2(UserQuery userQuery);
    @MapKey("")
    public List<Map<String,Object>> queryAllUsers(Integer userId);
    int deleteById(Integer[] id);




}