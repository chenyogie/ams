package com.ares.service.impl;

import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.User;
import com.ares.mapper.UserMapper;
import com.ares.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: ssm_parent
 * @Date: 2019/7/31 22:47
 * @Author: Chenyogie
 * @Description:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }
}
