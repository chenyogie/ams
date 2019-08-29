package com.ares.domain.vo;

import com.ares.basic.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @program: asm
 * @Date: 2019/8/5 16:02
 * @Author: Chenyogie
 * @Description:
 */
public class BorrowVo extends BaseDomain {

    private String borrowNum;
    private String num;
    private String headline;
    private String typename;
    private String location;
    private String name;
    private Boolean status;
    private Date date;
    private Date requiredate;

    public String getValue(){
        return borrowNum;
    }

    public String getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(String borrowNum) {
        this.borrowNum = borrowNum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getRequiredate() {
        return requiredate;
    }

    public void setRequiredate(Date requiredate) {
        this.requiredate = requiredate;
    }

    @Override
    public String toString() {
        return "BorrowVo{" +
                "borrowNum='" + borrowNum + '\'' +
                ", num='" + num + '\'' +
                ", headline='" + headline + '\'' +
                ", typename='" + typename + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", requiredate=" + requiredate +
                ", id=" + id +
                '}';
    }
}
