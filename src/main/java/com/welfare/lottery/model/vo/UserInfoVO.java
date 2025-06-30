package com.welfare.lottery.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author xuchengcheng
 * 用户信息的展示模型
 * @since 2025/6/20
 */
@Data
public class UserInfoVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7576191191552106567L;

    /**
     * 属地码、属地名称
     */
    private String regionCode;
    private String regionName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 是否MJ
     */
    private Boolean police;

    /**
     * 认证码
     */
    private String verifyCode;

    /**
     * 密码
     */
    private String password;

    /**
     * 已完成任务数
     */
    private Long completedTaskCount;

    /**
     * 逾期任务数
     */
    private Long overdueTaskCount;
    /**
     * 任务完成率
     */
    private String taskCompletedRate;
    /**
     * 是否活跃
     */
    private Boolean active;
    /**
     * 注册时间
     */
    private String registerTime;
}
