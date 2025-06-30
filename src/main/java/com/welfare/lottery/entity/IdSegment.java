package com.welfare.lottery.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * (IdSegment)表实体类
 *
 * @author admin
 * @since 2025-06-23 19:02:15
 */
@Setter
@Getter
@SuppressWarnings("serial")
public class IdSegment extends Model<IdSegment> {

    //业务标识
    private String bizTag;
    //当前最大ID
    private Long currentMax;
    //每次扩展步长
    private Integer step;
    //创建时间
    private Date createTime;
    //更新时间
    private Date modifyTime;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.bizTag;
    }
}

