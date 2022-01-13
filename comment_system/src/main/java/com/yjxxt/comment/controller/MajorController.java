package com.yjxxt.comment.controller;

import com.yjxxt.comment.base.BaseController;
import com.yjxxt.comment.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/majorController")
public class MajorController extends BaseController {

    @Autowired
    private MajorService majorService;

    /**
     * 查询专业列表
     */
    @RequestMapping("queryAllMajors")
    @ResponseBody
    public List<Map<String,Object>> queryAllMajors(Integer userId){
        return majorService.queryAllMajors(userId);
    }
}
