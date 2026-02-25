package com.alex.filter;

import com.alex.util.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;


@Slf4j
//@WebFilter(urlPatterns = "/*")//block all request paths
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().toString();

        if(url.contains("login")){
            log.info("login request , directly allowing access");
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("token");
        if(!StringUtils.hasLength(token)){
            log.info("jwt token is null, return error");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;

        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败, 返回错误结果");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6. 放行。
        log.info("令牌合法, 放行");
        filterChain.doFilter(request , response);


    }
}
