package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.Permission;
import com.ares.query.PermissionQuery;
import com.ares.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody Permission permission){
        try{
            if (permission.id != null){
                permissionService.update(permission);
                return AjaxResult.getInstance();
            }else{
                permissionService.add(permission);
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
            permissionService.del(id);
            return AjaxResult.getInstance();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败！"+ e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public Permission queryOne(@PathVariable("id")Long id){
        return permissionService.queryOne(id);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public List<Permission> queryAll(){
        return permissionService.queryAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PageList<Permission> query(@RequestBody PermissionQuery permissionQuery){
        return permissionService.queryPage(permissionQuery);
    }

}
