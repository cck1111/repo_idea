package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
       查询所有菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> menuList = menuService.findAllMenu();

        return new ResponseResult(true,200,"查询所有菜单信息成功",menuList);
    }

    /*
        新增&修改 回显信息
        如果是新增菜单,则id值为 -1, <br>修改菜单 则为当前选择的id值
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        // 判断传过来的id值
        if(id == -1){
            // 新增
            List<Menu> menus = menuService.findAllMenu(id);

            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menus);

            return new ResponseResult(true,200,"新增回显成功",map);
        }else {
            // 修改

            Menu menu = menuService.findMenuById(id);

            List<Menu> menus = menuService.findAllMenu(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menus);

            return new ResponseResult(true,200,"新增回显成功",map);
        }
    }

}
