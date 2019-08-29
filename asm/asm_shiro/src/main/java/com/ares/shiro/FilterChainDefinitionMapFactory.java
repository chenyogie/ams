package com.ares.shiro;


import com.ares.domain.Permission;
import com.ares.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 自己定义的工厂
 *      用于设置请求资源的放行与拦截
 * */
public class FilterChainDefinitionMapFactory {

    @Autowired
    private IPermissionService permissionService;
    public Map<String, String> createFilterChainDefinitionMap() {

        //LinkedHashMap是一个有序的集合
        Map<String, String> maps = new LinkedHashMap<>();
        //设置放行，无需验证
        maps.put("/login", "anon");
        maps.put("*.js", "anon");
        maps.put(".css", "anon");
        maps.put("/css/**", "anon");
        maps.put("/js/**", "anon");
        maps.put("/json/**", "anon");
        maps.put("/element-ui/**", "anon");
        maps.put("image/**", "anon");
        //设置权限

        List<Permission> list = permissionService.queryAll();
        list.forEach(permission -> maps.put(permission.getUrl(),"AMSPermissions["+permission.getSn()+"]" ));
        //拦截所有请求
        maps.put("/**", "authc");
        return maps;
    }
}
