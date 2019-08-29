package com.ares.mapper;


import com.ares.basic.mapper.BaseMapper;
import com.ares.domain.User;

/**
 * @program: ssm_parent
 * @Date: 2019/7/31 10:21
 * @Author: Chenyogie
 * @Description:
 */
public interface UserMapper extends BaseMapper<User> {
    public User queryByName(String name);
}
