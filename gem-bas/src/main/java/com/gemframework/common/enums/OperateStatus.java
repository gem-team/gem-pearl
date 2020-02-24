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
public enum OperateStatus {
//    操作状态 0成功 1失败'
    SUCCESS(0,"成功"),
    FAIL(1,"失败"),
    ;


    private Integer code;
    private String msg;

    OperateStatus(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
