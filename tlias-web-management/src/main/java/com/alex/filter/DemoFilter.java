package com.alex.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

///filter all request path
public class DemoFilter implements Filter {
    //初始化方法, web服务器启动, 创建Filter实例时调用, 只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init ...");
    }

    //拦截到请求时,调用该方法,可以调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)  {
        System.out.println("拦截到了请求...");
    }

    //销毁方法, web服务器关闭时调用, 只调用一次
    @Override
    public void destroy() {
        System.out.println("destroy ... ");
    }
}