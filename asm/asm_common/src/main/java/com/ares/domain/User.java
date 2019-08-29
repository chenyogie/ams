package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;


public class User extends BaseDomain {
    //用户名
    private String username;
    //密码
    private String password;
    //真实姓名
    private String name;
    //身份证号码
    private String idcard;

    //所属部门(部门对象)
    private Department dept;
    //入职时间
    private Date hiredate = new Date();
    //联系电话
    private String phone;
    //学历(数据字典里取)
    private String education;
    //居住地
    private String address;
    //性别(数据字典里取)
    private Boolean sex;
    //出生日期
    private Date   birth;
    //备注
    private String remark;
    //状态
    private Integer status;

    public User() {
    }

    private List<Role> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User(String username, String password, String name, String idcard, Department dept, Date hiredate, String telephone, String education, String address, Boolean sex, Date birth, String remark, Integer status) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.idcard = idcard;
        this.dept = dept;
        this.hiredate = hiredate;
        this.education = education;
        this.address = address;
        this.sex = sex;
        this.birth = birth;
        this.remark = remark;
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}