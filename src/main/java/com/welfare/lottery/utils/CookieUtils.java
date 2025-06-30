package com.welfare.lottery.utils;

import jakarta.servlet.http.Cookie;

import java.lang.reflect.Field;


/**
 * @author xuchengcheng
 * @since 2025/6/19
 */
public class CookieUtils {

    /**
     * 将 HttpServletRequest 中的 Cookie 映射到指定的对象
     */
    public static <T> T parseCookiesToObject(Cookie[] cookies, Class<T> clazz) throws Exception {
        if (cookies == null) {
            return null;
        }

        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();

            try {
                Field field = clazz.getDeclaredField(cookieName);
                field.setAccessible(true);

                // 简单类型转换（可扩展）
                if (field.getType() == Long.class || field.getType() == long.class) {
                    field.set(instance, Long.valueOf(cookieValue));
                } else if (field.getType() == Integer.class || field.getType() == int.class) {
                    field.set(instance, Integer.valueOf(cookieValue));
                } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                    field.set(instance, Boolean.valueOf(cookieValue));
                } else {
                    field.set(instance, cookieValue);
                }
            } catch (NoSuchFieldException ignored) {
                // 忽略不匹配字段
            }
        }

        return instance;
    }
}
