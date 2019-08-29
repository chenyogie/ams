package com.ares.shiro;

import com.ares.domain.User;
import com.ares.service.IPermissionService;
import com.ares.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


/**
 * 自己定义的安全管理器
 *      用于用户的权限验证与授权
 * */
public class AMSRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPermissionService permissionService;

    /**
     * 用于权限的授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        Set<String> permissions = permissionService.getPermissionsByUser(principal.getId());
        //创建权限对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }
    /**
     * 用于权限的验证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User loginUser = userService.queryByName(username);
        if (loginUser == null){
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes("zclyx");
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(), salt, getName());
        return simpleAuthenticationInfo;
    }
}
