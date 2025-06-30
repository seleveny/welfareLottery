package com.welfare.lottery.controller.common;

import com.alibaba.fastjson2.JSON;
import com.welfare.lottery.annotation.LoginRequired;
import com.welfare.lottery.common.UserContext;
import com.welfare.lottery.common.UserLoginInfo;
import com.welfare.lottery.common.constants.CookieConstants;
import com.welfare.lottery.common.exception.WelfareException;
import com.welfare.lottery.common.exception.WelfareExceptionCode;
import com.welfare.lottery.entity.UserInfo;
import com.welfare.lottery.request.LoginRequest;
import com.welfare.lottery.response.LoginResponse;
import com.welfare.lottery.response.WelfareResult;
import com.welfare.lottery.service.UserBusinessService;
import com.welfare.lottery.utils.AESUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author xuchengcheng
 * @since 2025/6/19
 */

@Tag(name = "登录接口")
@RestController
public class LoginController {

    @Resource
    private UserBusinessService userBusinessService;

    @Value("${encryption.password.salt}")
    private String passwordSalt;

    @Value("${encryption.auth.token.salt}")
    private String authTokenSalt;

    @Operation(summary = "用户登录接口", description = "用户登录接口", responses = {
            @ApiResponse(responseCode = "0", description = "成功返回 success")
    })
    @PostMapping("/login")
    public WelfareResult<LoginResponse> login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        checkParams(request);
        return new WelfareResult<>(response);
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出接口", description = "用户登出接口", responses = {
            @ApiResponse(responseCode = "0", description = "成功返回 success")
    })
    @LoginRequired
    public WelfareResult<Boolean> logout() {
        UserLoginInfo userLoginInfo = UserContext.getUserLoginInfo();
        if (Objects.nonNull(userLoginInfo)) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (Objects.nonNull(requestAttributes)) {
                HttpServletResponse servletResponse = requestAttributes.getResponse();
                if (Objects.nonNull(servletResponse)) {
                    clearLoginCookies(servletResponse);
                }
            }
        }
        return new WelfareResult<>(true);
    }

    private void clearLoginCookies(HttpServletResponse servletResponse) {
        for (String cookieName : CookieConstants.ALL_COOKIES) {
            Cookie cookie = new Cookie(cookieName, "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            cookie.setHttpOnly(true);
            servletResponse.addCookie(cookie);
        }
    }

    /**
     * 登录 添加用户cookies
     *
     * @param response
     * @param userinfo
     */
    private void addLoginCookies(HttpServletResponse response, UserInfo userinfo) {
        Cookie idCookie = new Cookie("id", userinfo.getId().toString());
        idCookie.setPath("/");
        idCookie.setHttpOnly(true);
        response.addCookie(idCookie);
        Cookie telephoneCookie = new Cookie("telephone", userinfo.getTelephone());
        telephoneCookie.setPath("/");
        telephoneCookie.setHttpOnly(true);
        response.addCookie(telephoneCookie);
        Cookie nameCookie = new Cookie("name", userinfo.getName());
        nameCookie.setPath("/");
        nameCookie.setHttpOnly(true);
        response.addCookie(nameCookie);
        Cookie roleCookie = new Cookie("role", userinfo.getRoleType());
        roleCookie.setPath("/");
        roleCookie.setHttpOnly(true);
        response.addCookie(roleCookie);
        Cookie levelCookie = new Cookie("level", String.valueOf(userinfo.getLevel()));
        levelCookie.setPath("/");
        levelCookie.setHttpOnly(true);
        response.addCookie(levelCookie);
        Cookie levelCodeCookie = new Cookie("regionCode", userinfo.getRegionCode());
        levelCodeCookie.setPath("/");
        levelCodeCookie.setHttpOnly(true);
        response.addCookie(levelCodeCookie);
        Cookie regionNameCookie = new Cookie("regionName", userinfo.getRegionCode());
        regionNameCookie.setPath("/");
        regionNameCookie.setHttpOnly(true);
        response.addCookie(regionNameCookie);
        Cookie verifyCodeCookie = new Cookie("verifyCode", userinfo.getVerifyCode());
        verifyCodeCookie.setPath("/");
        verifyCodeCookie.setHttpOnly(true);
        response.addCookie(verifyCodeCookie);
        Cookie authToken = new Cookie("authToken", AESUtils.encrypt(authTokenSalt, JSON.toJSONString(userinfo)));
        authToken.setPath("/");
        authToken.setHttpOnly(true);
        response.addCookie(authToken);
    }

    private void checkParams(LoginRequest request) {
        if (request == null) {
            throw WelfareException.PARAM_ERROR;
        }
        if (StringUtils.isBlank(request.getTelephone())) {
            throw new WelfareException(WelfareExceptionCode.PARAM_ERROR, "手机号不能为空");
        }
        if (StringUtils.isBlank(request.getPassword())) {
            throw new WelfareException(WelfareExceptionCode.PARAM_ERROR, "密码不能为空");
        }
    }

    @Operation(summary = "我的信息", description = "个人信息接口", responses = {
            @ApiResponse(responseCode = "0", description = "成功返回 success")
    })
    @PostMapping("/infoMe")
    @LoginRequired
    public WelfareResult<UserLoginInfo> infoMe() {
        UserLoginInfo response = UserContext.getUserLoginInfo();
        if (response == null) {
            throw WelfareException.LOGIN_ERROR;
        }
        return new WelfareResult<>(response);
    }

}
