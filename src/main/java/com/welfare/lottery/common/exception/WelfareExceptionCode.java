package com.welfare.lottery.common.exception;

/**
 * @author xuchengcheng
 * @since 2025/6/17
 */
public class WelfareExceptionCode {

    //通用异常
    public static final int UNKNOWN = 100000;
    public static final int PARAM_ERROR = 100001;
    public static final int SERVER_ERROR = 100002;
    public static final int DUPLICATE = 100003;
    public static final int PARENT_CODE_NOT_EXIST = 100004;
    public static final int DATA_OPERATE_FAIL = 100005;
    public static final int LEVEL_CODE_NOT_EXIST = 100006;

    //登录相关
    public static final int LOGIN_ERROR = 200001;
    public static final int PERMISSION_ERROR = 200002;

    //用户管理
    public static final int USER_ERROR = 300001;
    public static final int USER_ADD_FAIL = 300002;
    public static final int USER_MODIFY_FAIL = 300003;

    //任务管理相关
    public static final int TASK_ERROR = 400001;

    //大盘相关
    public static final int DASHBOARD_ERROR = 500001;

    //文件上传相关
    public static final int USER_EXCEL_FILE_EMPTY = 600001;
    public static final int EXCEL_PARSE_ERROR = 600002;
}
