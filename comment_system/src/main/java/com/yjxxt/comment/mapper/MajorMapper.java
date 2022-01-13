package com.yjxxt.comment.mapper;

import com.yjxxt.comment.base.BaseMapper;
import com.yjxxt.comment.bean.Major;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


public interface MajorMapper extends BaseMapper<Major,Integer> {
    //查询专业列表
    @MapKey("")
    public List<Map<String,Object>> queryAllMajors(Integer userId);




}