package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {

    /*
            分页&多条件查询
     */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
      用户注册
     */
    public void register(User user);

    /*
       用户登录
     */
    public User login(User user) throws Exception;

    /*
        根据用户id查询其角色（分配角色进行回显）
     */
    public List<Role> findUserRoleById(Integer uid);

    /*
      根据用户id分配角色
     */
    public void userContextRole(UserVo userVo);

    /*
        难点: 获取用户权限信息
     */
    public ResponseResult getUserPermission(Integer userId);
}
