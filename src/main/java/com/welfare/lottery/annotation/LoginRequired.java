package com.welfare.lottery.annotation;

import java.lang.annotation.*;

/**
 * @author xuchengcheng
 * 需要具备登录状态的接口注解
 * @since 2025/6/19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {

}
