package com.ares.tools;


import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


/**
 * 传统结构项目中，shiro从cookie中读取sessionId以此来维持会话
 *现在前端：在ajax的请求头中传递sessionId
 * 因此重写shiro获取sessionId的方式。
 * */
public class AMSSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "X-TOKEN";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public AMSSessionManager(){
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //如果请求头中有 X-TOKEN 则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            //否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }
}
