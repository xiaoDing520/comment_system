package com.yjxxt.comment.controller;


import com.yjxxt.comment.base.BaseController;
import com.yjxxt.comment.base.ResultInfo;
import com.yjxxt.comment.bean.User;
import com.yjxxt.comment.model.UserModel;
import com.yjxxt.comment.query.UserQuery;
import com.yjxxt.comment.service.UserService;
import com.yjxxt.comment.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("userController")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    //进入首页
    @RequestMapping("/index")
    public String index(){
        return "user/user";
    }

    //登录
    @ResponseBody
    @RequestMapping("login")
    public ResultInfo  login(String userName,String userPwd){

        ResultInfo resultInfo=new ResultInfo();

        //获取返回的对象
        UserModel userModel=userService.userLogin(userName,userPwd);
        //将返回的UserModel对象设置到ResultInfo对象中
        resultInfo.setResult(userModel);

        return resultInfo;
    }


    //跳转修改密码
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }

    //修改密码
    @ResponseBody
    @PostMapping("update")
    public ResultInfo updateUserPassWord(HttpServletRequest request,String oldPassWord,String newPassWord,String confirmPassWord){
        System.out.println("oldPassword = " + oldPassWord);
        ResultInfo resultInfo=new ResultInfo();
        //获取id
        Integer uid = LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println("uid = " + uid);
        userService.updateUserPassword(uid,oldPassWord,newPassWord,confirmPassWord);
        return resultInfo;
    }

    //基本信息
    @RequestMapping("/toSettingPage")
    public String setting(HttpServletRequest request){
        //获取id
        int uid=LoginUserUtil.releaseUserIdFromCookie(request);
        //通过id获取资料
        User user = userService.selectByPrimaryKey(uid);
        //存储
        request.setAttribute("user",user);
        return "user/setting";
    }

    //基本信息修改
    @RequestMapping("setting")
    @ResponseBody
    public ResultInfo sayUpdate(User user){
        ResultInfo resultInfo=new ResultInfo();
        userService.updateByPrimaryKeySelective(user);
        return resultInfo;
    }


    //默认查询
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(UserQuery userQuery){
        return userService.queryUserByParams(userQuery);
    }

    //查询条件2
    @RequestMapping("/list2")
    @ResponseBody
    public Map<String,Object> list2(UserQuery userQuery){
        return userService.queryUserByParams2(userQuery);
    }

    //添加用户
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(User user){
        //用户的添加
        userService.addUser(user);
        //返回目标数据对象
        return success("用户添加OK");
    }

    //修改用户
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(User user){
        //用户的添加
        userService.changeUser(user);
        //返回目标数据对象
        return success("用户修改OK");
    }

    //添加和修改页面访问
    @RequestMapping("/addOrUpdateUserPage")
    public String addUserPage(Integer id , Model model){
        if (null !=id){
            model.addAttribute("user",userService.selectByPrimaryKey(id));
        }
        return "user/add_update";
    }

    //删除用户
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteUserByIds(ids);
        return success("用户记录删除成功");
    }

    @RequestMapping("users")
    @ResponseBody
    public List<Map<String,Object>> queryUsers(Integer userId){
        return userService.queryAllUsers(userId);
    }

    @RequestMapping("deleteSubByIds")
    @ResponseBody
    public ResultInfo deleteSubByIds(Integer[] ids){
        userService.deleteSubByIds(ids);
        return success("课程记录删除成功");
    }

    //课程评论结束
    @RequestMapping("end")
    @ResponseBody
    public ResultInfo endUser(Integer[] ids){
        userService.endSubByIds(ids);
        return success("结束课程评价成功");
    }
}
