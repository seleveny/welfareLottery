package com.welfare.lottery.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author xuchengcheng
 * @since 2025/6/19
 */
@Data
public class LoginRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3161315020363807750L;

    private String telephone;

    private String password;

}
