package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    // 作业二 ：完成角色资源分配功能

    /*
         分页多条件查询资源信息
     */
    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {

        // 进行分页
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        // 调用方法
        List<Resource> resourceList = roleMapper.findAllResource(resourceVo);

        // 返回展示数据
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);

        return pageInfo;
    }

    /*
        根据角色id查询对应的资源信息
     */
    @Override
    public List<ResourceCategory> findResourceByRoleId(Integer id) {

        List<ResourceCategory> resourceByRoleId = roleMapper.findResourceByRoleId(id);


        return resourceByRoleId;
    }

    // 为角色分配资源信息
    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        // 先删除角色拥有的资源信息
        roleMapper.deleteResourceByRoleId(roleResourceVo.getRoleId());

        // 保存角色资源信息
        Role_Resource_Relation relation = new Role_Resource_Relation();

        Date date = new Date();

        // 获取resource  ids
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();
        for (Integer resourceId : resourceIdList) {

            // 分装Role_Resource_Relation
            relation.setRoleId(roleResourceVo.getRoleId());
            relation.setResourceId(resourceId);
            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);
            relation.setCreatedBy("system");
            relation.setUpdatedBy("system");

            // 保存
            roleMapper.saveResourceByResId(relation);
        }
    }
}
