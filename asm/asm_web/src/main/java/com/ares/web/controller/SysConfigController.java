package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.SysConfig;
import com.ares.query.SysConfigQuery;
import com.ares.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: ssm_parent
 * @Date: 2019/7/31 23:32
 * @Author: Chenyogie
 * @Description:
 */
@Controller
@RequestMapping("/sysconfig")
@CrossOrigin
public class SysConfigController {

    @Autowired
    private ISysConfigService iSysConfigService;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult addOrUpdate(SysConfig SysConfig){
        try{
            if (SysConfig.id != null){
                iSysConfigService.update(SysConfig);
                return AjaxResult.getInstance();
            }else{
                iSysConfigService.add(SysConfig);
                return AjaxResult.getInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("操作失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        try{
            iSysConfigService.del(id);
            return AjaxResult.getInstance();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败！"+ e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public SysConfig queryOne(@PathVariable("id")Long id){
        return iSysConfigService.queryOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PageList<SysConfig> query(@RequestBody SysConfigQuery SysConfigQuery){
        return iSysConfigService.queryPage(SysConfigQuery);
    }

}

