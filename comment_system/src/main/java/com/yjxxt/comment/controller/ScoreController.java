package com.yjxxt.comment.controller;

import com.yjxxt.comment.base.BaseController;
import com.yjxxt.comment.base.ResultInfo;
import com.yjxxt.comment.bean.Score;
import com.yjxxt.comment.service.ScoreService;
import com.yjxxt.comment.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("scoreController")
public class ScoreController extends BaseController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/add")
    @ResponseBody
    public ResultInfo save(HttpServletRequest request, Score score){
        System.out.println("score = " + score);
        //根据cookie获取id
//        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //设置用户id
//        score.setUserId(userId);
//        score.setId(20);
        scoreService.add(score);
        return success("评价成功");
    }

    @RequestMapping("index")
    public String index(){
        return "score/score";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(Score score){
        return scoreService.findScores(score);
    }

}
