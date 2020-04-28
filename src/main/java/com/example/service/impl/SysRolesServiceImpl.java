package com.example.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.dao.SysRolesMapper;
import com.example.service.SysRolesService;
@Service
public class SysRolesServiceImpl implements SysRolesService{

    @Resource
    private SysRolesMapper sysRolesMapper;

}
