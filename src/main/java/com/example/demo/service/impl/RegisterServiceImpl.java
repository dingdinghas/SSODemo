package com.example.demo.service.impl;

import com.example.demo.constant.BaseConstant;
import com.example.demo.domain.UserInfo;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.RegisterService;
import com.example.demo.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    public UserMapper userMapper;
    @Autowired
    private PasswordEncoder myPasswordEncoder;
    @Override
    public String registerUser(UserInfo userInfo) {

        userInfo.setPassword(myPasswordEncoder.encode(userInfo.getPassword()));
        userInfo.setEnable(1);
        userMapper.insertUser(userInfo);
        return BaseConstant.SUCCESS;
    }

    @Override
    public String validParam(String validValue) {
        UserInfo userInfo = new UserInfo();
        if (!StringUtils.isEmpty(validValue)) {
            String [] arr = validValue.split(";");
            if ("username".equals(arr[0])) {
                userInfo.setUsername(arr[1]);
            } else if ("mobile".equals(arr[0])){
                userInfo.setMobile(arr[1]);
            } else if ("email".equals(arr[0])){
                userInfo.setEmail(arr[1]);
            }
        }
        userInfo = userMapper.selectUser(userInfo);
        if (userInfo == null) {
            return BaseConstant.SUCCESS;
        }
        return BaseConstant.FAIL;
    }

    @Override
    public String getKey(HttpServletRequest request) {
        String keyString = EncryptUtil.getKeyString();
        HttpSession session = request.getSession();
        session.setAttribute("keyString", keyString);
        return keyString;
    }
}
