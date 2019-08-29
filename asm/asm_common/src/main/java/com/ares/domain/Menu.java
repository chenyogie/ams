package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应数据库中的菜单表menu
 *
 * @author Yao*/

public class Menu extends BaseDomain {
    /**
    *菜单编号
    * */
    private Long sn;
    /**
     * 菜单名字
     * */
    private String name;
    /**
     * 资源路径
     * */
    private String url;
    /**
     * 菜单图标
     * */
    private String icon;
    /**
     * 父菜单id
     * */
    private Menu parent_id;
    private List<Menu> children = new ArrayList<>();
    /**
     * 菜单使用状态
     * */
    private Integer status;

    public Menu() {
    }

    public Menu(Long sn, String name) {
        this.sn = sn;
        this.name = name;
    }

    public Menu(Long sn, String name, String url, String icon, Menu parent_id, Integer status) {
        this.sn = sn;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.parent_id = parent_id;
        this.status = status;
    }

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getparent_id() {
        return parent_id;
    }

    public void setparent_id(Menu parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "sn=" + sn +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", id=" + id +
                "} " ;
    }
}
