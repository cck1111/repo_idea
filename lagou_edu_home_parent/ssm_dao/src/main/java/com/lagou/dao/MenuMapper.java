package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
        1.查询所有父子菜单信息
     */
    public List<Menu> findAllMenu(int pid);

    /*
        2.根据角色id查询菜单id
     */
    public List<Integer> findMenuByRoleId(Integer rid);

    /*
        3.查询所有菜单信息
     */
    public List<Menu> findAllMenu1();

    /*
    4. 根据id查询菜单信息
     */
    public Menu findMenuById(Integer id);
}
