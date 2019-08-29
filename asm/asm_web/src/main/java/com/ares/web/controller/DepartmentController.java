package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.Department;
import com.ares.query.DepartmentQuery;
import com.ares.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Controller
@RequestMapping("/department")
public class DepartmentController  {




    @Autowired

    private IDepartmentService iDepartmentService;
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult addOrUpdate(Department department){
        try{
            if (department.id != null){
                iDepartmentService.update(department);
                return AjaxResult.getInstance();
            }else{
                iDepartmentService.add(department);
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
            iDepartmentService.del(id);
            return AjaxResult.getInstance();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败！"+ e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public Department queryOne(@PathVariable("id")Long id){
        return iDepartmentService.queryOne(id);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public List<Department> queryAll(){
        return iDepartmentService.queryAll();


    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PageList<Department> query(@RequestBody DepartmentQuery departmentQuery){
        return iDepartmentService.queryPage(departmentQuery);
    }

}
