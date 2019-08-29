package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.RecordType;
import com.ares.query.RecordTypeQuery;
import com.ares.service.IRecordTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/6 12:54
 * @Author: Chenyogie
 * @Description:
 */
@Controller
@RequestMapping("/recordType")
@CrossOrigin
public class RecordTypeController {

    @Autowired
    private IRecordTypeService recordTypeService;

    /**
     * 查询所有
     * @return
     */

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public PageList<RecordType> findAll(@RequestBody RecordTypeQuery recordTypeQuery){
        return recordTypeService.queryPage(recordTypeQuery);
    }
    /**
     * id查询一个
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    @ResponseBody
    public RecordType queryRecordType(@PathVariable("id")long id) {
        return  recordTypeService.queryOne(id);
    }
    /**
     * 修改
     * @param
     * @return
     */
    @RequestMapping(method= RequestMethod.PUT)
    @ResponseBody
    public AjaxResult updateRecordType(@RequestBody RecordType recordType) {

        try {
            if(recordType.getId()!=null){
                recordTypeService.update(recordType);
                return AjaxResult.getInstance();
            }else{
                recordTypeService.add(recordType);
                return AjaxResult.getInstance();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("修改失败" + e);
        }
    }

    /**
     * 添加
     * @param recordType
     * @return
     */
    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult addRecordType(@RequestBody RecordType recordType){
        try {
            recordTypeService.add(recordType);
            return  AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("添加失败"+e);
        }
    }
    @RequestMapping(value = "{id}",method= RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult updateRecordType(@PathVariable("id")Long id) {
        try {
            recordTypeService.del(id);
            return AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败" + e);
        }
    }
}
