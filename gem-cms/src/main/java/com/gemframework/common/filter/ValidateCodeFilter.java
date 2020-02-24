package com.gemframework.common.filter;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.exception.GemException;
import com.gemframework.common.utils.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义一个验证码的拦截器
 */
@Slf4j
@Component
@Order(2)
public class ValidateCodeFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(request.getMethod().equalsIgnoreCase("post")){
            log.info("验证验证码~~~~~~~"+request.getRequestURI()+"=="+request.getMethod());
        }
        if (StringUtils.equals("login", request.getRequestURI()) &&
                StringUtils.endsWithIgnoreCase(request.getMethod(), "post")) {
            try {
                if(!VerifyCodeUtil.checkVerifyCode(request)){
                    throw new GemException(ResultCode.VERIFY_CODE_ERROR);
                }else{
                    log.info("验证成功~~~~~~~");
                }
            } catch (GemException e) {
                throw new GemException(ResultCode.PARAM_EXCEPTION);
            }
        }
        filterChain.doFilter(request, response);
    }
}