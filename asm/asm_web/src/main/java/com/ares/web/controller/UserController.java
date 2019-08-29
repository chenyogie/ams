package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.User;
import com.ares.query.DepartmentQuery;
import com.ares.service.IUserService;
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
@RequestMapping("/user")
@CrossOrigin
public class UserController{

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult addOrUpdate(User user){
        try{
            if (user.id != null){
                userService.update(user);
                return AjaxResult.getInstance();
            }else{
                userService.add(user);
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
            userService.del(id);
            return AjaxResult.getInstance();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败！"+ e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public User queryOne(@PathVariable("id")Long id){
        return userService.queryOne(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PageList<User> query(@RequestBody DepartmentQuery departmentQuery){
        return userService.queryPage(departmentQuery);
    }

}

