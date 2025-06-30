package com.welfare.lottery.aspect;


import com.alibaba.fastjson2.JSON;
import com.welfare.lottery.common.CookiesInfo;
import com.welfare.lottery.common.UserContext;
import com.welfare.lottery.common.UserLoginInfo;
import com.welfare.lottery.common.exception.WelfareBaseException;
import com.welfare.lottery.common.exception.WelfareException;
import com.welfare.lottery.common.logger.LoggerFactory;
import com.welfare.lottery.response.WelfareResult;
import com.welfare.lottery.utils.AESUtils;
import com.welfare.lottery.utils.CookieUtils;
import com.welfare.lottery.utils.FastJsonUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author xuchengcheng
 * 拦截器汇总
 * @since 2025/6/18
 */
@Data
public class WelfareAspect {

    @Value("${encryption.auth.token.salt}")
    private String authTokenSalt;

    public Object loginRequiredAround(ProceedingJoinPoint jp) throws Throwable {
        // 获取当前请求对象
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        String[] remote = LoggerFactory.getRemote();
        String methodName = jp.getSignature().toString();
        String args = FastJsonUtils.toJSONString(jp.getArgs());

        // 从cookie中解析用户的信息
        Cookie[] cookies = request.getCookies();
        // 登录信息内容
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        try {
            if (cookies != null) {
                // 解析cookies内容
                CookiesInfo cookiesOriginInfo = CookieUtils.parseCookiesToObject(cookies, CookiesInfo.class);
                // 解码authToken做基本的校验
                String decryptAuthToken = AESUtils.decrypt(authTokenSalt, cookiesOriginInfo.getAuthToken());
                CookiesInfo cookiesDecryptInfo = JSON.parseObject(decryptAuthToken, CookiesInfo.class);
                // 校验是否匹配内容
                checkLoginCookies(cookiesDecryptInfo, cookiesOriginInfo);
                // 设置从cookies中获取到的用户信息
                userLoginInfo.setId(cookiesDecryptInfo.getId());
                userLoginInfo.setName(cookiesDecryptInfo.getName());
                userLoginInfo.setVerifyCode(cookiesDecryptInfo.getVerifyCode());
                userLoginInfo.setLevel(cookiesDecryptInfo.getLevel());
                userLoginInfo.setRegionCode(cookiesDecryptInfo.getRegionCode());
                userLoginInfo.setRegionName(cookiesDecryptInfo.getRegionName());
                userLoginInfo.setTelephone(cookiesDecryptInfo.getTelephone());
                userLoginInfo.setRoleType(cookiesDecryptInfo.getRoleType());
                // 传递到链路上下文
                UserContext.setUserLoginInfo(userLoginInfo);
                return jp.proceed();
            }
            throw WelfareException.LOGIN_ERROR;
        } catch (WelfareBaseException e) {
            LoggerFactory.controllerError(remote[1], remote[0], methodName, args, e.getCode(), e);
            return new WelfareResult<>(e);
        } catch (Exception e) {
            LoggerFactory.controllerError(remote[1], remote[0], methodName, args, WelfareException.SERVER_ERROR.getCode(), e);
            return new WelfareResult<>(WelfareException.SERVER_ERROR);
        }
    }

    private void checkLoginCookies(CookiesInfo cookiesDecryptInfo, CookiesInfo cookiesOriginInfo) {
        if (!Objects.equals(cookiesOriginInfo.getId(), cookiesDecryptInfo.getId())
                || !Objects.equals(cookiesOriginInfo.getName(), cookiesDecryptInfo.getName())
                || (StringUtils.isNoneBlank(cookiesOriginInfo.getVerifyCode()) && !Objects.equals(cookiesOriginInfo.getVerifyCode(), cookiesDecryptInfo.getVerifyCode()))
        ) {
            throw WelfareException.LOGIN_ERROR;
        }
    }

    public Object controllerAround(ProceedingJoinPoint jp) throws Throwable {
        String[] remote = LoggerFactory.getRemote();

        StopWatch sw = new StopWatch();
        String methodName = jp.getSignature().toString();
        String args = FastJsonUtils.toJSONString(jp.getArgs());

        try {
            sw.start();
            Object result = jp.proceed();
            sw.stop();

            LoggerFactory.controllerAccess(remote[1], remote[0], methodName, args, sw.getTotalTimeMillis());
            return result;
        } catch (WelfareBaseException e) {
            LoggerFactory.controllerError(remote[1], remote[0], methodName, args, e.getCode(), e);
            return new WelfareResult<>(e);
        } catch (Exception e) {
            LoggerFactory.controllerError(remote[1], remote[0], methodName, args, WelfareException.SERVER_ERROR.getCode(), e);
            return new WelfareResult<>(WelfareException.SERVER_ERROR);
        }
    }

    public Object serviceAround(ProceedingJoinPoint jp) throws Throwable {
        String[] remote = LoggerFactory.getRemote();

        StopWatch sw = new StopWatch();
        String methodName = jp.getSignature().toString();
        String args = FastJsonUtils.toJSONString(jp.getArgs());

        try {
            sw.start();
            Object result = jp.proceed();
            sw.stop();

            LoggerFactory.serviceAccess(remote[1], remote[0], methodName, args, result, sw.getTotalTimeMillis());
            return result;
        } catch (WelfareException e) {
            LoggerFactory.serviceError(remote[1], remote[0], methodName, args, e.getCode(), e);
            throw e;
        } catch (DuplicateKeyException e) {
            throw WelfareException.DUPLICATE;
        } catch (Exception e) {
            LoggerFactory.serviceError(remote[1], remote[0], methodName, args, WelfareException.SERVER_ERROR.getCode(), e);
            throw WelfareException.SERVER_ERROR;
        }
    }
}
