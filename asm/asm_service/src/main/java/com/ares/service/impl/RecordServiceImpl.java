package com.ares.service.impl;

import com.ares.basic.PageList;
import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.Record;
import com.ares.mapper.RecordMapper;
import com.ares.mapper.RecordMapper;
import com.ares.query.RecordQuery;
import com.ares.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ares.util.ExpireUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: ssm_parent
 * @Date: 2019/7/31 22:47
 * @Author: Chenyogie
 * @Description:
 */
@Service
public class RecordServiceImpl extends BaseServiceImpl<Record> implements IRecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void changeDocState(Record record) {
        recordMapper.changeDocState(record);
    }

    @Override
    public PageList<Record> queryPage(RecordQuery query) {
        Long count = recordMapper.queryCount(query);
        if (count == 0) {
            return new PageList<>();
        }
        List<Record> list = recordMapper.query(query);
        if (query.getSncode() != null) {
            List<Record> data = ExpireUtil.filterByDate(query.getSncode(), list);
            return new PageList<>(Long.valueOf(Integer.toString(data.size())), data);
        }else{
            return new PageList<>(count,list);
        }
    }
}
