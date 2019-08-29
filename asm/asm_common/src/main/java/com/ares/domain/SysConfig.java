package com.ares.domain;


import com.ares.basic.domain.BaseDomain;

public class SysConfig extends BaseDomain {
    //系统名称
    private String sysName;
    //公司名称
    private String companyName;
    //公司电话
    private String companyPhone;
    //公司传真
    private String companyFax;
    //公司地址
    private String companySite;
    //公司网址
    private String companyURL;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanySite() {
        return companySite;
    }

    public void setCompanySite(String companySite) {
        this.companySite = companySite;
    }

    public String getCompanyURL() {
        return companyURL;
    }

    public void setCompanyURL(String companyURL) {
        this.companyURL = companyURL;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
                "sysName='" + sysName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", companyFax='" + companyFax + '\'' +
                ", companySite='" + companySite + '\'' +
                ", companyURL='" + companyURL + '\'' +
                '}';
    }
}
