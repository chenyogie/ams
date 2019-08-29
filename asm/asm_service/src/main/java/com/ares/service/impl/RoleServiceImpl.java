package com.ares.service.impl;

import com.ares.basic.service.impl.BaseServiceImpl;
import com.ares.domain.Role;
import com.ares.mapper.RoleMapper;
import com.ares.query.RoleQuery;
import com.ares.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Yao
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryPermission(RoleQuery roleQuery) {
        return roleMapper.queryPermission(roleQuery);
    }

    @Override
    public List<Role> queryRoleAndMenu(Serializable identifying) {
        return roleMapper.queryRoleAndMenu(identifying);
    }
    /**
     * 重写方法以便维护中间表
     * */
    @Override
    public void add(Role role) {
        roleMapper.save(role);

        List<Map<String, Long>> rolePermisisons = role.getPermissionsMap();
        List<Map<String, Long>> roleMenus = role.getMenusMap();
        if (rolePermisisons.size() > 0){
            roleMapper.saveRolePermissions(rolePermisisons);
        }
        if (roleMenus.size() > 0){
            roleMapper.saveRoleMenus(roleMenus);
        }
    }

    @Override
    public void del(Serializable id) {
        roleMapper.remove(id);

        roleMapper.removeRolePermissions(id);
        roleMapper.removeRoleMenus(id);
    }

    @Override
    public void update(Role role) {
        roleMapper.update(role);

        roleMapper.removeRolePermissions(role.getId());
        roleMapper.saveRolePermissions(role.getPermissionsMap());

        roleMapper.removeRoleMenus(role.getId());
        roleMapper.saveRoleMenus(role.getMenusMap());
    }
}
