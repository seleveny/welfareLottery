package com.welfare.lottery.common;

/**
 * @author xuchengcheng
 * @since 2025/6/19
 */
public class UserContext {

    /**
     * 记录用于传递的用户登录信息上下文
     */
    private static final ThreadLocal<UserLoginInfo> userLoginInfo = new ThreadLocal<>();

    public static UserLoginInfo getUserLoginInfo() {
        return userLoginInfo.get();
    }

    public static void setUserLoginInfo(UserLoginInfo userLoginInfo) {
        UserContext.userLoginInfo.set(userLoginInfo);
    }

    public static void clear() {
        userLoginInfo.remove();
    }
}
