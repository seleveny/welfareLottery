package com.welfare.lottery.common.logger;

import com.welfare.lottery.common.exception.WelfareExceptionCode;
import com.welfare.lottery.utils.FastJsonUtils;
import com.welfare.lottery.utils.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;

/**
 * @author xuchengcheng
 * @since 2025/6/18
 */
public class LoggerFactory {

    static final String localHost = IpUtils.localIp;

    static final Logger controllerAccessLogger = org.slf4j.LoggerFactory.getLogger("controllerAccessLogger");

    static final Logger controllerErrorLogger = org.slf4j.LoggerFactory.getLogger("controllerErrorLogger");


    static final Logger serviceAccessLogger = org.slf4j.LoggerFactory.getLogger("serviceAccessLogger");

    static final Logger serviceErrorLogger = org.slf4j.LoggerFactory.getLogger("serviceErrorLogger");


    public static <T> Logger getLogger(Class<T> clazz) {
        return org.slf4j.LoggerFactory.getLogger(clazz);
    }


    public static void serviceAccess(String remoteHost, String remoteType, String methodName, String args, Object res, long time) {

        serviceAccessLogger.info(localHost + "|" + remoteHost + "|" + remoteType + "|" + methodName + ":" + args + ":" + FastJsonUtils.toJSONString(res) + "|" + time + "|OK");
    }

    public static void serviceError(String remoteHost, String remoteType, String methodName, String args, int code, Throwable t) {
        serviceAccessLogger.info(localHost + "|" + remoteHost + "|" + remoteType + "|" + methodName + ":" + args + "|-|ERROR[" + code + "]");
        serviceErrorLogger.error(localHost + "|" + remoteHost + "|" + remoteType + "|" + methodName + ":" + args + "|-|ERROR[" + code + "]", t);
    }


    public static void controllerAccess(String remoteHost, String remoteType, String methodName, String args, long time) {

        controllerAccessLogger.info(localHost + "|" + remoteHost + "|" + remoteType + "|" + methodName + ":" + args + "|" + time + "|OK");
    }

    public static void controllerError(String remoteHost, String remoteType, String methodName, String args, int code, Throwable t) {
        controllerAccessLogger.info(localHost + "|" + remoteHost + "|" + remoteType + "|" + methodName + ":" + args + "|-|ERROR[" + code + "]");
        if (code == WelfareExceptionCode.UNKNOWN) {
            controllerErrorLogger.error(localHost + "|" + remoteHost + "|" + remoteType + "|" + methodName + ":" + args + "|-|ERROR[" + code + "]", t);
        } else {
            controllerErrorLogger.error(localHost + "|" + remoteHost + "|" + remoteType + "|" + methodName + ":" + args + "|-|ERROR[" + code + "]");
        }
    }


    public static String[] getRemote() {
        String remoteType = StringUtils.defaultString(MDC.get("traceType"));
        String remoteIp = StringUtils.defaultString(MDC.get("remoteIp"), StringUtils.defaultString("REMOTE", "-"));

        return new String[]{remoteType, remoteIp};
    }
}
