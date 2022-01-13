package com.yjxxt.comment.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.comment.base.BaseService;
import com.yjxxt.comment.bean.Subject;
import com.yjxxt.comment.mapper.SubjectMapper;
import com.yjxxt.comment.query.SubjectQuery;
import com.yjxxt.comment.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SubjectService extends BaseService<Subject,Integer> {
    @Autowired(required = false)
    private SubjectMapper subjectMapper;

    //默认查询课程信息
    public List<Map<String,Object>> selectSubjectAll(Integer userId) {
        return subjectMapper.selectSubjectAll(userId);
    }

    //学生查询课程信息
    public Map<String,Object> findSubject(SubjectQuery subjectQuery){
        //实例化Map
        Map<String,Object> map=new HashMap<String,Object>();
        //开启分页单位
        PageHelper.startPage(subjectQuery.getPage(),subjectQuery.getLimit());

        PageInfo<Subject> rlist=new PageInfo<>(selectByParams(subjectQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",rlist.getTotal());
        map.put("data",rlist.getList());
        return map;
    }

    public Map<String, Object> querySubjectByParams(SubjectQuery subjectQuery) {
        //实例
        Map<String, Object> map = new HashMap<String, Object>();
        //实例化分页单位
        PageHelper.startPage(subjectQuery.getPage(), subjectQuery.getLimit());
        //开始分页
        PageInfo<Subject> plist = new PageInfo<Subject>(subjectMapper.selectByParams2(subjectQuery));
        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", plist.getTotal());
        map.put("data", plist.getList());
        //返回map
        return map;

    }

    /**
     * 添加课程
     * 1. 参数校验
     *  用户名 非空 唯一性
     * 邮箱 非空
     *  手机号 非空 格式合法
     * 2. 设置默认参数
     *  isValid 1
     *  creteDate 当前时间
     *  updateDate 当前时间
     *  userPwd 123456 -> md5加密
     * 3. 执行添加，判断结果
     *
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void addSub(Subject subject){
        //参数校验
        checkParams(subject.getSubName(),subject.getUserName());
        //设置默认参数
        subject.setIsValid(1);
        subject.setCreateDate(new Date());

        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date); //需要将date数据转移到Calender对象中操作
        calendar.add(calendar.DATE, 7);//把日期往后增加n天.正数往后推,负数往前移动
        date=calendar.getTime();   //这个时间就是日期往后推一天的结果
        System.out.println(date);
        subject.setUpdateDate(date);
        subject.setState(1);
        //执行添加，判断结果
        AssertUtil.isTrue(subjectMapper.insertSelective(subject)<1,"用户添加失败");
    }

    /**
     * 参数校验
     * subName
     * userName
     *
     */
    private void checkParams(String subName, String userName) {
        AssertUtil.isTrue(StringUtils.isBlank(subName),"课程不能为空");
        //验证课程名是否存在
        Subject temp=subjectMapper.querySubByUserName(subName);
        AssertUtil.isTrue(null!=temp,"该课程已存在");
        AssertUtil.isTrue(StringUtils.isBlank(subName),"请输入课程名称");
    }

    //修改课程信息
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeSub(Subject subject) {
        //根据ID获取课程信息
        Subject temp = (Subject) subjectMapper.selectByPrimaryKey(subject.getId());
        //判断
        AssertUtil.isTrue(temp==null,"待修改的记录不存在");
        //判断修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(subject)<1,"修改失败");
    }



    private void checkSubjectParam(String subName, Integer userId) {
        AssertUtil.isTrue(StringUtils.isBlank(subName),"请输入科目名称");
        AssertUtil.isTrue(userId==null,"请输入教师编号");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void evaluateSubject(Subject subject){
        //验证
        Subject temp = selectByPrimaryKey(subject.getId());
        AssertUtil.isTrue(temp==null,"待评价科目不存在");
        checkSubjectParam(subject.getSubName(),subject.getUserId());
        //未评价
        if (temp.getState()==0){
            subject.setState(1);
        }
        //已评价
        if (temp.getState()!=0){

            //删除评价按钮
            //用”查看按钮”替换”评价按钮“
            //只能查看，不能修改

        }

        //评价是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(subject)<1,"评价失败");
    }
}
