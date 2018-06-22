package com.example.demo.controller;

import com.example.demo.domain.UserInfo;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class myController {


    @GetMapping("/redirectUrl")
    public String root (HttpSession session, HttpServletRequest request, HttpServletResponse response, String url) {
        SecurityContextImpl spring_security_context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UserInfo userInfo = (UserInfo) spring_security_context.getAuthentication().getPrincipal();
        request.getSession().setAttribute("user", userInfo);
        Cookie cookie = new Cookie("NToken", userInfo.getUsername().toString());
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return "redirect:http://" + url;
    }
}
