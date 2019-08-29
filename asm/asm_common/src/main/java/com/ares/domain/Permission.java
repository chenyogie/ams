package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应数据库中的权限表permission
 *
 * @author Yao*/

public class Permission extends BaseDomain {
    /**
     * 权限编号
     * */
    private String sn;
    /**
     *权限标识
     * */
    private Long identifying;
    /**
     *权限名称
     * */
    private String name;
    /**
     *资源路径
     * */
    private String url;
    /**
     *权限使用状态
     * */
    private Integer status;
    /**
     *父级权限id
     * */
    private Permission parent_id;
    /**
     *菜单id
     * */
    private Long menu_id;

    /**
     * 权限与菜单：一对多
     * */
    private List<Menu> menus = new ArrayList<>();

    public Permission() {
    }

    public Permission(Long pIdentifying, String pName) {
        this.identifying = pIdentifying;
        this.name = pName;
    }

    public Permission(String sn, Long identifying, String name, String url, Integer status) {
        this.sn = sn;
        this.identifying = identifying;
        this.name = name;
        this.url = url;
        this.status = status;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getIdentifying() {
        return identifying;
    }

    public void setIdentifying(Long identifying) {
        this.identifying = identifying;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Permission getParent_id() {
        return parent_id;
    }

    public void setParent_id(Permission parent_id) {
        this.parent_id = parent_id;
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "sn='" + sn + '\'' +
                ", identifying=" + identifying +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", id=" + id +
                "} " ;
    }
}
