package com.project.board.util;

import javax.servlet.http.HttpServletRequest;

public class UserIpUtil {
    public static String getIp(HttpServletRequest request) {

        // X-Forwarded-For 사용자 IP를 식별하기 위한 표준
        String ip = request.getHeader("X-Forwarded-For");

        if(ip == null) {
            // Proxy 환경에서 Ip 얻기
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null) {
            // WebLogic Server IP
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null) {
            // 사용자가 공유 인터넷에서 페이지에 엑세스 할때 IP 가져오기
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if(ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if(ip == null) {
            // 사용자의 실제 IP 받아오기
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
