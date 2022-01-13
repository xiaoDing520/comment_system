package com.yjxxt.comment.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.comment.base.BaseService;
import com.yjxxt.comment.bean.Subject;
import com.yjxxt.comment.bean.User;
import com.yjxxt.comment.bean.UserSubject;
import com.yjxxt.comment.mapper.SubjectMapper;
import com.yjxxt.comment.mapper.UserMapper;
import com.yjxxt.comment.mapper.UserSubjectMapper;
import com.yjxxt.comment.model.UserModel;
import com.yjxxt.comment.query.UserQuery;
import com.yjxxt.comment.utils.AssertUtil;
import com.yjxxt.comment.utils.Md5Util;
import com.yjxxt.comment.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    private UserMapper userMapper;
    @Autowired(required = false)
    private SubjectMapper subjectMapper;
    @Autowired(required = false)
    private UserSubjectMapper userSubjectMapper;

    //登录
    public UserModel userLogin(String userName,String userPwd){
        //验证参数
        checkLoginParams(userName,userPwd);

        //根据用户名查询用户对象
        User user = userMapper.queryUserByUserName(userName);

        //判断用户是否存在
        AssertUtil.isTrue(user==null,"用户不存在");

        //用户存在，校验密码
        checkLoginPwd(user.getUserPwd(),userPwd);

        //密码正确
        return buildUserInfo(user);
    }

    //设置返回值
    private UserModel buildUserInfo(User user) {
        UserModel userModel=new UserModel();
        //设置用户信息
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        return userModel;
    }

    //判断原密码和新密码是否一致
    private void checkLoginPwd(String uPwd, String userPwd) {
        //对密码加密
        userPwd= Md5Util.encode(userPwd);
         //判断2个密码是否相等
        AssertUtil.isTrue(!uPwd.equals(userPwd),"密码不正确");
    }

    //登录密码校验
    public void checkLoginParams(String userName,String userPwd){
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码不能为空");
    }

    //修改密码
    public void updateUserPassword(Integer uid, String oldPassword, String newPassWord, String confirmPassword) {
        //通过uid获取用户信息
        User user=userMapper.selectByPrimaryKey(uid);
        System.out.println(user);
        //参数校验
        checkPasswordParams(user,oldPassword,newPassWord,confirmPassword);
        //修改密码
        user.setUserPwd(Md5Util.encode(newPassWord));
        //密码是否修改成功
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"修改失败");
    }

    //修改密码校验
    private void checkPasswordParams(User user, String oldPassword, String newPassWord, String confirmPassword) {
        //判断用户是否登录
        AssertUtil.isTrue(user==null,"未登录");
        //判断原始密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"请输入原始密码");
        //判断新密码和加密后的原始密码是否一致
        AssertUtil.isTrue(Md5Util.encode(newPassWord).equals(user.getUserPwd()),"新密码和原始密码一致");
        //判断新密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(newPassWord),"新密码不能为空");
        //确认密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"确认密码不能为空");
        //新密码和确认密码是否一致
        AssertUtil.isTrue(!newPassWord.equals(confirmPassword),"新密码与确认密码不一致");
    }

    //注册：添加用户
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        //验证
        checkParams(user.getUserName(),user.getSex(),user.getAge());

        //设定默认值
        user.setIsValid(1);
        user.setRoleId(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //密码加密
        user.setUserPwd(Md5Util.encode("123456"));
        //判断是否成功
        AssertUtil.isTrue(userMapper.insertSelective(user)<1,"注册失败");
    }

    //注册数据校验
    public void checkParams(String name,String sex,Integer age){
        User temp = userMapper.queryUserByUserName(name);
        AssertUtil.isTrue(temp!=null,"用户已经存在");
        AssertUtil.isTrue(StringUtils.isBlank(name),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(sex),"性别不能为空");
        AssertUtil.isTrue(age==0,"年龄不能为空");
    }

    /**
     * 用户模块的列表查询
     * 条件
     *
     * @param query
     * @return
     */
    public Map<String, Object> queryUserByParams(UserQuery query) {
        //创建map对象
        Map<String, Object> map = new HashMap<>();
        //初始化分页单位
        PageHelper.startPage(query.getPage(), query.getLimit());
        //开始分页
        PageInfo<User> plist = new PageInfo<User>(userMapper.selectByParams(query));
        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        //统计sql语句
        map.put("count", plist.getTotal());
        //实现分页
        map.put("data", plist.getList());
        plist.getList().forEach(System.out::println);
        //返回map
        return map;
    }

    public Map<String, Object> queryUserByParams2(UserQuery query) {
        //创建map对象
        Map<String, Object> map = new HashMap<>();
        //初始化分页单位
        PageHelper.startPage(query.getPage(), query.getLimit());
        //开始分页
        PageInfo<User> plist = new PageInfo<User>(userMapper.selectByParams2(query));
        System.out.println(plist.getList());
        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        //统计sql语句
        map.put("count", plist.getTotal());
        //实现分页
        map.put("data", plist.getList());
        //返回map
        return map;

    }


    /**
     * 验证：
     * 1，用户名非空，且唯一
     * 2，性别非空
     * 3，年龄非空
     * 4，课程非空
     *
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        //判断角色是否为空
        AssertUtil.isTrue(user.getRoleId() == null, "请选择角色");
        //参数校验
        checkUser(user.getId(), user.getUserName(), user.getSex(), user.getAge(),user.getSubId(), user.getMajorId(),user.getRoleId());
        //设定默认
        user.setRoleId(1);
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        //密码加密
        user.setUserPwd(Md5Util.encode("123456"));
        //验证是否成功
        AssertUtil.isTrue(insertHasKey(user) < 1, "添加失败了");
        //判断添加的角色身份
        if (user.getRoleId() == 1) {
            addOrUpdate(user.getId(),user.getSubId());
        }
    }

    //添加和修改数据校验
    private void checkUser(Integer id, String userName, String sex, Integer age, Integer[] subId, Integer majorId,Integer roleId) {
        AssertUtil.isTrue(StringUtils.isBlank(String.valueOf(id)), "用户编号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空");
        //用户名唯一
        User temp = userMapper.queryUserByUserName(userName);
        if (id==null){
            AssertUtil.isTrue(temp != null, "用户名已经存在");
        }else {
            AssertUtil.isTrue(temp != null && !temp.getId().equals(id), "用户名已经存在");
        }
        AssertUtil.isTrue(StringUtils.isBlank(sex), "性别不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(String.valueOf(age)), "年龄不能为空");
        if (roleId == 1) {
            AssertUtil.isTrue(subId == null,"课程不能为空");
        }
        AssertUtil.isTrue(StringUtils.isBlank(String.valueOf(majorId)), "专业编号不能为空");
    }

    //修改用户信息
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeUser(User user){
        //根据ID获取用户的信息
        User temp = userMapper.selectByPrimaryKey(user.getId());
        //判断
        AssertUtil.isTrue(temp==null,"待修改的记录不存在");
        //判断角色是否为空
        AssertUtil.isTrue(user.getRoleId() == null, "请选择角色");
        //验证参数
        checkUser(user.getId(), user.getUserName(), user.getSex(), user.getAge(),user.getSubId(), user.getMajorId(),user.getRoleId());
        //设定默认值
        user.setUpdateDate(new Date());
        //判断修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(user)<1,"修改失败了");
        //判断添加的角色身份
        if (user.getRoleId() == 1) {
            addOrUpdate(user.getId(),user.getSubId());
        }
    }

    //添加和修改课程中间表
    private void addOrUpdate(Integer userId,Integer[] subId) {
        //判断用户id是否为空
        AssertUtil.isTrue(userId == null,"请输入参数");
        //判断课程数据是否为空
        AssertUtil.isTrue(subId == null || subId.length == 0,"请选择用户的课程");
        //根据用户id查询，用户所拥有的课程数量
        int count = userSubjectMapper.selectByUserId(userId);
        //判断是否要删除课程
        if (count > 0) {
            AssertUtil.isTrue(userSubjectMapper.deleteByUserId(userId) != count,"添加失败");
        }
        //创建List集合
        List<UserSubject> list = new ArrayList<>();
        //循环遍历数组
        for (int i = 0;i<subId.length;i++) {
            //创建用户_课程中间表对象
            UserSubject userSubject = new UserSubject();
            //设置值
            userSubject.setUserId(userId);
            userSubject.setSubId(subId[i]);
            //添加数据
            list.add(userSubject);
        }
        //判断是否添加成功
        AssertUtil.isTrue(userSubjectMapper.addUserSubject(list) != subId.length,"添加失败");
    }

    /**
     * 删除用户
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserByIds(Integer[] ids){
        AssertUtil.isTrue(null==ids,"请选择待删除的用户记录");
        System.out.println(ids);
        //遍历
        for (Integer userId:ids) {
            //统计当前用户有几门课程
            int count=userSubjectMapper.selectByUserId(userId);
            if(count>0){
                //删除原有数据
                AssertUtil.isTrue(userSubjectMapper.deleteByUserId(userId)!=count,"用户记录删除失败");
            }
        }
        AssertUtil.isTrue(userMapper.deleteById(ids)!=ids.length,"用户记录删除失败");
    }

    //根据用户id查询,所有角色
    public List<Map<String, Object>> queryAllUsers(Integer userId){
        return userMapper.queryAllUsers(userId);
    }

    /*添加分配*/
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(Subject subject){
        //用户角色分配
        relationUser(subject.getId(),subject.getUserIds());

    }

    /*修改分配*/
    public void updateUser(Subject subject){
        Integer subjectId=subjectMapper.querySubByUserName(subject.getSubName()).getId();
        relationUser(subjectId,subject.getUserIds());
    }

    /**
     * 用户角色分配
     * 原始角色不存在 添加新的角色记录
     * 原始角色存在 添加新的角色记录
     * 原始角色存在 清空所有角色
     * 原始角色存在 移除部分角色
     * 如何进行角色分配???
     * 如果用户原始角色存在 首先清空原始所有角色 添加新的角色记录到用户角色表
     */

    private void relationUser(Integer subId, String userIds) {
        int count=userSubjectMapper.countUserSubByUserId(subId);
        if (count>0){
            AssertUtil.isTrue(userSubjectMapper.deletUserSubByUserId(subId)!=count,"用户角色分配失败");
        }
        AssertUtil.isTrue(Integer.parseInt(userIds)>1,"只能分配一位老师");
        if (StringUtils.isNotBlank(userIds)){
            //重新添加新的
            List<UserSubject> userSubjectKeys=new ArrayList<>();
            for (String s:userIds.split(",")){
                UserSubject userSubject =new UserSubject();
                userSubject.setUserId(Integer.parseInt(s));
                userSubject.setSubId(subId);
                userSubjectKeys.add(userSubject);
            }
            AssertUtil.isTrue(userSubjectMapper.insertBatch(userSubjectKeys)<userSubjectKeys.size(),"课程分配失败");
        }
    }



    /*删除*/
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSubByIds(Integer[] ids) {
        AssertUtil.isTrue(null==ids || ids.length == 0,"请选择待删除的课程记录!");
        AssertUtil.isTrue(deleteBatch(ids) != ids.length,"课程记录删除失败!");
    }


    /*结束评价*/

    @Transactional(propagation = Propagation.REQUIRED)
    public void endSubByIds(Integer[] ids) {
       /* List<Subject> subjects=new ArrayList<Subject>();
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择待结束评价的课程");
        for (Integer sid:ids) {
            Subject subject = subjectMapper.selectByPrimaryKey(sid);
            System.out.println(subject.getUserId()+"userId");
            subject.setUpdateDate(new Date());
            subjects.add(subject);
        }
        System.out.println(subjects);
        AssertUtil.isTrue(subjectMapper.endSub((Integer[]) subjects.toArray()).<subjects.size(),"结束评价课程失败");*/
        AssertUtil.isTrue(ids == null || ids.length == 0, "请选择待结束评价的课程");
        AssertUtil.isTrue(subjectMapper.endSub(ids)!=ids.length,"结束评价未成功！");
    }

//    public void endSub(Subject subject) {
//        AssertUtil.isTrue(subject.getId()==null || selectByPrimaryKey(subject.getId())==null,"请选择要结束的数据");
//        subject.setState(0);
//        subject.setUpdateDate(new Date());
//        AssertUtil.isTrue(subjectMapper.updateByPrimaryKeySelective(subject)<1,"结束评价未成功");
//    }
}
