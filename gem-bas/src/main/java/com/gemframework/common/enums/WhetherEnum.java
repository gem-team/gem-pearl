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
public enum WhetherEnum {
//    类型 0否 1是 2其他'
    NO(0,"否"),
    YES(1,"是"),
    OTHER(2,"其他"),
    ;


    private Integer code;
    private String msg;

    WhetherEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
