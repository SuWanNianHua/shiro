package com.example.controller;

import com.example.comment.PasswordHelper;
import com.example.pojo.SysUsers;
import com.example.service.SysUsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 张佳奇
 * @Description 该类为项目的入口类  不需要经过权限验证就可以访问的资源
 * @Date 2020-04-26 09:22
 */
@RestController
public class ShiroController {


    @Resource
    SysUsersService sysUsersService;

    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping("doLogin")
    public Object doLogin(@RequestParam String username, @RequestParam String password) {
        //创建用户名/密码验证token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        System.out.println(token.toString());
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        //执行登录
        //调用该方法时会自动调用comment包中的RealmShiro中的doGetAuthenticationInfo方法
        //本来该方法是需要捕获异常的,但由于采用的注解形式进行了全局的异常处理所以在此处不用进行捕获异常
        subject.login(token);
        SysUsers user = sysUsersService.findUserByName(username);
        subject.getSession().setAttribute("user", user);
        return "LOGIN SUCCESS";
    }

    @GetMapping("register")
    public Object register(String username,String password) {
        SysUsers user = new SysUsers();
        user.setUsername(username);
        user.setPassword(password);
        //该方法将用户密码进行加密存储
        passwordHelper.emcryptPassword(user);
        //注册成功保存
        sysUsersService.saveUser(user);
        return "REGISTER SUCCESS";
    }

}
