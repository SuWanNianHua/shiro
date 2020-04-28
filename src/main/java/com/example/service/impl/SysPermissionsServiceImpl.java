package com.example.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.dao.SysPermissionsMapper;
import com.example.service.SysPermissionsService;
@Service
public class SysPermissionsServiceImpl implements SysPermissionsService{

    @Resource
    private SysPermissionsMapper sysPermissionsMapper;

}
