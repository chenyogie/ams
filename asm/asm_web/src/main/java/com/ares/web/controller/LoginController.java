package com.ares.web.controller;

import com.ares.basic.AjaxResult;
import com.ares.domain.User;
import com.ares.tools.GetUserContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public AjaxResult login(@RequestBody User user){
        Subject currentloginUser = SecurityUtils.getSubject();
        if (!currentloginUser.isAuthenticated()){
            try {
                UsernamePasswordToken passwordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
                currentloginUser.login(passwordToken);
            }catch (UnknownAccountException e){
                e.printStackTrace();
                return AjaxResult.getInstance("用户名或密码错误！"+e.getMessage()).setSuccess(false);
            }catch (IncorrectCredentialsException e){
                e.printStackTrace();
                return AjaxResult.getInstance("用户名或密码错误！"+e.getMessage()).setSuccess(false);
            }
            catch (AuthenticationException e){
                e.printStackTrace();
                return AjaxResult.getInstance("登录异常！"+e.getMessage()).setSuccess(false);
            }
        }
        Map<String,Object> map = new HashMap<>();
        System.out.println(currentloginUser.isAuthenticated());
        User principal = (User) currentloginUser.getPrincipal();
        GetUserContextUtil.putUser(principal);
        principal.setPassword(null);
        map.put("user", principal);
        map.put("token", currentloginUser.getSession().getId());
        System.out.println(currentloginUser.getSession().getId());
        return AjaxResult.getInstance().setResultObj(map);
    }
}
