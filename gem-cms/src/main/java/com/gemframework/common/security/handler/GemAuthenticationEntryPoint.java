package com.gemframework.common.security.handler;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.ResultURL;
import com.gemframework.common.response.GemResponse;
import com.gemframework.common.security.exception.GemAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GemAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if(e instanceof GemAuthenticationException){
            GemResponse.returnResult(request,response, ((GemAuthenticationException) e).getResultCode(), ((GemAuthenticationException) e).getUrl());
        }else {
            GemResponse.returnResult(request,response, ResultCode.SYSTEM_EXCEPTION, ResultURL.EXCEPTION);
        }
    }
}
