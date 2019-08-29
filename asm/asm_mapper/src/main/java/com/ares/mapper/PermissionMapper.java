package com.ares.mapper;

import com.ares.basic.mapper.BaseMapper;
import com.ares.domain.Permission;
import com.ares.query.PermissionQuery;

import java.util.List;
import java.util.Set;

/**
 * @author Yao
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    public List<Permission> queryPerMenu(PermissionQuery permissionQuery);
    List<Permission> loadPermissionTree();
    Set<Permission> loadPermissionByUser();
}
