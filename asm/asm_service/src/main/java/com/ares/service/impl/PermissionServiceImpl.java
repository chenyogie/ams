package com.ares.service.impl;

import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.Permission;
import com.ares.mapper.PermissionMapper;
import com.ares.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yao
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Set<String> getPermissionsByUser(Long id) {
        Set<Permission> permissions = permissionMapper.loadPermissionByUser();
        Set<String> p = new HashSet<>();
        for (Permission permission : permissions) {
            String pp = permission + "";
            p.add(pp);
        }
        return p;
    }

    @Override
    public List<Permission> queryPermissionTree() {
        return permissionMapper.loadPermissionTree();
    }
}
