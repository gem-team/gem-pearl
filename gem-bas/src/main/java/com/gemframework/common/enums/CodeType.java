package com.gemframework.common.enums;

import lombok.Getter;

/**
 * @Title: CodeType.java
 * @Package: com.gemframework.enum
 * @Date: 2019/11/27 22:28
 * @Version: v1.0
 * @Description: 枚举类

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Getter
public enum CodeType {
//    类型 0JAVA HTML 2其他'
    JAVA(0,"JAVA"),
    HTML(1,"HTML"),
    OTHER(2,"其他"),
    ;


    private Integer code;
    private String msg;

    CodeType(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
