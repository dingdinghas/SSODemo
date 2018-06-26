package com.example.demo.util;

import javax.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie getCookie(String token){
        Cookie cookie = new Cookie("NToken", token);
        cookie.setPath("/");
        cookie.setDomain("example.com");
        return cookie;
    }
}
