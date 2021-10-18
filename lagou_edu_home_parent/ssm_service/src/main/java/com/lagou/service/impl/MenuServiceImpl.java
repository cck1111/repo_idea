package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllMenu(int pid) {
        List<Menu> menuList = menuMapper.findAllMenu(pid);
        return menuList;
    }

    //2.根据角色id查询菜单id
    @Override
    public List<Integer> findMenuByRoleId(Integer rid) {

        List<Integer> integerList = menuMapper.findMenuByRoleId(rid);

        return integerList;
    }

    //3.. 查询所有菜单信息
    @Override
    public List<Menu> findAllMenu() {

        List<Menu> menuList = menuMapper.findAllMenu1();

        return menuList;
    }

    //4 根据id查询菜单信息
    @Override
    public Menu findMenuById(Integer id) {

        Menu menu = menuMapper.findMenuById(id);

        return menu;
    }
}
