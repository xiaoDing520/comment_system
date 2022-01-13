package com.yjxxt.comment.service;

import com.yjxxt.comment.base.BaseService;
import com.yjxxt.comment.bean.Moudle;
import com.yjxxt.comment.dto.TreeDto;
import com.yjxxt.comment.mapper.MoudleMapper;
import com.yjxxt.comment.mapper.RoleMoudleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


/**
 * @ClassName MoudleService
 * @Desc 资源业务逻辑类
 * @Author xiaoding
 * @Date 2022-01-07 11:34
 * @Version 1.0
 */
@Service
public class MoudleService extends BaseService<Moudle,Integer> {
    @Autowired(required = false)
    private MoudleMapper moudleMapper;
    @Autowired(required = false)
    private RoleMoudleMapper roleMoudleMapper;

    public Map<String,Object> selectQuery() {
        //创建Map对象
        Map<String,Object> map = new HashMap<>();
        //添加查询数据
        List<Moudle> list = moudleMapper.selectQuery();
        //添加数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",list.size());
        map.put("data",list);
        //返回Map对象数据
        return map;
    }

    /**
     * 查询所有资源信息
     * @return
     */
    public List<TreeDto> findModules(Integer roleId){
        //获取全部资源信息
        List<TreeDto> list = moudleMapper.selectMoudles();
        //根据角色id，查询角色对象的资源id
        List<Integer> list2 = roleMoudleMapper.selectByRoleId(roleId);
        System.out.println(list2 == null);
        //判断是否有值
        if (list2 != null && list2.size() == 0) {
            return list;
        }
        //循环遍历
        for (TreeDto treeDto :list) {
            if (list2.contains(treeDto.getId())) {
                treeDto.setChecked(true);
            }
        }
        return list;
    }
}
