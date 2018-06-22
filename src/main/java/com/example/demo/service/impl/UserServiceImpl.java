package com.example.demo.service.impl;

import com.example.demo.domain.UserInfo;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService,Serializable {

    @Autowired
    private PasswordEncoder myPasswordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 此处可以去数据库查询账号密码等信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo = userMapper.selectUser(userInfo);
        if (userInfo != null) {
            // 返回UserInfo,用于与表单提交的用户名和密码进行对比。
            return userInfo;
        }
        return null;
    }


}
