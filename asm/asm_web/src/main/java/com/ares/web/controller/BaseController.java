package com.ares.web.controller;

import com.ares.basic.AjaxResult;

import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/1 23:07
 * @Author: Chenyogie
 * @Description:
 */
public interface BaseController<T>{
    AjaxResult addOrUpdate(T t);

    AjaxResult del(Long id);

    T getById(Long id);

    List<T> list();

}
