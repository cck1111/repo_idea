package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色（条件）
     */
    public List<Role> findAllRole(Role role);

    /*
        2.为角色分配菜单信息
     */
    public void RoleContextMenu(RoleMenuVo roleMenuVo);

    /*
     3. 删除角色信息
     */
    public void deleteRole(Integer rid);
}
