package com.example.service;

import com.example.pojo.SysUsers;

public interface SysUsersService{
    /**
     * 根据用户账户名查询用户的所有信息(所拥有的角色,权限)
     * @param userName
     * @return
     */
    SysUsers findUserByName(String userName);

    /**
     * 注册用户
     * @param users
     * @return
     */
    int saveUser(SysUsers users);
}
