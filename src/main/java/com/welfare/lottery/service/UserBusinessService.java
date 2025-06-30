package com.welfare.lottery.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.welfare.lottery.entity.UserInfo;
import com.welfare.lottery.mapstruct.UserMapStruct;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class UserBusinessService {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserMapStruct userMapStruct;

    @Resource
    private GenerateIdService generateIdService;

    /**
     * 获取指定时间段内注册用户数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public Long getCountByRange(Date startTime, Date endTime) {
        return userInfoService.count(new LambdaQueryWrapper<UserInfo>()
                .ge(UserInfo::getCreateTime, startTime)
                .le(UserInfo::getCreateTime, endTime));
    }

    public Long getCountByRangeAndRegion(Date startTime, Date endTime, List<String> regionCodes) {
        return userInfoService.count(new LambdaQueryWrapper<UserInfo>()
                .ge(UserInfo::getCreateTime, startTime)
                .le(UserInfo::getCreateTime, endTime)
                .in(UserInfo::getRegionCode, regionCodes));
    }
}
