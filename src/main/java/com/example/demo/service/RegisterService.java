package com.example.demo.service;

import com.example.demo.domain.UserInfo;

import javax.servlet.http.HttpServletRequest;

public interface RegisterService {

    Long registerUser(UserInfo userInfo);

    String validParam(String ValidValue);

    String getKey(HttpServletRequest request);
}
