package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
    * 用户表
    */
@Data
@Table(name = "sys_users")
public class SysUsers {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "`PASSWORD`")
    private String password;

    /**
     * 盐值
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 头像
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 住址
     */
    @Column(name = "address")
    private String address;

    /**
     * 状态
     */
    @Column(name = "locaed")
    private String locaed;

    /**
     * 用户对应的角色集合
     */
    private List<SysRoles> roles;

}