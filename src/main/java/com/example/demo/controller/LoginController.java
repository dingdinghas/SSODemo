package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.JWTUtil;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @GetMapping("/loginPage")
    public String loginPage (HttpServletRequest request,String url) {
        return "/loginPage.html?url="+(String) request.getSession().getAttribute("url");
    }
    @GetMapping("/logins")
    public String loginPage (HttpServletRequest request, HttpServletResponse response, String url) {
        SecurityContextImpl spring_security_context = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        UserInfo userInfo = (UserInfo) spring_security_context.getAuthentication().getPrincipal();
        String token = JWTUtil.generateJWTToken(userInfo.getId()+"", 3600 * 6);
        Cookie cookie = CookieUtil.getCookie(token);
        response.addCookie(cookie);
        return "redirect:http://" + url;
    }
}
