package com.welfare.lottery.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author xuchengcheng
 * @since 2025/6/25
 */
@Data
public class DeleteUserRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 727838841916327140L;

    /**
     * 人员信息表中的user_info中的自增id
     */
    private Long id;


}
