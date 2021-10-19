package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.Role_Resource_Relation;

import java.util.List;

public interface RoleMapper {

    // 作业二 ：完成角色资源分配功能

    /*
         分页多条件查询资源信息
     */
    public List<Resource> findAllResource(ResourceVo resourceVo);

    // 根据角色id查询对应的拥有的资源信息
    public List<ResourceCategory> findResourceByRoleId(Integer id);

    // 为角色分配资源信息
    // 首先将角色拥有的资源信息清空
    public void deleteResourceByRoleId(Integer rid);

    //再保存分配好的资源信息
    public void saveResourceByResId(Role_Resource_Relation role_resource_relation);




}
