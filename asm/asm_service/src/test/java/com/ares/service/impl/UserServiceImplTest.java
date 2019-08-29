package com.ares.service.impl;


import com.ares.domain.User;
import com.ares.mapper.UserMapper;
import com.ares.query.UserQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/1 18:42
 * @Author: Chenyogie
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceImplTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testAdd(){
    }

    @Test
    public void testDel(){
        userMapper.remove(1L);
    }

    @Test
    public void testUpdate(){
        User user = userMapper.loadOne(2L);
        userMapper.update(user);
    }

    @Test
    public void testFindOne(){
        System.out.println(userMapper.loadOne(2L));
    }

    @Test
    public void testFindAll(){
        List<User> users = userMapper.loadAll();
        users.forEach(e-> System.out.println(e));
    }

    @Test
    public void testFindPage(){
        UserQuery query = new UserQuery();
        query.setKeyWords("q");
        System.out.println("总记录数："+userMapper.queryCount(query));
        List<User> list = userMapper.query(query);
        list.forEach(e-> System.out.println("item："+e));
    }
}