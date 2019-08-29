package com.ares.service;

import com.ares.basic.service.IBaseService;
import com.ares.domain.Permission;

import java.util.List;
import java.util.Set;

public interface IPermissionService extends IBaseService<Permission> {
    Set<String> getPermissionsByUser(Long id);
    List<Permission> queryPermissionTree();
}
