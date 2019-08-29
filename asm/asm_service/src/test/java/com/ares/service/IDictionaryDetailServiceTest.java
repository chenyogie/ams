package com.ares.service;

import com.ares.domain.DictionaryDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/9 18:41
 * @Author: Chenyogie
 * @Description:
 */
public class IDictionaryDetailServiceTest extends BaseTest{

    @Autowired
    private IDictionaryDetailService dictionaryDetailService;

    @Test
    public void testLoadByType(){
        List<DictionaryDetail> list = dictionaryDetailService.loadByType("certificatetype");
        for (DictionaryDetail dictionaryDetail : list) {
            System.out.println(dictionaryDetail);
        }

    }

}