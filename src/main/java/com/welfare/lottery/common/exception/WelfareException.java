package com.welfare.lottery.common.exception;

import java.io.Serial;

/**
 * @author xuchengcheng
 * @since 2025/6/18
 */
public class WelfareException extends WelfareBaseException {

    public static final WelfareException UNKNOWN = new WelfareException(WelfareExceptionCode.UNKNOWN, "未知错误");
    public static final WelfareException PARAM_ERROR = new WelfareException(WelfareExceptionCode.PARAM_ERROR, "参数错误");
    public static final WelfareException SERVER_ERROR = new WelfareException(WelfareExceptionCode.SERVER_ERROR, "服务器错误");
    public static final WelfareException DUPLICATE = new WelfareException(WelfareExceptionCode.DUPLICATE, "重复数据");
    public static final WelfareException LOGIN_ERROR = new WelfareException(WelfareExceptionCode.LOGIN_ERROR, "用户未登录");
    public static final WelfareException PERMISSION_ERROR = new WelfareException(WelfareExceptionCode.PERMISSION_ERROR, "权限不足");
    public static final WelfareException USER_EXCEL_FILE_EMPTY = new WelfareException(WelfareExceptionCode.USER_EXCEL_FILE_EMPTY, "用户文件为空");
    public static final WelfareException USER_ADD_FAIL = new WelfareException(WelfareExceptionCode.USER_ADD_FAIL, "用户文件错误");
    public static final WelfareException EXCEL_PARSE_ERROR = new WelfareException(WelfareExceptionCode.EXCEL_PARSE_ERROR, "Excel解析错误");
    public static final WelfareException USER_MODIFY_FAIL = new WelfareException(WelfareExceptionCode.USER_MODIFY_FAIL, "用户信息更新失败");
    public static final WelfareException PARENT_CODE_NOT_EXIST = new WelfareException(WelfareExceptionCode.PARENT_CODE_NOT_EXIST, "父级编码不存在");
    public static final WelfareException DATA_OPERATE_FAIL = new WelfareException(WelfareExceptionCode.DATA_OPERATE_FAIL, "数据操作失败");
    public static final WelfareException LEVEL_CODE_NOT_EXIST = new WelfareException(WelfareExceptionCode.LEVEL_CODE_NOT_EXIST, "levelCode不存在");

    @Serial
    private static final long serialVersionUID = 424837108908242786L;

    public WelfareException(int code, String reason) {
        super(code, reason);
    }

    public WelfareException(int code, String reason, String number) {
        super(code, reason, number);
    }

    public WelfareException(int code, String reason, String number, Throwable cause) {
        super(code, reason, number, cause);
    }

    public WelfareException(WelfareException e, String number) {
        super(e.code, e.reason, number);
    }


    public int getCode() {
        return code;
    }

    public String getNumber() {
        return number;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return code + ":" + reason + ":" + number;
    }

}
