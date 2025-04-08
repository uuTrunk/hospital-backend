package com.uutrunk.hospitallogin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {
    // 使用BCrypt加密算法（符合OWASP安全规范）
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 对密码进行加密处理
     * @param rawPassword 明文密码
     * @return 加密后的密码
     */
    public static String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 验证密码是否匹配
     * @param rawPassword 用户输入的明文密码
     * @param encodedPassword 数据库存储的加密密码
     * @return 验证结果
     */
    public static boolean verify(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}