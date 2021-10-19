package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 作业二 ：完成角色资源分配功能

    /*
         分页多条件查询资源信息
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){

        //调用service
        PageInfo<Resource> pageInfo = roleService.findAllResource(resourceVo);

        return new ResponseResult(true,200,"分页&多条件查询成功",pageInfo);
    }

    /*
        根据角色id查询对应的资源信息
    */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){

        List<ResourceCategory> resourceByRoleId = roleService.findResourceByRoleId(roleId);

        return new ResponseResult(true,200,"根据角色id查询对应的资源信息成功",resourceByRoleId);

    }

    // 为角色分配资源信息
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){

        roleService.roleContextResource(roleResourceVo);

        return new ResponseResult(true,200,"为角色分配资源信息成功",null);
    }
}
