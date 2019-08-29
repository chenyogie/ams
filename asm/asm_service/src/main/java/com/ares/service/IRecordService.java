package com.ares.service;

import com.ares.basic.PageList;
import com.ares.basic.service.IBaseService;
import com.ares.domain.Record;
import com.ares.query.RecordQuery;

/**
 * @program: ssm_parent
 * @Date: 2019/7/31 22:46
 * @Author: Chenyogie
 * @Description:
 */
public interface IRecordService extends IBaseService<Record> {
    /**
     * 改写方法
     * @param query 根据具体的query对象查询分页数据
     * @return
     */
    PageList<Record> queryPage(RecordQuery query);
    void changeDocState(Record record);
}
