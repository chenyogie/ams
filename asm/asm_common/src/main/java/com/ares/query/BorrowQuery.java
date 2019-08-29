package com.ares.query;

import com.ares.basic.query.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: asm
 * @Date: 2019/8/5 22:57
 * @Author: Chenyogie
 * @Description:
 */
public class BorrowQuery extends BaseQuery {
    private Date begindate;
    private Date enddate;
    private String typename;
    private String num;
    private String borrowNum;
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getBegindate() {
        return begindate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(String borrowNum) {
        this.borrowNum = borrowNum;
    }

    @Override
    public String toString() {
        return "BorrowQuery{" +
                "begindate=" + begindate +
                ", enddate=" + enddate +
                ", typename='" + typename + '\'' +
                ", num='" + num + '\'' +
                ", status='" + status + '\'' +
                ", borrowNum='" + borrowNum + '\'' + super.toString()+
                '}';
    }
}
