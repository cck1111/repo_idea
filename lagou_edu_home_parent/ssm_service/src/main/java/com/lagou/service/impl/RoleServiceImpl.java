package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /*
      查询所有角色（条件）
   */
    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }

    /*
    2.为角色分配菜单信息
    */
    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {

        //1 调用Mapper删除方法
        Integer roleId = roleMenuVo.getRoleId();
        roleMapper.deleteRoleContextMenu(roleId);

        //2 调用添加方法
        for (Integer menu : roleMenuVo.getMenuIdList()) {

            // 封装数据
            Role_menu_relation roleMenuRelation = new Role_menu_relation();

            Date date = new Date();
            roleMenuRelation.setMenuId(menu);
            roleMenuRelation.setRoleId(roleMenuVo.getRoleId());
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);

            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");

            roleMapper.roleContextMenu(roleMenuRelation);
        }



    }

    /*
      3 删除角色信息
     */

    @Override
    public void deleteRole(Integer rid) {

        //1 先删除角色关联中间表菜单的信息
        roleMapper.deleteRoleContextMenu(rid);

        //2.在删除角色信息
        roleMapper.deleteRole(rid);

    }
}
