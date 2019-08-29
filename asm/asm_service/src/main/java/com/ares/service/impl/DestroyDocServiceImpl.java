package com.ares.service.impl;

import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.DestroyDoc;
import com.ares.lucene.index.IDestroyDocIndexHelper;
import com.ares.mapper.DestroyDocMapper;
import com.ares.service.IDestroyDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class DestroyDocServiceImpl extends BaseServiceImpl<DestroyDoc> implements IDestroyDocService {
    @Autowired
    private DestroyDocMapper destroyDocMapper;

    @Autowired
    private IDestroyDocIndexHelper iDestroyDocIndexHelper;

    @Override
    public void add(DestroyDoc destroyDoc) {
        destroyDocMapper.save(destroyDoc);
        //iDestroyDocIndexHelper.save(destroyDoc);
    }

    @Override
    public void del(Serializable id) {
        destroyDocMapper.remove(id);
        //iDestroyDocIndexHelper.remove(Long.parseLong(id.toString()));
    }

    @Override
    public void update(DestroyDoc destroyDoc) {
        destroyDocMapper.update(destroyDoc);
        //iDestroyDocIndexHelper.update(destroyDoc);
    }

    @Override
    public DestroyDoc queryOne(Serializable id) {
        return destroyDocMapper.loadOne(id);
    }

    @Override
    public List<DestroyDoc> queryAll() {
        return destroyDocMapper.loadAll();
    }

    /*@Override
    public PageList<DestroyDoc> queryPage(BaseQuery query) {
        DestroyDocQuery destroyDocQuery = (DestroyDocQuery) query;
        return iDestroyDocIndexHelper.list(destroyDocQuery);
    }*/
}
