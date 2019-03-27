package com.demo.mavenTutorial.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class AddInterceptor extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("进入拦截器");

        // 注册拦截器
        InterceptorRegistration interceptorRegistration = registry
                .addInterceptor(new SessionHandlerInterceptors());
        interceptorRegistration.addPathPatterns("/mvc/interceptor");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/mvc/cors")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET");
    }
}
