package com.gemframework.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


@Slf4j
@Component
@Order(1)
public class CustomFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        //启动时执行
        log.debug("==================启动时执行");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.debug("==================访问时执行");
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        //执行下一个过滤器
        fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        return;
    }

    @Override
    public void destroy() {

        log.info("被销毁了");
    }
}