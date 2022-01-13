package com.yjxxt.comment.mapper;

import com.yjxxt.comment.base.BaseMapper;
import com.yjxxt.comment.bean.Moudle;
import com.yjxxt.comment.dto.TreeDto;

import java.util.List;

public interface MoudleMapper extends BaseMapper<Moudle,Integer> {
    public List<Moudle> selectQuery();
    List<TreeDto> selectMoudles();
//    public int selectParentOptValues();
}