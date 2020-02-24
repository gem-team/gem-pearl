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
public enum ResultCode {

    SUCCESS(0,"返回成功"),
    NOT_LOGINED(100,"用户未登录"),
    LOGIN_FAIL(101,"登录失败"),
    UNKNOWN_REQUEST(404,"未知请求"),
    PERMISSION_DENIED(403,"权限不足"),

    ERROR_REQUEST(502,"访问错误"),
    SERVICE_EXCEPTION(500,"服务异常"),

    //系统错误码 1000-9999
    PARAM_EXCEPTION(1000,"参数错误"),
    DATA_EXIST(1001,"数据已存在"),
    DATA_NOT_EXIST(1002,"数据不存在"),
    CRATE_IMAGE_ERROR(1003,"生成图形验证码失败"),
    VERIFY_CODE_ERROR(1004,"验证验证码失败"),
    MODULE_ATTR_ERROR(1005,"请完善模块属性（字段）信息"),
    FILE_NOT_EXIST(1006,"文件不存在,请检查"),

    //业务错误码 10000-99999
    USER_EXIST(10000,"用户已存在"),
    DEPT_EXIST(10001,"部门已存在"),
    ROLE_EXIST(10002,"角色已存在"),
    MENU_EXIST(10003,"菜单已存在"),
    MODULE_EXIST(10004,"模块已存在"),
    MENU_LEVEL_EX(10005,"菜单级别错误，所属上级不能选择自己或子节点"),
    SYSTEM_EXCEPTION(999999,"系统异常"),
    ;


    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
