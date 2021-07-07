package com.project.board.config;

import com.project.board.interceptor.UserInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Autowired
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
        .addPathPatterns("/*/*.do")
        .excludePathPatterns("/user/login.do")
        .excludePathPatterns("/user/login")
        .excludePathPatterns("/user/signUp.do")
        .excludePathPatterns("/user/signUp")
        .excludePathPatterns("/user/checkId")
        .excludePathPatterns("/user/checkNickname");
    }

}
