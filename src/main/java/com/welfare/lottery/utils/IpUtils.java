package com.welfare.lottery.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IpUtils {

    static String LOCALHOST = "127.0.0.1";

    public static final String localIp = getHostAddress();

    public static final String getHostAddress() {
        try {
            Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
            for (; n.hasMoreElements(); ) {
                NetworkInterface e = n.nextElement();
                Enumeration<InetAddress> a = e.getInetAddresses();
                for (; a.hasMoreElements(); ) {
                    InetAddress addr = a.nextElement();
                    if (addr.isLoopbackAddress() || !(addr instanceof Inet4Address)) {
                        continue;
                    }
                    if (!LOCALHOST.equals(addr.getHostAddress())) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {

        }
        return LOCALHOST;
    }

    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotEmpty(ip)) {
            String[] ips = ip.split(",");
            ip = ips[0];
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-real-ip");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }


}
