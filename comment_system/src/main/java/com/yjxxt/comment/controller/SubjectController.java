package com.yjxxt.comment.controller;

import com.yjxxt.comment.base.BaseController;
import com.yjxxt.comment.base.ResultInfo;
import com.yjxxt.comment.bean.Subject;
import com.yjxxt.comment.query.SubjectQuery;
import com.yjxxt.comment.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/subjectController")
public class SubjectController extends BaseController {
    @Autowired(required = false)
    private SubjectService subjectService;

    //根据用户id查询课程
    @PostMapping("/queryAllSubject")
    @ResponseBody
    public List<Map<String,Object>> queryAllSubject(Integer userId) {
        return subjectService.selectSubjectAll(userId);
    }

    /*查询*/
    @RequestMapping("su")
    @ResponseBody
    public Map<String,Object> subject(SubjectQuery subjectQuery){

        return subjectService.findSubject(subjectQuery);
    }


    //进入首页
    @RequestMapping("in")
    public String in(){
        return "subject/subject";
    }



    /*添加1*/
    @RequestMapping("add")
    public String add(Integer id, Model model){
        if (id!=null){
            Subject subject=subjectService.selectByPrimaryKey(id);
            model.addAttribute("subject",subject);
        }
        return "subject/add";
    }

    /*关闭加载层*/
    @RequestMapping("index")
    public String index() {
        return "subject/subject";
    }



    /*添加2*/
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Subject subject) {
        System.out.println(subject);
        //课程的添加
        subjectService.addSub(subject);
        //返回目标数据对象
        return success("课程添加OK");
    }


    /*修改*/
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Subject subject) {
        //课程的修改
        subjectService.changeSub(subject);
        //返回目标数据对象
        return success("课程修改OK");
    }

    @RequestMapping("/student")
    public String student(){
        return "student/student";
    }

    @RequestMapping("evaluateSubject")
    @ResponseBody
    public String evaluateSubject(Subject subject){
        //添加操作
        subjectService.evaluateSubject(subject);
        //返回目标对象
        return "修改成功";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> saylist(SubjectQuery subjectQuery){
        //调用方法获取数据
        Map<String,Object> map = subjectService.querySubjectByParams(subjectQuery);
        System.out.println(map);
        //返回map
        return map;
    }

    @RequestMapping("addSubject")
    public String addSubject(Integer id, Model model){
        Subject subject = subjectService.selectByPrimaryKey(id);
        model.addAttribute("subject",subject);
        return "student/add";
    }
}
