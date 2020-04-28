package com.example.dao;

import com.example.pojo.SysUsers;
import tk.mybatis.mapper.common.Mapper;

public interface SysUsersMapper extends Mapper<SysUsers> {
    SysUsers findUserByName(String userName);
}