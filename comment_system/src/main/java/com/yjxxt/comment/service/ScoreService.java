package com.yjxxt.comment.service;

import com.yjxxt.comment.base.BaseService;
import com.yjxxt.comment.bean.Score;
import com.yjxxt.comment.mapper.ScoreMapper;
import com.yjxxt.comment.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService extends BaseService<ScoreService,Integer> {
    @Resource
    private ScoreMapper scoreMapper;

    //分数添加
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Score score){
        //判断添加是否成功
        AssertUtil.isTrue(scoreMapper.insertScore(score)<1,"添加失败");
    }

    public Map<String, Object> findScores(Score score) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Score> slist = scoreMapper.selectScores(score.getTeacherId());
        map.put("code",0);
        map.put("msg","success");
        map.put("count",slist.size());
        map.put("data", slist);
        return map;
    }
}
