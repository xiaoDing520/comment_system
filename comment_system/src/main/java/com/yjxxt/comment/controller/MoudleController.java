package com.yjxxt.comment.controller;

import com.yjxxt.comment.base.BaseController;
import com.yjxxt.comment.dto.TreeDto;
import com.yjxxt.comment.service.MoudleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.List;

/**
 * @ClassName MoudleController
 * @Desc 资源控制类
 * @Author xiaoding
 * @Date 2022-01-07 11:36
 * @Version 1.0
 */
@Controller
@RequestMapping("/moudleController")
public class MoudleController extends BaseController {
    @Autowired(required = false)
    private MoudleService moudleService;

    @RequestMapping("/index")
    public String index() {
        return "moudle/moudle";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> selectQuery() {
        //调用查询的方法
        return moudleService.selectQuery();
    }

    @PostMapping("/findModules")
    @ResponseBody
    public List<TreeDto> findModules(Integer roleId) {
        moudleService.findModules(roleId).forEach(System.out::println);
        return moudleService.findModules(roleId);
    }
}
