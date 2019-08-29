package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.Role;
import com.ares.query.RoleQuery;
import com.ares.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/role")
public class RoleController{

    @Autowired
    private IRoleService roleService;

    /*@RequestMapping(value = "/add",method = RequestMethod.PUT)
    public AjaxResult add(Role role){
        try{
            roleService.add(role);
            return AjaxResult.getInstance();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("保存失败！"+e.getMessage());
        }
    }
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult update(Long id,Role role){
        try{
            roleService.update(role);
            return AjaxResult.getInstance();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("修改失败！"+e.getMessage());
        }
    }*/

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody Role role){
         try{
             if (role.id != null){
                 roleService.update(role);
                 return AjaxResult.getInstance();
             }else {
                 roleService.add(role);
                 return AjaxResult.getInstance();
             }
         }catch (Exception e){
             e.printStackTrace();
             return AjaxResult.getInstance("操作失败！"+ e.getMessage());
         }
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete(@PathVariable("id")Long id){
        try{
            roleService.del(id);
            return AjaxResult.getInstance();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败！"+ e.getMessage());
        }
    }

    @RequestMapping(value = "/queryOne/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Role queryOne(@PathVariable("id")Long id){
        return roleService.queryOne(id);
    }

    @RequestMapping(value = "/queryAll",method = RequestMethod.PATCH)
    @ResponseBody
    public List<Role> queryAll() {
        return roleService.queryAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PageList<Role> query(@RequestBody RoleQuery roleQuery) {
        return roleService.queryPage(roleQuery);
    }
}
