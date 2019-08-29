package com.ares.basic.mapper;


import com.ares.basic.query.BaseQuery;

import java.io.Serializable;
import java.util.List;

/**
 * @program: ssmvue
 * @Date: 2019/7/30 11:50
 * @Author: Chenyogie
 * @Description:
 */
public interface BaseMapper<T> {
    /**
     * 保存一条数据
     * @param t
     */
    void save(T t);

    /**
     * 删除一条数据
     * @param id
     */
    void remove(Serializable id);

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
    T loadOne(Serializable id);

    /**
     * 查询所有数据
     * @return
     */
    List<T> loadAll();

    /**
     * 带条件的查询总记录数
     * @param query
     * @return
     */
    Long queryCount(BaseQuery query);

    /**
     * 带条件的分页查询
     * @return
     */
    List<T> query(BaseQuery query);
}
