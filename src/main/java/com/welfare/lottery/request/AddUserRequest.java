package com.welfare.lottery.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author xuchengcheng
 * @since 2025/6/23
 */
@Data
public class AddUserRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -9201440400011942998L;

    //姓名
    private String name;
    //身份证号
    private String idCard;
    /**
     * 属地码、属地名称
     */
    private String regionCode;
    private String regionName;

    //工作单位
    private String company;
    //工作职务
    private String jobTitle;
    //是否MJ
    private Integer police;
    //账号角色
    private String roleType;
    //手机号
    private String telephone;
    //密码
    private String password;
    //认证码
    private String verifyCode;
    //申请理由
    private String applyReason;
    //状态
    private Boolean status;
}
