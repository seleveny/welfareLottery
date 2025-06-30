package com.welfare.lottery.controller.user;

import com.welfare.lottery.mapstruct.UserMapStruct;
import com.welfare.lottery.response.WelfareResult;
import com.welfare.lottery.service.UserBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
