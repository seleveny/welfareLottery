package com.welfare.lottery.common.exception;

import lombok.Data;


import java.io.Serial;
import java.util.UUID;

/**
 * @author xuchengcheng
 * @since 2025/6/17
 */
@Data
public class WelfareBaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 740365610779459431L;

    public int code;
    public String reason;
    public String number;

    public WelfareBaseException(int code, String reason) {
        super(reason);
        this.code = code;
        this.reason = reason;
    }

    public WelfareBaseException(int code, String reason, String number) {
        super(reason);
        this.code = code;
        this.reason = reason;
        if (null == number) {
            number = UUID.randomUUID().toString().replaceAll("-", "");
        }
        this.number = number;
    }

    public WelfareBaseException(int code, String reason, String number, Throwable cause) {
        super(reason, cause);
        this.code = code;
        this.reason = reason;
        this.number = number;
    }

    @Override
    public String toString() {
        return code + ":" + reason;
    }

}
