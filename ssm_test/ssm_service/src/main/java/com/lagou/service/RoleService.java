package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

public interface RoleService {

    // 作业二 ：完成角色资源分配功能

    /*
         分页多条件查询资源信息
     */
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);

    /*
      获取当前角色的资源信息
     */
    // 根据角色id查询对应的资源信息
    public List<ResourceCategory> findResourceByRoleId(Integer id);

    // 为角色分配资源信息
    public void roleContextResource(RoleResourceVo roleResourceVo);

}
