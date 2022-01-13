package com.yjxxt.comment.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.comment.base.BaseService;
import com.yjxxt.comment.bean.Role;
import com.yjxxt.comment.bean.RoleMoudle;
import com.yjxxt.comment.mapper.MoudleMapper;
import com.yjxxt.comment.mapper.RoleMapper;
import com.yjxxt.comment.mapper.RoleMoudleMapper;
import com.yjxxt.comment.query.RoleQuery;
import com.yjxxt.comment.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;
    @Autowired(required = false)
    private MoudleMapper moudleMapper;
    @Autowired(required = false)
    private RoleMoudleMapper roleMoudleMapper;

    /**
     * 角色 列表查询
     * @param roleQuery
     * @return
     */
    public Map<String, Object> findRolesByParam(RoleQuery roleQuery) {
        //实例化map
        Map<String, Object> map=new HashMap<>();
        //开启分页
        PageHelper.startPage(roleQuery.getPage(),roleQuery.getLimit());
        PageInfo<Role> rlist=new PageInfo<>(selectByParams(roleQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",rlist.getTotal());
        map.put("data",rlist.getList());
        //返回目标
        return map;
    }

    /**
     * 角色添加
     *      验证：
     *          1）角色名非空
     *          2）角色名唯一
     *      设默认值：
     *          是否有效、创建时间、更新时间
     *      操作是否成功
     * @param role
     */
    public void addRole(Role role){
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名不能为空");
        Role temp=roleMapper.selectRoleByName(role.getRoleName());
        AssertUtil.isTrue(temp!=null,"角色已存在");
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.insertHasKey(role)<1,"添加失败");
        //调用添加角色资源的方法
    }

    /*
     * @Method addRoleGrant
     * @Description 角色授权
     * @Date 9:48 2022/1/8
     * @Param [roleId, moudleId]
     * @return void
     */
    public void addRoleGrant(Integer roleId,Integer[] moudleId) {
        //判断roleId是否为空
        AssertUtil.isTrue(roleId == null,"请选择角色");
        //判断数组是否为空
        AssertUtil.isTrue(moudleId == null || moudleId.length == 0,"请选择授权资源");
        //查询角色的拥有的资源数量
        int count = roleMoudleMapper.selectByCount(roleId);
        //判断是否要删除角色对应的资源信息
        if (count > 0) {
            AssertUtil.isTrue(roleMoudleMapper.deleteByRoleId(roleId) != count,"角色授权失败");
        }
        //定义List
        List<RoleMoudle> list = new ArrayList<>();
        //循环遍历
        for (int i = 0;i<moudleId.length;i++) {
            //创建角色资源对象,并添加数据
            list.add(new RoleMoudle(roleId,moudleId[i]));
        }
        //是否添加成功
        AssertUtil.isTrue(roleMoudleMapper.insertBatch(list) != moudleId.length,"角色授权失败");
    }

    /**
     * 角色修改
     *      验证：
     *          1）根据id验证
     *          2）角色名非空
     *          3）角色名唯一
     *      设默认值：
     *          更新时间
     *      操作是否成功
     * @param role
     */
    public void changeRole(Role role){
        Role temp = roleMapper.selectByPrimaryKey(role.getId());
        AssertUtil.isTrue(temp==null,"待修改记录不存在");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"角色名不能为空");
        Role temp2=roleMapper.selectRoleByName(role.getRoleName());
        AssertUtil.isTrue(temp2!=null && !temp2.getId().equals(role.getId()),"角色已存在");
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"修改失败");
    }

    /**
     * 角色 删除（单删）
     *          实际上是把is_valid修改为0（失效）
     * @param role
     */
    public void removeRoleById(Role role) {
        AssertUtil.isTrue(role.getId()==null || selectByPrimaryKey(role.getId())==null,"请选择要修改的数据");
        role.setIsValid(0);
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"删除失败");
    }

    //根据用户id查询全部角色
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleMapper.queryAllRoles(userId);
    }
}
