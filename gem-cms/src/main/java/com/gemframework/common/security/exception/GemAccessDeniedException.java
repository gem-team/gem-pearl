package com.gemframework.common.security.exception;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.ResultURL;
import lombok.*;
import org.springframework.security.access.AccessDeniedException;

@Getter
@Setter
public class GemAccessDeniedException extends AccessDeniedException {

    private ResultCode resultCode;
    private ResultURL url;

    public GemAccessDeniedException(ResultCode resultCode) {
        super(resultCode.getMsg());
    }

    public GemAccessDeniedException(ResultCode resultCode, ResultURL URL) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
        this.url = URL;
    }
}