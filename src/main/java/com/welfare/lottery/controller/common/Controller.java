package com.welfare.lottery.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "公共接口")
@RestController
public class Controller {

    @Operation(summary = "公共检查接口", description = "用于测试接口是否正常", responses = {
            @ApiResponse(responseCode = "无", description = "成功返回 success")
    })
    @RequestMapping("/pub_check")
    public String pubCheck() {
        return "success";
    }

}
