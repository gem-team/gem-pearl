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
public enum MenuType {
//    类型 0菜单 1按钮 2其他'
    MENU(0,"菜单"),
    BUTTON(1,"按钮"),
    OTHER(2,"其他"),
    ;


    private Integer code;
    private String msg;

    MenuType(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
