package com.ares.web.controller;


import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.Record;
import com.ares.domain.Site;
import com.ares.query.RecordQuery;
import com.ares.service.IRecordService;
import com.ares.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/record")
@CrossOrigin
public class RecordController {
    @Autowired
    private IRecordService recordService;
    @Autowired
    private ISiteService siteService;

    /**
     * 添加
     * @param record
     * @return
     */
    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult addRecord(@RequestBody Record record){
        System.out.println(record);
        try {
            recordService.add(record);
            return  AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("添加失败"+e);
        }

    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}",method= RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delRecord(@PathVariable("id")Long id){
        try {
            recordService.del(id);
            return  AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("删除失败"+e);
        }
    }

    /**
     * 修改
     * @param id
     * @param record
     * @return
     */
    @RequestMapping(value = "{id}",method= RequestMethod.PUT)
    @ResponseBody
    public AjaxResult updateRecord(@PathVariable("id")Long id,@RequestBody Record record){
        try {
            recordService.update(record);
            return  AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("添加失败"+e);
        }
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public PageList<Record> queryAll(@RequestBody RecordQuery recordQuery){
        return recordService.queryPage(recordQuery);
    }

    /**
     * 分页高级查询
     * @param query
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.PATCH)
    @ResponseBody
    public PageList<Record> query(@RequestBody RecordQuery query){
        System.out.println(query);
        return recordService.queryPage(query);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Record queryById(@PathVariable("id")Long id){
        return  recordService.queryOne(id);

    }



    @RequestMapping(value ="/siteName" ,method = RequestMethod.PATCH)
    @ResponseBody
    public  List<Site> getSiteName(){
        return  siteService.queryAll();
    }

}
