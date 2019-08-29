package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

import java.util.Date;

/**
 * @program: asm
 * @Date: 2019/8/10 12:00
 * @Author: Chenyogie
 * @Description:
 */
public class Revert extends BaseDomain {

    private String revertNum;
    private Borrow borrow;
    private User user;
    private Date revertdate;
    private String note;

    public String getRevertNum() {
        return revertNum;
    }

    public void setRevertNum(String revertNum) {
        this.revertNum = revertNum;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRevertdate() {
        return revertdate;
    }

    public void setRevertdate(Date revertdate) {
        this.revertdate = revertdate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Revert{" +
                "revertNum='" + revertNum + '\'' +
                ", borrow=" + borrow +
                ", user=" + user +
                ", revertdate=" + revertdate +
                ", note='" + note + '\'' +
                ", id=" + id +
                '}';
    }
}
