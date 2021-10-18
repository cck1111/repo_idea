package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /*
            分页&多条件查询
    */
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());

        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);

        return pageInfo;
    }

    /*
       用户注册
     */
    @Override
    public void register(User user) {

        // 使用Md5对密码进行加密
        String md5 = null;
        try {
            md5 = Md5.md5(user.getPassword(), "lagou");
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setPassword(md5);

        // 补充数据
        user.setName(user.getPhone());
        Date date = new Date();
        user.setCreate_time(date);
        user.setUpdate_time(date);

        userMapper.register(user);
    }

    /*
        用户登录
     */
    @Override
    public User login(User user) throws Exception {

        User user1 = userMapper.login(user);

        //判定查询出来的用户是否为空
        if (user1 != null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }else {
            return null;
        }

    }

    /*
        根据用户id查询其角色（分配角色进行回显）
     */
    @Override
    public List<Role> findUserRoleById(Integer uid) {

        List<Role> roleList = userMapper.findUserRoleById(uid);

        return roleList;
    }

    /*
      根据用户id分配角色
     */
    @Override
    public void userContextRole(UserVo userVo) {

        //先删除用户id关联的角色
        userMapper.deleteRole(userVo.getUserId());

        //传过来的roleId集合   再分配用户角色 通过遍历
        for (Integer roleId : userVo.getRoleIdList()) {

            //补充user_role_relation 表数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);

            user_role_relation.setCreatedBy("SYSTEM");
            user_role_relation.setUpdatedby("system");

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            userMapper.userContextRole(user_role_relation);
        }
    }


    /*
        难点: 获取用户权限信息,进行动态展示
     */
    @Override
    public ResponseResult getUserPermission(Integer userId) {

        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRoleById(userId);

        //2.获取角色ID 存到list集合中
        List<Integer> roleIds = new ArrayList<>();

        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        //3. 根据用户ID 查询父级菜单
        List<Menu> menuList = userMapper.findParentMenuByRoleId(roleIds);

        //4.查询父菜单关联的子菜单
        for (Menu menu : menuList) {

            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());

            // 将子菜单封装到Menu实体类中
            menu.setSubMenuList(subMenu);
        }

        //5.查询用户关联的资源权限信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //6.封装查询结果进行返回
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",menuList);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }


}
