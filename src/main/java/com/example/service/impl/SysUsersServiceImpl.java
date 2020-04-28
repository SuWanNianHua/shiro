package com.example.service.impl;

import com.example.dao.SysUsersMapper;
import com.example.pojo.SysUsers;
import com.example.service.SysUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("sysUsersService")
public class SysUsersServiceImpl implements SysUsersService{

    @Resource
    private SysUsersMapper sysUsersMapper;

    @Override
    public SysUsers findUserByName(String userName) {

        return sysUsersMapper.findUserByName(userName);
    }

    @Override
    public int saveUser(SysUsers users) {
        return sysUsersMapper.insertSelective(users);
    }
}
