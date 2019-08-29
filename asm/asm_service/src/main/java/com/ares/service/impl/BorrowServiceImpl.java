package com.ares.service.impl;

import com.ares.basic.PageList;
import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.Borrow;
import com.ares.domain.Record;
import com.ares.domain.vo.BorrowVo;
import com.ares.mapper.BorrowMapper;
import com.ares.mapper.BorrowerMapper;
import com.ares.mapper.RecordMapper;
import com.ares.query.BorrowQuery;
import com.ares.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/3 16:32
 * @Author: Chenyogie
 * @Description:
 */

@Service
public class BorrowServiceImpl extends BaseServiceImpl<Borrow> implements IBorrowService {

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BorrowerMapper borrowerMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void add(Borrow borrow) {

        /*borrow.setRequiredate(borrow.getDate());
        //获取calendar实例
        Calendar calendar=Calendar.getInstance();
        //设置当前Calendar时间
        calendar.setTime(borrow.getDate());
        //让日期加3
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+3);
        //设置时间
        borrow.setRequiredate(calendar.getTime());*/

        //如果借阅人的id存在，说明该人员已经在数据库中了
        if(borrow.getBorrower().getId() != null){
            borrowMapper.save(borrow);
        }else{
            //保存借阅人信心[返回主键]
            borrowerMapper.save(borrow.getBorrower());
            //设置当前登录用户为操作当前数据的工作人员
            //System.out.println(1/0);
            borrow.setUserId(1L);
        }
        //保存借阅记录
        borrowMapper.save(borrow);

        //修改档案的借阅状态
        Record record = recordMapper.loadOne(borrow.getRecordId());
        record.setBorrowstatus(false);
        recordMapper.update(record);
    }

    @Override
    public PageList queryPage(BorrowQuery query) {
        Long count = borrowMapper.queryCount(query);
        if(count==0){
            return new PageList<>();
        }
        List<BorrowVo> list = borrowMapper.query(query);
        return new PageList<>(count,list);
    }
}
