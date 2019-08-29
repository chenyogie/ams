package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

/**
 * 档案地点
 */
public class Site extends BaseDomain {
    //地点
    private String location;
    //存档名称
    private String name;
    //联系人
    private String linkman;
    //备注信息
    private String remark;

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

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Site{" +
                "location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", linkman='" + linkman + '\'' +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                '}';
    }
}
