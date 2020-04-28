package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
    * 角色表
    */
@Data
@Table(name = "sys_roles")
public class SysRoles {
    /**
     * 角色编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "rolename")
    private String rolename;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 状态
     */
    @Column(name = "available")
    private String available;

    /**
     * 角色对应的权限集合
     */
    private List<SysPermissions> permissions;
}