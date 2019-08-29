package com.ares.tools;

import com.ares.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class GetUserContextUtil {
    private static final String LOGINUSER = "loginUser";

    public static void putUser(User user){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Object principal = subject.getPrincipal();
        session.setAttribute(LOGINUSER,user);
    }

    public static User getUser(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User attribute = (User)session.getAttribute(LOGINUSER);
        return attribute;
    }
}
