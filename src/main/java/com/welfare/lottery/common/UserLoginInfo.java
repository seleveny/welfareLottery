package com.welfare.lottery.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author xuchengcheng
 * 用户的登录信息内容
 * @since 2025/6/19
 */
@Data
public class UserLoginInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 4329283761881991257L;

    private Long id;

    private String name;

    private String verifyCode;

    private String roleType;

    private String level;

    private String telephone;

    private String regionCode;

    private String regionName;
}
