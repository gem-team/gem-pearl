package com.gemframework.common.security.exception;

import com.gemframework.common.enums.ResultCode;
import com.gemframework.common.enums.ResultURL;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
public class GemAuthenticationException extends AuthenticationException {

    private ResultCode resultCode;
    private ResultURL url;

    public GemAuthenticationException(ResultCode resultCode) {
        super(resultCode.getMsg());
    }

    public GemAuthenticationException(ResultCode resultCode, ResultURL URL) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
        this.url = URL;
    }

}