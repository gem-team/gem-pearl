package com.gemframework.common.security.handler;

import com.gemframework.common.constant.GemConstant;
import com.gemframework.common.enums.OperateStatus;
import com.gemframework.common.enums.OperateType;
import com.gemframework.common.utils.GemIPHandler;
import com.gemframework.model.vo.SysLogVo;
import com.gemframework.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gemframework.common.aspect.LogAspect.getIpAddress;

@Slf4j
@Component("gemLoginFailureHandler")
public class GemLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SysLogService sysLogService;

    public GemLoginFailureHandler(){
        this.setDefaultFailureUrl("/login?error=true");
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        super.onAuthenticationFailure(request,response,exception);
        response.setContentType(GemConstant.MediaType.JSON_UTF_8);
        log.info("登录失败:"+exception.getMessage());
        //这里写登录失败的逻辑
        // 判断是用哪一种方式进行登录的

//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setContentType(GemConstant.MediaType.JSON_UTF_8);
//        if(exception instanceof BadCredentialsException){
//            response.getWriter().write(GemJsonUtils.objectToJson(ResultData.getResultWithCode(GemErrorStatus.PASSWORD_ERROR)));
//        }else if(exception instanceof InternalAuthenticationServiceException){
//            response.getWriter().write(GemJsonUtils.objectToJson(ResultData.getResultWithCode(GemErrorStatus.NOT_USER)));
//        }else{
//            log.error("---auth error:{}","GemLoginFailureHandler");
//            response.getWriter().write(GemJsonUtils.objectToJson(ResultData.getResultWithCode(GemErrorStatus.AUTHENTICATION_FAILED)));
//        }

        //记录操作日志
        SysLogVo sysLogVo = SysLogVo.builder()
                .account(request.getParameter("username").trim())
                .username(request.getParameter("username").trim())
                .clientIp(getIpAddress(request))
                .address(GemIPHandler.ipToAddress(getIpAddress(request)))
                .operateType(OperateType.LOGIN.getCode())
                .operateStatus(OperateStatus.FAIL.getCode())
                .requestUrl(request.getRequestURL().toString())
                .requestMothod(request.getMethod())
                .build();
        sysLogService.save(sysLogVo);
    }

}
