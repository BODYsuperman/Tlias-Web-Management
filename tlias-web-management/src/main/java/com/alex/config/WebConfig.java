package com.alex.config;

//
//import com.alex.interceptor.TokenInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alex.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Custom interceptor object
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register custom interceptor
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")              // Intercept all requests
                .excludePathPatterns("/login");       // Exclude login requests
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configure image access mapping
        registry.addResourceHandler("/images/**")     // Browser access path
                .addResourceLocations("file:/Users/alexandarmay/Documents/files/"); // Local file storage path
    }
}