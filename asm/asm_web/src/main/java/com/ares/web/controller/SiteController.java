package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.Site;
import com.ares.query.SiteQuery;
import com.ares.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private ISiteService siteService;


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}",method= RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delSite(@PathVariable("id")Long id){
        try {
            siteService.del(id);
            return  AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败"+e);
        }
    }

    /**
     * 添加
     * @param site
     * @return
     */
    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSite(@RequestBody Site site){
        try {
            siteService.add(site);
            return  AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("添加失败"+e);
        }
    }

    /**
     * 修改
     * @param site
     * @return
     */
    @RequestMapping(method= RequestMethod.PUT)
    @ResponseBody
    public AjaxResult updateSite(@RequestBody Site site) {
        try {
            siteService.update(site);
            return AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("修改失败" + e);
        }
    }

    /**
     * id查询一个
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    @ResponseBody
    public Site querySite(@PathVariable("id")long id) {
        return  siteService.queryOne(id);
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public PageList<Site> queryAll(@RequestBody SiteQuery siteQuery){
        System.out.println(siteQuery);
        return siteService.queryPage(siteQuery);
    }
}
