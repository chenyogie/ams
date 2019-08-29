package com.ares.query;

import com.ares.basic.query.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProblematicDocQuery extends BaseQuery {
    //开始时间
    private Date beginDate;
    //结束时间
    private Date endDate;
    //状态
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProblematicDocQuery{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}
