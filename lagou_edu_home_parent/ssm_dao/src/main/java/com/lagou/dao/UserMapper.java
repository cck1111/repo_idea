package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    // 分页查询&多条件查询
    public List<User> findAllUserByPage(UserVo userVo);

    /*
        用户注册
     */
    public void register(User user);

    /*
        用户登录
     */
    public User login(User user);

    /*
        根据用户id查询其角色（分配角色进行回显）
     */
    public List<Role> findUserRoleById(Integer uid);

    /*
        分配角色 操作user_role_relation表
        先删除用户id对应的角色信息
     */
    public void deleteRole(Integer uid);

    // 再对用户进行角色的添加 进行关联
    public void userContextRole(User_Role_relation user_role_relation);

    /*
        难点：用户动态菜单跟资源的查询 --- 获取用户权限，进行菜单动态展示
     */
    //1.根据用户id查询角色    -- 上方已经编写好了

    //2.根据据角色ID 查询角色所拥有的顶级菜单(parentID = -1)
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);  // 查询出来的角色是多个，即多对多

    //3.根据ParentId (顶级菜单的id= 子级菜单的parentId） 查询子级菜单信息
    public List<Menu> findSubMenuByPid(Integer pid);

    //4.根据角色ID，查询角色的资源权限的信息
    public List<Resource> findResourceByRoleId(List<Integer> ids);
    
    public List<Resource> findResourceByRoleId2(List<Integer> ids);


    public void test11();
    public void test21();
    public void test31();
    public void test41();
    public void test51();
    public void test61();
    public void test71();
    public void test81();


    public void test1();
    public void test2();
    public void test3();
    public void test4();
    public void test5();
    public void test6();
    public void test7();
    public void test8();
}

