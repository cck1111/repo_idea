package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
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

    /*
      1.查询所有角色（条件）
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> roleList = roleService.findAllRole(role);

        return new ResponseResult(true,200,"查询所有角色成功（条件）",roleList);
    }

    /*
       2.查询所有父子菜单信息
     */

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        // -1 = parent_id
        List<Menu> menuList = menuService.findAllMenu(-1);

        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        return new ResponseResult(true,200,"查询父子级菜单成功",map);
    }

    /*
         根据角色id查询菜单id
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> integerList = menuService.findMenuByRoleId(roleId);

        return new ResponseResult(true,200,"根据角色id查询菜单id成功",integerList);
    }

    /*
       为角色分配菜单信息
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){


        roleService.RoleContextMenu(roleMenuVo);

        return new ResponseResult(true,200,"为角色分配菜单信息成功",null);
    }

    /*
      删除角色信息
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);

        return new ResponseResult(true,200,"删除角色成功",null);
    }

}
