package com.ya666.cryptocurrencylog.config;

import com.ya666.cryptocurrencylog.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //註冊攔截器添加規則
        registry.addInterceptor(new LoginInterceptor())
                //攔截所有請求
                .addPathPatterns("/**")
                //放行資源
                .excludePathPatterns("/web/login.html",
                                     "/web/register.html",
                                     "/bootstrap3/**","/css/**","/img/**")
                //放行請求接口
                .excludePathPatterns("/money/**","/user/**","/sse")
                .excludePathPatterns("/error");

    }
}
