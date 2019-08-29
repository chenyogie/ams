package com.ares.query;

import com.ares.basic.query.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DestroyDocQuery extends BaseQuery {
    //开始时间
    private Date beginDate;
    //结束时间
    private Date endDate;

    public Date getBeginDate() {
        return beginDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DestroyDocQuery{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }
}
