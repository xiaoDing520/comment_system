package com.yjxxt.comment.service;

import com.yjxxt.comment.base.BaseService;
import com.yjxxt.comment.bean.Major;
import com.yjxxt.comment.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MajorService extends BaseService<Major,Integer> {
    /**
     * 查询专业名称
     */
    @Autowired(required = false)
    private MajorMapper majorMapper;

    //根据用户id查询全部专业
    public List<Map<String,Object>> queryAllMajors(Integer UserId){
        return majorMapper.queryAllMajors(UserId);
    }
}

