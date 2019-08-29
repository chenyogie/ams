package com.ares.domain;

import com.ares.basic.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class DestroyDoc extends BaseDomain {
    private Record doc;
    private User user;
    private Date destroydate = new Date();
    /*一方*/
    private DictionaryDetail dictionarydetail;
    private String remark;

    public DestroyDoc() {
    }

    public DestroyDoc(Record doc, User user, Date destroydate, DictionaryDetail dictionarydetail, String remark) {
        this.doc = doc;
        this.user = user;
        this.destroydate = destroydate;
        this.dictionarydetail = dictionarydetail;
        this.remark = remark;
    }

    //设置时间日期格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getDestroydate() {
        return destroydate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setDestroydate(Date destroydate) {
        this.destroydate = destroydate;
    }

    public DictionaryDetail getDictionarydetail() {
        return dictionarydetail;
    }

    public void setDictionarydetail(DictionaryDetail dictionarydetail) {
        this.dictionarydetail = dictionarydetail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Record getDoc() {
        return doc;
    }

    public void setDoc(Record doc) {
        this.doc = doc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DestroyDoc{" +
                "doc=" + doc +
                ", user=" + user +
                ", destroydate=" + destroydate +
                ", dictionarydetail=" + dictionarydetail +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                '}';
    }
}
