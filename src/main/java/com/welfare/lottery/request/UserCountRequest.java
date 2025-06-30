package com.welfare.lottery.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xuchengcheng
 * 获取用户数的统计请求参数
 * @since 2025/6/20
 */
@Data
public class UserCountRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 706445221079482733L;

    private Date startTime;

    private Date endTime;
}
