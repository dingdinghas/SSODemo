package com.example.demo.mapper;

import com.example.demo.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * The interface User mapper.
 */
@Mapper
public interface UserMapper {
    /**
     * Select user user info.
     *
     * @param userInfo the user info
     * @return the user info
     */
    UserInfo selectUser(UserInfo userInfo);

    /**
     * Insert user.
     *
     * @param userInfo the user info
     */
    void insertUser(UserInfo userInfo);
}
