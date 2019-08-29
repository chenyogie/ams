package com.ares.basic.service.impl;

import com.ares.basic.PageList;
import com.ares.basic.mapper.BaseMapper;
import com.ares.basic.query.BaseQuery;
import com.ares.basic.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @program: ssmvue
 * @Date: 2019/7/30 16:57
 * @Author: Chenyogie
 * @Description:
 */
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;


    @Transactional
    @Override
    public void add(T t) {
        baseMapper.save(t);
    }

    @Transactional
    @Override
    public void del(Serializable id) {
        baseMapper.remove(id);
    }

    @Transactional
    @Override
    public void update(T t) {
        baseMapper.update(t);
    }

    @Override
    public T queryOne(Serializable id) {
        return baseMapper.loadOne(id);
    }

    @Override
    public List<T> queryAll() {
        return baseMapper.loadAll();
    }


    @Override
    public PageList<T> queryPage(BaseQuery query) {
        Long count = baseMapper.queryCount(query);
        if(count==0){
            return new PageList<>();
        }
        List<T> list = baseMapper.query(query);

        return new PageList<>(count,list);
    }

}
