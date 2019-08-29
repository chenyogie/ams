package com.ares.domain;

import com.ares.basic.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Record extends BaseDomain {

    //档案地点·
    private Site site;
    //档案编号
    private String num;
    //档案标题
    private String headline;
    //建表人
    private User createuser;
    //建表时间
    private Date createDate=new Date();
    //修改时间
    private Date updateDate;
    //修改表人员
    private User updateuser;
    //借阅状态
    private Boolean borrowstatus=false;
    //过期时间
    private Date expirationdate;
    //是否提醒
    private Boolean expirationnotice=false;
    //提前通知天数
    private Date advancedate;
    //档案内容
    private String content;
    //简介
    private String introduce;
    //是否常用
    private Boolean oftenuse=false;
    //文件编号
    private String f_num;
    //来文部门
    private Department c_department;
    //发文部门
    private Department g_department;
    //批准时间
    private Date ratify;
    //鉴定时间
    private Date authenticate;
    //产品批号
    private String product_num;
    //原料批号
    private String raw_num;
    //档案数量
    private Integer f_count;
    //档案页数
    private Integer f_page;
    //专利号
    private String patent;
    //年度检验人员
    private User checkUser;
    //档案状态
    private Integer f_status;
    //档案类型
    private RecordType type;
    //发文人
    private User ogm_user;


    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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

    public User getCreateuser() {
        return createuser;
    }

    public void setCreateuser(User createuser) {
        this.createuser = createuser;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUpdateDate() {
        return updateDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public User getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(User updateuser) {
        this.updateuser = updateuser;
    }

    public Boolean getBorrowstatus() {
        return borrowstatus;
    }

    public void setBorrowstatus(Boolean borrowstatus) {
        this.borrowstatus = borrowstatus;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getExpirationdate() {
        return expirationdate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Boolean getExpirationnotice() {
        return expirationnotice;
    }

    public void setExpirationnotice(Boolean expirationnotice) {
        this.expirationnotice = expirationnotice;
    }

    public Date getAdvancedate() {
        return advancedate;
    }

    public void setAdvancedate(Date advancedate) {
        this.advancedate = advancedate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Boolean getOftenuse() {
        return oftenuse;
    }

    public void setOftenuse(Boolean oftenuse) {
        this.oftenuse = oftenuse;
    }

    public String getF_num() {
        return f_num;
    }

    public void setF_num(String f_num) {
        this.f_num = f_num;
    }

    public Department getC_department() {
        return c_department;
    }

    public void setC_department(Department c_department) {
        this.c_department = c_department;
    }

    public Department getG_department() {
        return g_department;
    }

    public void setG_department(Department g_department) {
        this.g_department = g_department;
    }

    public Date getRatify() {
        return ratify;
    }

    public void setRatify(Date ratify) {
        this.ratify = ratify;
    }

    public Date getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Date authenticate) {
        this.authenticate = authenticate;
    }

    public String getProduct_num() {
        return product_num;
    }

    public void setProduct_num(String product_num) {
        this.product_num = product_num;
    }

    public String getRaw_num() {
        return raw_num;
    }

    public void setRaw_num(String raw_num) {
        this.raw_num = raw_num;
    }

    public Integer getF_count() {
        return f_count;
    }

    public void setF_count(Integer f_count) {
        this.f_count = f_count;
    }

    public Integer getF_page() {
        return f_page;
    }

    public void setF_page(Integer f_page) {
        this.f_page = f_page;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public User getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(User checkUser) {
        this.checkUser = checkUser;
    }

    public Integer getF_status() {
        return f_status;
    }

    public void setF_status(Integer f_status) {
        this.f_status = f_status;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public User getOgm_user() {
        return ogm_user;
    }

    public void setOgm_user(User ogm_user) {
        this.ogm_user = ogm_user;
    }

    public Record() {
    }

    @Override
    public String toString() {
        return "Record{" +
                "site=" + site +
                ", num='" + num + '\'' +
                ", headline='" + headline + '\'' +
                ", createuser=" + createuser +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", updateuser=" + updateuser +
                ", borrowstatus=" + borrowstatus +
                ", expirationdate=" + expirationdate +
                ", expirationnotice=" + expirationnotice +
                ", advancedate=" + advancedate +
                ", content='" + content + '\'' +
                ", introduce='" + introduce + '\'' +
                ", oftenuse=" + oftenuse +
                ", f_num='" + f_num + '\'' +
                ", c_department=" + c_department +
                ", g_department=" + g_department +
                ", ratify=" + ratify +
                ", authenticate=" + authenticate +
                ", product_num='" + product_num + '\'' +
                ", raw_num='" + raw_num + '\'' +
                ", f_count=" + f_count +
                ", f_page=" + f_page +
                ", patent='" + patent + '\'' +
                ", checkUser=" + checkUser +
                ", f_status=" + f_status +
                ", type=" + type +
                ", ogm_user=" + ogm_user +
                '}';
    }
}
