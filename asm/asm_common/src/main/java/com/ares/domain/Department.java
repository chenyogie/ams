package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

public class Department extends BaseDomain {

    //部门名称
    private String name;
    //联系电话
    private String phone;
    //传真
    private String fax;
    //部门路径
    private String deptPath;
    //上级部门,部门对象,多对一
    private Department superior;
    //部门主管,员工对象，多对一
    private User principal;
    //子部门
//    private List<Department> children = new ArrayList<>();
    //备注
    private String remark;
    //状态
    private Integer status;

    public Department() {
    }

    public Department(String name, String phone, String fax, String deptPath, Department superior, User principal, String remark, Integer status) {
        this.name = name;
        this.phone = phone;
        this.fax = fax;
        this.deptPath = deptPath;
        this.superior = superior;
        this.principal = principal;
        this.remark = remark;
        this.status = status;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    public Department getSuperior() {
        return superior;
    }

    public void setSuperior(Department superior) {
        this.superior = superior;
    }

    public User getPrincipal() {
        return principal;
    }

    public void setPrincipal(User principal) {
        this.principal = principal;
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
}