package com.ares.mapper;

import com.ares.basic.mapper.BaseMapper;
import com.ares.domain.Permission;
import com.ares.domain.Role;
import com.ares.query.RoleQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Yao
 */
public interface RoleMapper extends BaseMapper<Role> {
    public List<Role> queryPermission(RoleQuery roleQuery);
    public List<Permission>  queryRoleAndPermission(Serializable role_identifying);
    public Permission queryPer(Serializable id);

    public List<Role> queryRoleAndMenu(Serializable identifying);

    public List<Role> queryRoleAndPermissionAndMenu(RoleQuery roleQuery);

    void saveRolePermissions(List<Map<String, Long>> rolePermisisons);

    void saveRoleMenus(List<Map<String, Long>> roleMenus);

    void removeRolePermissions(Serializable id);

    void removeRoleMenus(Serializable id);
}
