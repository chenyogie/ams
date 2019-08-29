package com.ares.service.impl;


import com.ares.domain.Record;
import com.ares.mapper.RecordMapper;
import com.ares.query.RecordQuery;
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
public class RecordServiceImplTest {

    @Autowired
    private RecordMapper recordMapper;

    @Test
    public void testAdd(){
        Record record = new Record();
        record.setNum("哈哈哈");
        record.setAdvancedate(new Date());
        recordMapper.save(record);
    }

    @Test
    public void testDel(){
        recordMapper.remove(1L);


    }

    @Test
    public void testUpdate(){
        Record record = recordMapper.loadOne(1);
        System.out.println(record);
        record.setNum("99999999999999999");
        recordMapper.update(record);
        System.out.println(record);
    }

    @Test
    public void testFindOne(){
        Record record = recordMapper.loadOne(1);
        System.out.println(record);
        record.setNum("99999999999999999");
        recordMapper.update(record);
        System.out.println(record);
    }

    @Test
    public void testFindAll(){

    }

    @Test
    public void testFindPage(){
        RecordQuery recordQuery = new RecordQuery();
        Long count = recordMapper.queryCount(recordQuery);
        System.out.println(count);

    }

    @Test
    public void testQuery(){
        RecordQuery recordQuery = new RecordQuery();
        Long count = recordMapper.queryCount(recordQuery);
        System.out.println(count);
        System.out.println("===========================");
        List<Record> list = recordMapper.query(recordQuery);
        list.forEach(e-> System.out.println(e));
    }
}