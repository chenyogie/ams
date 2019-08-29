package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.ProblematicDoc;
import com.ares.query.ProblematicDocQuery;
import com.ares.service.IProblematicDocService;
import com.ares.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/problematicDoc")
@CrossOrigin
public class ProblematicDocController implements BaseController<ProblematicDoc>{
    @Autowired
    private IProblematicDocService problematicDocService;
    @Autowired
    private IRecordService recordService;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @Override
    public AjaxResult addOrUpdate(@RequestBody ProblematicDoc problematicDoc) {
        System.out.println(problematicDoc);
        try {
            problematicDoc.setAccidentdate(new Date());
            recordService.changeDocState(problematicDoc.getDoc());
            if(problematicDoc.getId()!=null) {
                problematicDocService.update(problematicDoc);
            }else{
                problematicDocService.add(problematicDoc);
            }
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
            problematicDocService.del(id);
            return AjaxResult.getInstance();
        } catch (Exception e) {
            return AjaxResult.getInstance().setSuccess(false).setMessage("删除失败"+e.getMessage());
        }
    }

    @Override
    public ProblematicDoc getById(Long id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @Override
    public List<ProblematicDoc> list(){
        return problematicDocService.queryAll();
    }

    // 高级查询
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public PageList<ProblematicDoc> queryPage(@RequestBody ProblematicDocQuery query){
        return problematicDocService.queryPage(query);
    }
}
