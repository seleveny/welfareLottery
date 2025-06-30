package com.welfare.lottery.controller.user;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.welfare.lottery.annotation.LoginRequired;
import com.welfare.lottery.common.UserContext;
import com.welfare.lottery.common.UserLoginInfo;
import com.welfare.lottery.common.exception.WelfareException;
import com.welfare.lottery.entity.UserInfo;
import com.welfare.lottery.mapstruct.UserMapStruct;
import com.welfare.lottery.model.vo.UserInfoVO;
import com.welfare.lottery.request.AddUserRequest;
import com.welfare.lottery.response.AddUserResponse;
import com.welfare.lottery.response.WelfareResult;
import com.welfare.lottery.response.PageResult;
import com.welfare.lottery.service.UserBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "人员管理")
@RestController
public class UserController {

    @Resource
    private UserMapStruct userMapStruct;

    @Resource
    private UserBusinessService userBusinessService;

    @Resource
    private TransactionTemplate transactionTemplate;


    @Operation(summary = "人员列表", description = "获取人员列表", responses = {@ApiResponse(responseCode = "0", description = "成功")})
    @PostMapping("/queryUserList")
    public WelfareResult<Boolean> queryUserList(Boolean request) {
        return new WelfareResult<>();
    }
}
