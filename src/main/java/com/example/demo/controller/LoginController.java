package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @GetMapping("/loginPage")
    public String loginPage (HttpServletRequest request) {
        return "loginPage.html";
    }

}
