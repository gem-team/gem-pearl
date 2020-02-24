package com.gemframework.common.enums;

import lombok.Getter;

/**
 * @Title: ResultCode.java
 * @Package: com.gemframework.enum
 * @Date: 2019/11/27 22:28
 * @Version: v1.0
 * @Description: 错误码枚举类

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Getter
public enum ResultURL {

    SUCCESS("succ","成功页面"),
    ERROR("error","错误页面"),
    INDEX("index","首页"),
    LOGIN("login","登录页"),
    EXCEPTION("500","服务异常"),
    NOT_FOUND("404","找不到页面"),
    REFUSE("403","拒绝访问"),

    UNKNOWN("404","页面不存在"),
    ;


    private String url;
    private String msg;

    ResultURL(String url, String msg){
        this.url = url;
        this.msg = msg;
    }
}
