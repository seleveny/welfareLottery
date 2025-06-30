package com.welfare.lottery.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author xuchengcheng
 * @since 2025/6/19
 */
@Data
public class LoginResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6513675478025965438L;

    private String verifyCode;
    private String name;
    private Integer level;
    private String levelCode;
    private String roleType;
}
