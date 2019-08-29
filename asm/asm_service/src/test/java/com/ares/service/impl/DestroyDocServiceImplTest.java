package com.ares.service.impl;

import com.ares.BaseTest;
import com.ares.domain.DestroyDoc;
import com.ares.lucene.index.IDestroyDocIndexHelper;
import com.ares.service.IDestroyDocService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DestroyDocServiceImplTest extends BaseTest{
    @Autowired
    private IDestroyDocService destroyDocService;
    @Autowired
    private IDestroyDocIndexHelper indexHelper;

    @Test
    public void testAddExsitDestroyDocIndexs()throws Exception{
        List<DestroyDoc> destroyDocs = destroyDocService.queryAll();
        for (DestroyDoc destroyDoc : destroyDocs) {
            indexHelper.save(destroyDoc);
        }
    }

    @Test
    public void testQueryAll()throws Exception {
        destroyDocService.queryAll().forEach(e-> System.out.println(e));
    }
}
