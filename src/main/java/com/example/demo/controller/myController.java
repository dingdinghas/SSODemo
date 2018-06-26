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
    @GetMapping("/recordUrl")
    public String recordUrl (HttpServletRequest request,String url) {
        request.getSession().setAttribute("url", url);
        return "redirect:/logins?url="+url;
    }
}
