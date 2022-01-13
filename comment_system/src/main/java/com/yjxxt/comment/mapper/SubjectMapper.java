package com.yjxxt.comment.mapper;

import com.yjxxt.comment.base.BaseMapper;
import com.yjxxt.comment.bean.Subject;
import com.yjxxt.comment.query.SubjectQuery;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface SubjectMapper extends BaseMapper {

    @MapKey("")
    public List<Map<String,Object>> selectSubjectAll(Integer userId);
    @MapKey("")
    Subject querySubByUserName(String userName);
    int endSub(Integer[] ids);

    List<Subject> selectByParams2(SubjectQuery subjectQuery);
}