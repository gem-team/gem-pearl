package com.gemframework.common.enums;

import lombok.Getter;

/**
 * @Title: ResultCode.java
 * @Package: com.gemframework.enum
 * @Date: 2019/11/27 22:28
 * @Version: v1.0
 * @Description: 枚举类

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Getter
public enum OperateType {
//    类型 0登录 1其他'
    LOGIN(0,"登录"),
    OTHER(1,"其他"),
    ;


    private Integer code;
    private String msg;

    OperateType(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
