package com.welfare.lottery.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author xuchengcheng
 * 密码的工具类
 * @since 2025/6/23
 */
public class PasswordUtils {

    // 使用 BCryptPasswordEncoder 进行加密和校验
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();


    public static String encryptPassword(String password) {
        return ENCODER.encode(password);
    }

    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}
