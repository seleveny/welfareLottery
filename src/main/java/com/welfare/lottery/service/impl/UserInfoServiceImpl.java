package com.welfare.lottery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welfare.lottery.dao.UserInfoDao;
import com.welfare.lottery.entity.UserInfo;
import com.welfare.lottery.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * (UserInfo)表服务实现类
 *
 * @author admin
 * @since 2025-06-27 12:25:52
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

}

