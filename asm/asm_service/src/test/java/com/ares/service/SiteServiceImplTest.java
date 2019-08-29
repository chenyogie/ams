package com.ares.service;

import com.ares.basic.PageList;
import com.ares.domain.Site;
import com.ares.query.SiteQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/3 16:50
 * @Author: Chenyogie
 * @Description:
 */

public class SiteServiceImplTest extends BaseTest{

    @Autowired
    private ISiteService siteService;

    @Test
    public void addVo() {
        List<Site> sites = siteService.queryAll();
        sites.forEach(site -> System.out.println(site));
    }
    @Test
    public void addVo1() {
        Site site1 = siteService.queryOne(2L);
        System.out.println(site1);
        site1.setName("小商小贩们");
        siteService.update(site1);
        Site site2 = siteService.queryOne(2L);
        System.out.println(site1);
    }
    @Test
    public void addVo2() {
        SiteQuery siteQuery = new SiteQuery();
        siteQuery.setKeyWords("我");
        PageList<Site> sites = siteService.queryPage(siteQuery);
        List<Site> rows = sites.getRows();
        rows.forEach(e-> System.out.println(e));

    }
    @Test
    public void addVo3() {
       Site sites = siteService.queryOne(4);
       sites.setRemark("大王喊我来吃饭");
       siteService.update(sites);
    }
}