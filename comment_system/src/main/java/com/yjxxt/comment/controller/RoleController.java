package com.yjxxt.comment.controller;

import com.yjxxt.comment.base.BaseController;
import com.yjxxt.comment.base.ResultInfo;
import com.yjxxt.comment.bean.Role;
import com.yjxxt.comment.query.RoleQuery;
import com.yjxxt.comment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "role/role";
    }

    @RequestMapping("addOrUpdate")
    public String AddOrUpdateRole(Integer roleId, Model model){
        if(roleId!=null){
            Role role = roleService.selectByPrimaryKey(roleId);
            model.addAttribute("role",role);
        }
        return "role/add_update";
    }

    @RequestMapping("roleGrant")
    public String roleGrant(Integer roleId,Model model){
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }

    /**
     * 角色 列表查询
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(RoleQuery roleQuery){
        return roleService.findRolesByParam(roleQuery);
    }

    //根据用户id查询全部角色
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleService.queryAllRoles(userId);
    }

    /**
     * 添加
     * @param role
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(Role role){
        roleService.addRole(role);
        return success("添加成功");
    }

    @PostMapping("/addRoleGrant")
    @ResponseBody
    public ResultInfo addRoleGrant(Integer roleId,Integer[] moudleId) {
        roleService.addRoleGrant(roleId,moudleId);
        return success("角色授权成功");
    }

    /**
     * 修改
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Role role){
        roleService.changeRole(role);
        return success("修改成功");
    }

    /**
     * 角色 删除（单删）
     * @param role
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Role role){
        roleService.removeRoleById(role);
        return success("删除成功");
    }
}
