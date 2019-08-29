package com.ares.service.impl;



import com.ares.domain.RecordType;
import com.ares.service.IRecordTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/1 18:42
 * @Author: Chenyogie
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RecordTypeServiceImplTest {

    @Autowired
    private IRecordTypeService recordTypeService;

    @Test
    public void testAdd(){
        RecordType recordType = new RecordType();
        recordType.setName("xxx档案");
        recordType.setSncode("xxx");
        recordTypeService.add(recordType);
    }

    @Test
    public void testDel(){
    recordTypeService.del(5L);


    }

    @Test
    public void testUpdate(){

    }

    @Test
    public void testFindOne(){
        System.out.println(recordTypeService.queryOne(5L));
    }

    @Test
    public void testFindAll(){
        List<RecordType> types = recordTypeService.queryAll();
        types.forEach(e-> System.out.println(e));
    }

    @Test
    public void testFindPage(){

    }
}