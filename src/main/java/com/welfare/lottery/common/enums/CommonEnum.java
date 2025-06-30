package com.welfare.lottery.common.enums;

import lombok.Getter;

public enum CommonEnum {

    YES(1, "是"),
    NO(0, "否"),
    ;

    @Getter
    private final Integer code;

    @Getter
    private final String desc;

    CommonEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CommonEnum getByCode(Integer code) {
        for (CommonEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
