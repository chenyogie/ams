package com.ares.service;

import com.ares.basic.service.IBaseService;
import com.ares.domain.User;

/**
 * @program: ssm_parent
 * @Date: 2019/7/31 22:46
 * @Author: Chenyogie
 * @Description:
 */
public interface IUserService extends IBaseService<User> {
    public User queryByName(String name);
}
