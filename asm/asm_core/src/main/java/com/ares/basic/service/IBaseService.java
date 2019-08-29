package com.ares.basic.service;


import com.ares.basic.PageList;
import com.ares.basic.query.BaseQuery;

import java.io.Serializable;
import java.util.List;

/**
 * @program: ssmvue
 * @Date: 2019/7/30 11:58
 * @Author: Chenyogie
 * @Description:
 */
public interface IBaseService<T> {
    /**
     * 保存一条数据
     * @param t
     */
    void add(T t);

    /**
     * 删除一条数据
     * @param id
     */
    void del(Serializable id);

    /**
     * 更新一条数据
     * @param t
     */
    void update(T t);

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    T queryOne(Serializable id);

    /**
     * 查询所有数据
     * @return
     */
    List<T> queryAll();

    /**
     * 带条件的分页查询
     * @return
     */
    PageList<T> queryPage(BaseQuery query);

}
