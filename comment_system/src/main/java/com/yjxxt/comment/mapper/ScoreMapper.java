package com.yjxxt.comment.mapper;

import com.yjxxt.comment.base.BaseMapper;
import com.yjxxt.comment.bean.Score;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface ScoreMapper extends BaseMapper<Score,Integer> {
    int insertScore(Score score);
    List<Score> selectScores(Integer teacherId);
}