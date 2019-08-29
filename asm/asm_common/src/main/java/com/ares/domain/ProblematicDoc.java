package com.ares.domain;

import com.ares.basic.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProblematicDoc extends BaseDomain {
    private Record doc;

    /*一方*/
    private DictionaryDetail dictionarydetail;

    private User user;
    private Date accidentdate = new Date();
    private String remark;

    public ProblematicDoc() {
    }

    public ProblematicDoc(Record doc, DictionaryDetail dictionarydetail, User user, Date accidentdate, String remark) {
        this.doc = doc;
        this.dictionarydetail = dictionarydetail;
        this.user = user;
        this.accidentdate = accidentdate;
        this.remark = remark;
    }

    public DictionaryDetail getDictionarydetail() {
        return dictionarydetail;
    }

    public void setDictionarydetail(DictionaryDetail dictionarydetail) {
        this.dictionarydetail = dictionarydetail;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getAccidentdate() {
        return accidentdate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setAccidentdate(Date accidentdate) {
        this.accidentdate = accidentdate;
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
        return "ProblematicDoc{" +
                "doc=" + doc +
                ", dictionarydetail=" + dictionarydetail +
                ", user=" + user +
                ", accidentdate=" + accidentdate +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                '}';
    }
}
