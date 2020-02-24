package com.gemframework.model.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ResetPasswordReq {

    @NotNull(message = "ID不能为空")
    private Long id;
    @NotBlank(message = "请设置新密码")
    private String newPass;
}
