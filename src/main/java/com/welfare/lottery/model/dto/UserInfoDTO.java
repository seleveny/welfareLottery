package com.welfare.lottery.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xuchengcheng
 * 传递用户信息的DTO
 * @since 2025/6/19
 */
@Data
public class UserInfoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3263195683319761774L;

    //自增id
    private Long id;
    //姓名
    private String name;
    //属地等级1：国、2：省、3：市、4：县
    private Integer level;
    //对应的编码
    private String levelCode;
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
    //认证码
    private String verifyCode;
    //申请理由
    private String applyReason;
    //启用状态
    private Integer status;
    //是否活跃
    private Integer active;
    //被删除
    private Integer deleted;
    //删除原因
    private String deleteReason;
    //创建时间
    private Date createTime;
    //更新时间
    private Date modifyTime;
}
