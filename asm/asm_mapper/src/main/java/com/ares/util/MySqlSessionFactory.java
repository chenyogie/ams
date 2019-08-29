package com.ares.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.io.IOException;

/**
 * @program: asm
 * @Date: 2019/8/13 11:00
 * @Author: Chenyogie
 * @Description:
 */
public class MySqlSessionFactory extends SqlSessionFactoryBean {

    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        try {
            //捕获异常
            return super.buildSqlSessionFactory();
        } catch (IOException e) {
            //打印异常信息
            e.printStackTrace();
        }
        return null;
    }
}
