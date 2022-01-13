package com.yjxxt.comment.controller;

import com.yjxxt.comment.base.BaseController;
import com.yjxxt.comment.base.ResultInfo;
import com.yjxxt.comment.bean.User;
import com.yjxxt.comment.service.RoleMoudleService;
import com.yjxxt.comment.service.UserService;
import com.yjxxt.comment.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IndexController
 * @Desc 主页面控制层
 * @Author xiaoding
 * @Date 2022-01-05 15:11
 * @Version 1.0
 */
@Controller
public class IndexController extends BaseController {
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private RoleMoudleService roleMoudleService;

    /*
     * @Method index
     * @Description 访问登录
     * @Date 15:15 2022/1/5
     * @Param []
     * @return java.lang.String
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /*
     * @Method main
     * @Description 访问主页面
     * @Date 15:16 2022/1/5
     * @Param []
     * @return java.lang.String
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request){
        //通过工具类获取userId
        int userid = LoginUserUtil.releaseUserIdFromCookie(request);
        //调用service层的方法，通过id查对象
        User user =(User)userService.selectByPrimaryKey(userid);
        //将用户设置到request作用域中
        request.setAttribute("user",user);
        //存储用户的角色对应的权限码
        roleMoudleService.selectByRoleId2(user.getRoleId()).forEach(System.out::println);
        request.getSession().setAttribute("roleMoudle",roleMoudleService.selectByRoleId2(user.getRoleId()));
        return "main";
    }

    /*
     * @Method welcome
     * @Description 访问欢迎页面
     * @Date 15:16 2022/1/5
     * @Param []
     * @return java.lang.String
     */
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    //登录页面转发
    @RequestMapping("register")
    public String register(){
        return "register";
    }

    //注册：添加用户
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(User user){
        userService.saveUser(user);
        return success("添加成功");
    }
}
