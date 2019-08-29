package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.domain.Borrow;
import com.ares.domain.Record;
import com.ares.domain.Revert;
import com.ares.domain.User;
import com.ares.service.IBorrowService;
import com.ares.service.IRecordService;
import com.ares.service.IRevertService;
import com.ares.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: asm
 * @Date: 2019/8/10 14:13
 * @Author: Chenyogie
 * @Description:
 */
@Controller
@CrossOrigin
@RequestMapping("/revert")
public class RevertController {

    @Autowired
    private IRevertService revertService;

    @Autowired
    private IRecordService recordService;

    @Autowired
    private IBorrowService borrowService;

    /**
     * 添加归还记录的方法
     * @param revert
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult add(@RequestBody Revert revert){
        System.out.println("接收的参数："+revert.toString());
        try {
            //生成一个归还记录的编号
            revert.setRevertNum(UUIDUtil.createRevertNum());
            //设置当前操作的工作人员
            User user = new User();
            user.setId(1L);
            revert.setUser(user);

            //将借阅记录的状态设置为已归还状态
            Borrow borrow = borrowService.queryOne(revert.getBorrow().getId());
            System.out.println("根据id查询borrow对象："+borrow);
            borrow.setStatus(true);
            borrowService.update(borrow);

            //将档案的状态修改为可借阅的状态
            Record record = recordService.queryOne(borrow.getRecordId());
            record.setBorrowstatus(true);
            recordService.update(record);

            revertService.add(revert);
            return AjaxResult.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getInstance("添加失败，原因是："+e.getMessage());
        }
    }

}
