package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.Menu;
import com.ares.query.MenuQuery;
import com.ares.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/menu")
public class MenuController{
    @Autowired
    private IMenuService menuService;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult addOrUpdate(@RequestBody Menu menu){
        try{
            if (menu.id != null){
                menuService.update(menu);
                return AjaxResult.getInstance();
            }else{
                menuService.add(menu);
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
            menuService.del(id);
            return AjaxResult.getInstance();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败！"+ e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public Menu queryOne(@PathVariable("id")Long id){
        return menuService.queryOne(id);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public List<Menu> queryAll(){
        return menuService.queryAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PageList<Menu> query(@RequestBody MenuQuery menuQuery){
        return menuService.queryPage(menuQuery);
    }

}
