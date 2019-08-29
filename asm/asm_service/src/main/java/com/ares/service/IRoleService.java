package com.ares.service;

import com.ares.basic.service.IBaseService;
import com.ares.domain.Role;
import com.ares.query.RoleQuery;

import java.io.Serializable;
import java.util.List;

public interface IRoleService extends IBaseService<Role> {
    public List<Role> queryPermission(RoleQuery roleQuery);
    public List<Role> queryRoleAndMenu(Serializable identifying);
}
