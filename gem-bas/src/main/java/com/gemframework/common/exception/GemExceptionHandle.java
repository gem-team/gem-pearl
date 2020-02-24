package com.gemframework.common.exception;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.model.BaseResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: GemException.java
 * @Package: com.gemframework.gembasic.exception
 * @Date: 2019/11/27 21:53
 * @Version: v1.0
 * @Description: 异常统一捕获类
 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@ControllerAdvice
public class GemExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(GemExceptionHandle.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResultData handleExp(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        if (ex instanceof GemException) {
            logger.error("GemException: code:{},msg:{}" ,((GemException) ex).getCode(), ex.getMessage());
            GemException gemException = (GemException) ex;
            return BaseResultData.ERROR(gemException.getCode(), gemException.getMessage());
        } else {
            logger.error("System Exception: code:{},msg:{}" , ex.getStackTrace(),ex.getMessage());
            return BaseResultData.ERROR(ResultCode.SYSTEM_EXCEPTION.getCode(), ResultCode.SYSTEM_EXCEPTION.getMsg());
        }
    }
}
