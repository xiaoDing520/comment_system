package com.yjxxt.comment.mapper;

import com.yjxxt.comment.base.BaseMapper;
import com.yjxxt.comment.bean.Role;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;
import java.util.List;

public interface RoleMapper extends BaseMapper<Role,Integer> {
    //根据角色名查询角色信息
    Role selectRoleByName(String roleName);

    //查询角色列表
    @MapKey("")
    public List<Map<String,Object>> queryAllRoles(Integer userId);
}