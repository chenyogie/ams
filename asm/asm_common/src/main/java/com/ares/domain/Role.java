package com.ares.domain;

import com.ares.basic.domain.BaseDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对应数据库中的角色表
 *
*
 * @author Yao*/

public class Role extends BaseDomain {
    /**
     * 角色编号
     * */
    private String sn;
    /**
     * 角色标识
     * */
    private Long identifying;
    /**
     * 角色名称
     * */
    private String name;
    /**
     * 角色使用状态
     *    0：启用
     *    -1：禁止使用
     * */
    private Integer status;

    /**
     * 角色与权限：多对多
     * */
    private List<Permission> permissions = new ArrayList<>();

    /**
     * 角色与菜单：多对多
     * */
    private List<Menu> menus = new ArrayList<>();

    public Role() {
    }

    public Role(String sn, Long identifying, String name, Integer status) {
        this.sn = sn;
        this.identifying = identifying;
        this.name = name;
        this.status = status;
    }

    public List<Map<Long,Long>> getPermissionMap(){
        return null;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role{" +
                "sn='" + sn + '\'' +
                ", identifying=" + identifying +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", id=" + id +
                ", menus=" + menus +
                "} " ;
    }
    /**
     * 用于维护中间表
     * */
    public List<Map<String,Long>> getPermissionsMap() {
        List<Map<String, Long>> rolrPermissions = new ArrayList<>();
        for (Permission permission : permissions) {
            Map<String, Long> rolePermisison = new HashMap<>();
            rolePermisison.put("role_identifying", this.identifying);
            rolePermisison.put("permission_identifying", permission.getIdentifying());
            rolrPermissions.add(rolePermisison);
        }
        return rolrPermissions;
    }
    public List<Map<String,Long>> getMenusMap() {
        List<Map<String, Long>> rolrMenus = new ArrayList<>();
        for (Menu menu : menus) {
            Map<String, Long> roleMenu = new HashMap<>();
            roleMenu.put("role_id", this.identifying);
            roleMenu.put("menu_id", menu.getSn());
            rolrMenus.add(roleMenu);
        }
        return rolrMenus;
    }
}
