package com.alex.filter;

import com.alex.util.CurrentHolder;
import com.alex.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;


@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();

        if ( requestURI.equals("/login") ) {
            log.info("登录入口，放行" );
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = request.getHeader("token");

        if ( token == null || token.isEmpty()){
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try{
            Claims claims = JwtUtils.parseJWT(token);
            Integer Empid = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(Empid);
            log.info("当前登录员工id：{}",Empid);
        } catch(Exception e){
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);

        CurrentHolder.remove();
    }
}