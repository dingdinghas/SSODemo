package com.example.demo.controller;

import com.example.demo.domain.UserInfo;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type My controller.
 */
@Controller
public class myController {
    /**
     * Record url string.
     *该controller是用来记录原始url，用来登录或者注册成功后重定向的url
     * 前端拦截url为
     * http://sso.example.com?url=originUrl
     * originUrl被记录到session中
     * 重定向到一个需要认证的logins 方法中
     * 触发认证后登录成功跳转至该controller，生成token后再重定向至originUrl
     *
     * @param request the request
     * @param url     the url
     * @return the string
     */
    @GetMapping("/recordUrl")
    public String recordUrl (HttpServletRequest request,String url) {
        request.getSession().setAttribute("url", url);
        return "redirect:/logins?url="+url;
    }
}
