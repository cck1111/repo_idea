package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
       分页&多条件查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        return new ResponseResult(true,200,"分页多条件查询成功",pageInfo);

    }

    /*
      用户注册
     */

    @RequestMapping("/register")
    public void register(@RequestBody User user){

        userService.register(user);
    }

    /*
      用户登录
     */
    @RequestMapping("login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User login = userService.login(user);

        if (login != null){
            //将数据id和access_token存入session中，下次请求需要验证，没有则重新登录
            String access_token = UUID.randomUUID().toString();
            HttpSession session = request.getSession();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",login.getId());

            //返回数据
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",login.getId());

            //将查询出来的user存到map中
            map.put("user",login);

            return new ResponseResult(true,1,"用户登录成功",map);
        }
            return new ResponseResult(false,400,"用户或密码错误",null);
    }

        /*
        根据用户id查询其角色（分配角色进行回显）
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){

        List<Role> roleList = userService.findUserRoleById(id);

        return new ResponseResult(true,200,"分配角色回显成功",roleList);
    }

    /*
      通过用户id给用户分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){

        userService.userContextRole(userVo);

        return new ResponseResult(true,200,"分配角色成功",null);
    }


    /*
        难点: 获取用户权限信息,进行动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //1.获取请求头中token
        String header_token = request.getHeader("Authorization");

        //2.获取session中token
        String session_token = (String) request.getSession().getAttribute("access_token");

        //3.判断token是否一致
        if(header_token.equals(session_token)){

            //获取session中的用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");

            //调用service，进行菜单信息查询
            ResponseResult userPermission = userService.getUserPermission(user_id);
            return userPermission;
        }else {
            return new ResponseResult(false,400,"获取菜单信息失败",null);
        }

    }

}
