package com.xg.supermarket.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName LoginControlle.java
 * @Description 登录控制器
 * @createTime 2021年07月01日 08:22:00
 */
@Controller
public class LoginControlle {
    @RequestMapping("login")
    public String login(){
        return "login_v2";
    }

    @PostMapping("doLogin")
    public String doLogin(String username,@RequestParam(required = true) String password){
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        System.err.println(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
        } catch ( UnknownAccountException uae ) {
            //用户名不在系统中，向他们显示错误消息？
            System.out.println("用户名不存在");
        } catch ( IncorrectCredentialsException ice ) {
            //密码不匹配，再试一次？
            System.out.println("密码不匹配");
        } catch ( LockedAccountException lae ) {
            //该用户名的帐户被锁定 - 无法登录。给他们看信息？
         } catch ( AuthenticationException ae ) {
            //意外情况 - 错误？
            System.out.println("错误");
        }
        return "redirect:/index";

    }
    @GetMapping("outLogin")
    public String outLogin(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

}
