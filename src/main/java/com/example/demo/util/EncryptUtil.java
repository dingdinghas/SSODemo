package com.example.demo.util;

import com.example.demo.constant.BaseConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.DigestUtils;

/**
 * The type Encrypt util.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EncryptUtil {
    public static final String oriKey = "F4C57DFEFCB4ABC88C262960EF45CB78==";

    /**
     * Gets key string.
     * 注册前后端加密解密key，每次请求都会产生一个新的key
     *
     * @return the key string
     */
    public static  String getKeyString () {
        long time = System.currentTimeMillis();
        return DigestUtils.md5DigestAsHex((Math.random() + oriKey + time).getBytes());
    }
}
