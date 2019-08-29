package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.DestroyDoc;
import com.ares.query.ProblematicDocQuery;
import com.ares.service.IDestroyDocService;
import com.ares.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/destroyDoc")
@CrossOrigin
public class DestroyDocController implements BaseController<DestroyDoc>{
    @Autowired
    private IDestroyDocService destroyDocService;
    @Autowired
    private IRecordService recordService;

    @Override
    public AjaxResult addOrUpdate(DestroyDoc destroyDoc) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult DestroyDoc(@RequestBody DestroyDoc destroyDoc) {
        try {
            destroyDoc.setDestroydate(new Date());
            recordService.changeDocState(destroyDoc.getDoc());
            destroyDocService.add(destroyDoc);
            return AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance().setSuccess(false).setMessage("操作失败"+e.getMessage());
        }
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    @Override
    public AjaxResult del(@PathVariable("id")Long id) {
        try {
            destroyDocService.del(id);
            return AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance().setSuccess(false).setMessage("删除失败"+e.getMessage());
        }
    }

    @Override
    public DestroyDoc getById(Long id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @Override
    public List<DestroyDoc> list(){
        return destroyDocService.queryAll();
    }

    // 高级查询
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public PageList<DestroyDoc> queryPage(@RequestBody ProblematicDocQuery query){
        return destroyDocService.queryPage(query);
    }
}
