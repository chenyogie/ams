package com.ares.service.impl;

import com.ares.BaseTest;
import com.ares.domain.ProblematicDoc;
import com.ares.lucene.index.IProblematicDocIndexHelper;
import com.ares.service.IProblematicDocService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProblematicDocServiceImplTest extends BaseTest{
    @Autowired
    private IProblematicDocService problematicDocService;
    @Autowired
    private IProblematicDocIndexHelper indexHelper;

    @Test
    public void testQueryAll()throws Exception {
        problematicDocService.queryAll().forEach(e-> System.out.println(e));
    }

    @Test
    public void testAddExsitDestroyDocIndexs()throws Exception{
        List<ProblematicDoc> problematicDocs = problematicDocService.queryAll();
        for (ProblematicDoc problematicDoc : problematicDocs) {
            indexHelper.save(problematicDoc);
        }
    }
}
