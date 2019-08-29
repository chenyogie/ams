package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

import java.util.Date;

/**
 * @program: asm
 * @Date: 2019/8/3 14:40
 * @Author: Chenyogie
 * @Description: 借阅记录
 */
public class Borrow extends BaseDomain {

    //借阅编号
    private String borrowNum;
    //借阅时间[默认为系统当前时间]
    private Date date = new Date();
    //要求归还时间
    private Date requiredate;
    //借阅状态：0，未归还；1已归还
    private Boolean status;
    //备注信息
    private String notes;

    //借阅人
    private Borrower borrower;

    //工作人员
    private Long userId;

    //借阅的档案id
    private Long recordId;

    public Borrow() {
    }

    public String getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(String borrowNum) {
        this.borrowNum = borrowNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRequiredate() {
        return requiredate;
    }

    public void setRequiredate(Date requiredate) {
        this.requiredate = requiredate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "borrowNum='" + borrowNum + '\'' +
                ", date=" + date +
                ", requiredate=" + requiredate +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", borrower=" + borrower +
                ", userId=" + userId +
                ", recordId=" + recordId +
                ", id=" + id +
                '}';
    }
}
