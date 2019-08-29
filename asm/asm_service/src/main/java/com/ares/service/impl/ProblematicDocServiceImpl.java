package com.ares.service.impl;

import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.ProblematicDoc;
import com.ares.lucene.index.IProblematicDocIndexHelper;
import com.ares.mapper.ProblematicDocMapper;
import com.ares.service.IProblematicDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ProblematicDocServiceImpl extends BaseServiceImpl<ProblematicDoc> implements IProblematicDocService {
    @Autowired
    private ProblematicDocMapper problematicDocMapper;

    @Autowired
    private IProblematicDocIndexHelper iProblematicDocIndexHelper;

    @Override
    public void add(ProblematicDoc problematicDoc) {
        problematicDocMapper.save(problematicDoc);
        //iProblematicDocIndexHelper.save(problematicDoc);
    }

    @Override
    public void del(Serializable id) {
        problematicDocMapper.remove(id);
        //iProblematicDocIndexHelper.remove(Long.parseLong(id.toString()));
    }

    @Override
    public void update(ProblematicDoc problematicDoc) {
        problematicDocMapper.update(problematicDoc);
        //iProblematicDocIndexHelper.update(problematicDoc);
    }

    @Override
    public ProblematicDoc queryOne(Serializable id) {
        return problematicDocMapper.loadOne(id);
    }

    @Override
    public List<ProblematicDoc> queryAll() {
        return problematicDocMapper.loadAll();
    }

    /*@Override
    public PageList<ProblematicDoc> queryPage(BaseQuery query) {
        ProblematicDocQuery problematicDocQuery = (ProblematicDocQuery) query;
        return iProblematicDocIndexHelper.list(problematicDocQuery);
    }*/
}
