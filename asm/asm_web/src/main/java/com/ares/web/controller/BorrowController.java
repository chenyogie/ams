package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.basic.PageList;
import com.ares.domain.Borrow;
import com.ares.domain.DictionaryDetail;
import com.ares.domain.Record;
import com.ares.domain.vo.BorrowVo;
import com.ares.query.BorrowQuery;
import com.ares.query.RecordQuery;
import com.ares.service.IBorrowService;
import com.ares.service.IDictionaryDetailService;
import com.ares.service.IRecordService;
import com.ares.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/4 13:12
 * @Author: Chenyogie
 * @Description:
 */
@Controller
@RequestMapping("/borrow")
@CrossOrigin
public class BorrowController {

    @Autowired
    private IBorrowService borrowService;

    @Autowired
    private IRecordService recordService;

    @Autowired
    private IDictionaryDetailService dictionaryDetailService;

    /**
     * 借阅的方法
     * @param borrow
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult add(@RequestBody Borrow borrow){
        System.out.println(borrow.toString());
        try {
            //设置借阅编号
            borrow.setBorrowNum(UUIDUtil.createBorrowNum());
            //借阅的状态为0
            borrow.setStatus(false);
            //借阅的日期为当前日期
            borrow.setDate(new Date());
            borrowService.add(borrow);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("保存数据失败！");
        }
        return AjaxResult.getInstance();
    }

    /**
     * 查询所有借阅记录
     * @param query
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public PageList<BorrowVo> findPage(@RequestBody BorrowQuery query){

        System.out.println("接收的参数：=================："+query);

        //在归还页面点击借阅记录的时候，只需要展示未归还的借阅记录,在前天设置
        //query.setStatus(false);
        PageList<BorrowVo> page = borrowService.queryPage(query);
        return page;
    }

    /**
     * 分页查询档案
     * @param recordQuery
     * @return
     */
    @RequestMapping(value = "/findRecord",method = RequestMethod.POST)
    @ResponseBody
    public PageList<Record> findRecordPage(@RequestBody RecordQuery recordQuery){
        System.out.println(recordQuery);
        PageList<Record> pageList = recordService.queryPage(recordQuery);
        System.out.println(pageList.getTotal());
        List<Record> rows = pageList.getRows();
        rows.forEach(e-> System.out.println(e));
        return pageList;
    }

    /**
     * 去数据字典中查询字典明细
     * @return
     */
    @RequestMapping(value = "/findCertificateType",method = RequestMethod.POST)
    @ResponseBody
    public List<DictionaryDetail> findCertificateType(){
        return dictionaryDetailService.loadByType("certificatetype");
    }

    @RequestMapping(value = "/findExpireType",method = RequestMethod.POST)
    @ResponseBody
    public List<DictionaryDetail> findExpireType(){
        return dictionaryDetailService.loadByType("expiretype");
    }


}
