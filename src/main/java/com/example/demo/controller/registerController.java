package com.example.demo.controller;

import com.example.demo.domain.UserInfo;
import com.example.demo.service.RegisterService;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Register controller.
 */
@Controller
@RequestMapping("/register")
public class registerController {
    /**
     * The Register service.
     */
    @Autowired
    public RegisterService registerService;

    /**
     * Register page view.
     *
     * @return the string
     */
    @GetMapping("/page")
    public String registerPage (HttpServletRequest request) {
        String url = (String) request.getSession().getAttribute("url");
        return "redirect:/registerPage.html?url="+ url;
    }


    /**
     * Register user string.
     *
     * @param userInfo the user info
     * @return the string
     */
    @PostMapping("/user")
    public String registerUser (UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        String url = (String) request.getSession().getAttribute("url");
        Long id = registerService.registerUser(userInfo);
        if (id != null) {
            String token = JWTUtil.generateJWTToken(id+"", 3600 * 6);
            Cookie cookie = CookieUtil.getCookie(token);
            response.addCookie(cookie);
           return "redirect:http://" +url;
        } else {
            return "redirect:/registerPage.html?url="+ url;
        }
    }

    /**
     * Valid validValue
     *
     * @param validValue 校验参数值，校验项;参数值
     * @return the string
     */
    @GetMapping ("/valid")
    @ResponseBody
    public String validUserName (String validValue) {
        return registerService.validParam(validValue);
    }
    @GetMapping("/key")
    @ResponseBody
    public String getKey (HttpServletRequest request) {
        return registerService.getKey(request);
    }
    @GetMapping("/check")
    @ResponseBody
    public String check (HttpServletRequest request) {
        return registerService.getKey(request);
    }
}
