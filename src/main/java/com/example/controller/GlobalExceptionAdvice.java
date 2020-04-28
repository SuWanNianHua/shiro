package com.example.controller;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 张佳奇
 * @Description shiro权限验证的全局捕获异常
 * @Date 2020-04-28 14:25
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * 捕获全局异常  简单的考虑了几种异常若有有其他需要 ,请查看shiro异常类型 然后自己增加判断 如若有其他业务异常也可在此增加
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String Exception(Exception e){
        String msg="";
        if (e instanceof UnauthorizedException){
            msg+="你没有权限";
            return msg;
        }else if (e instanceof UnknownAccountException){
            msg+="账号不存在";
            return msg;
        }else if(e instanceof UnauthenticatedException) {
            msg+="请登录后访问";
            return msg;
        }
       e.printStackTrace();
        return msg;
    }

}
