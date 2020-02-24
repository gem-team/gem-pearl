package com.gemframework.common.security.handler;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.ResultURL;
import com.gemframework.common.response.GemResponse;
import com.gemframework.common.security.exception.GemAccessDeniedException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GemAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws  ServletException, IOException {
        if(e instanceof GemAccessDeniedException){
            GemResponse.returnResult(request,response, ((GemAccessDeniedException) e).getResultCode(), ((GemAccessDeniedException) e).getUrl());
        }else {
            GemResponse.returnResult(request,response, ResultCode.SYSTEM_EXCEPTION, ResultURL.EXCEPTION);
        }
    }
}