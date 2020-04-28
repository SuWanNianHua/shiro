package com.example.pojo;

import javax.persistence.*;
import lombok.Data;

/**
    * 权限表
    */
@Data
@Table(name = "sys_permissions")
public class SysPermissions {
    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 权限名称
     */
    @Column(name = "`NAME`")
    private String name;

    /**
     * 权限路径
     */
    @Column(name = "url")
    private String url;

    /**
     * 权限类型
     */
    @Column(name = "`TYPE`")
    private String type;

    /**
     * 父节点
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * 状态
     */
    @Column(name = "available")
    private String available;
}