package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

/**
 * @program: asm
 * @Date: 2019/8/3 14:42
 * @Author: Chenyogie
 * @Description: 借阅人实体类
 */
public class Borrower extends BaseDomain {

    //借阅人姓名
    private String name;
    //证件类型
    private String certificateType;
    //证件编号
    private String certificateNum;
    //联系方式
    private String phone;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "name='" + name + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", certificateNum='" + certificateNum + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                '}';
    }
}
