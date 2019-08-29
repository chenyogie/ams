package com.ares.mapper;

import com.ares.basic.mapper.BaseMapper;
import com.ares.domain.Record;

public interface RecordMapper extends BaseMapper<Record> {
    void changeDocState(Record record);
    /**
     * 修改档案的借阅状态
     * @param record
     */
    /*void (Record record);*/
}
