package com.yjxxt.comment.service;

import com.yjxxt.comment.base.BaseService;
import com.yjxxt.comment.bean.RoleMoudle;
import com.yjxxt.comment.mapper.RoleMoudleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMoudleService extends BaseService<RoleMoudle,Integer> {
    @Autowired(required = false)
    private RoleMoudleMapper roleMoudleMapper;

    public List<Integer> selectByRoleId(Integer roleId) {
        return roleMoudleMapper.selectByRoleId(roleId);
    }

    public List<String> selectByRoleId2(Integer roleId) {
        return roleMoudleMapper.selectByRoleId2(roleId);
    }
}
