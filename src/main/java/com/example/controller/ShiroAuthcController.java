package com.example.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张佳奇
 * @Description 该类需要有一定的权限才可以访问的资源,采用注解模式来判断权限校验
 *              采用注解校验权限时会产生异常信息  根据对应的异常信息进行不同的操作  异常处理在GlobalExceptionAdvice中
 * @Date 2020-04-28 09:49
 */
@RestController
@RequestMapping("authc")
public class ShiroAuthcController{

    /**
     * 注解校验角色和权限
     */
    @RequiresPermissions("系统设置")
    @RequestMapping("renewable")
    public String shiroTest(){
        return "用户权限校验成功";
    }

    @RequiresAuthentication
    @RequestMapping("/index")
    public String shiroT(){
        return "登录后才可以访问的资源";
    }

    @RequiresRoles("超级管理")
    @RequestMapping("realms")
    public String realm(){
        return "用户橘色权限校验成功";
    }
}
