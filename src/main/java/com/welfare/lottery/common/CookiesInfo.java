package com.welfare.lottery.common;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author xuchengcheng
 * 用户登录后cookies解析之后的内容
 * @since 2025/6/19
 */
@Data
public class CookiesInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 2507906675352839173L;

    //数据库自增id
    private Long id;

    //用户的名称
    private String name;

    //autoToken（应该能够解析出和传递过来的内容匹配的内容数据）
    private String authToken;

    //用户的认证码
    private String verifyCode;

    //cookies的过期时间
    private DateTime expireTime;

    private String telephone;

    private String roleType;

    private String level;

    private String regionCode;

    private String regionName;
}
