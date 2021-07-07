package com.project.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.board.session.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {
    
    Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    @Autowired
    private UserInfo userInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

                // Session 값 없을 경우 redirect Login
                if (userInfo.getUserId().isBlank()) {

                    response.sendRedirect(request.getContextPath()+"/user/login.do");

                    return true;

                } else {

                    return true;

                }

    }

}
