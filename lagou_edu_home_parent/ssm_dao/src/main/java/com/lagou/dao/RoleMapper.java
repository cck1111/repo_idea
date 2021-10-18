package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
        1.查询所有角色信息（条件）
     */
    public List<Role> findAllRole(Role role);

    /*
     2. 为角色分配菜单列表
     */
     //2.1 先删除角色  ， 中间表关联的菜单信息根据角色id
    public void deleteRoleContextMenu(Integer id);

    //2.2 添加角色关联的菜单信息
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        3.删除角色
     */
    //3.1  (//2.1) 先删除角色  ， 中间表关联的菜单信息根据角色id

    //3.2 删除角色信息
    public void deleteRole(Integer rid);
}
