package com.yjxxt.comment.mapper;

import com.yjxxt.comment.base.BaseMapper;
import com.yjxxt.comment.bean.RoleMoudle;

import java.util.List;

public interface RoleMoudleMapper extends BaseMapper<RoleMoudle,Integer> {
    public List<Integer> selectByRoleId(Integer roleId);
    public int selectByCount(Integer roleId);
    public int deleteByRoleId(Integer roleId);
    public List<String> selectByRoleId2(Integer roleId);
}