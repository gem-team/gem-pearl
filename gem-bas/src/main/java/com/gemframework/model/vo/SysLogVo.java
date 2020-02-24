package com.gemframework.model.vo;

import lombok.*;

/**
 * @Title: SysLogVo.java
 * @Package: com.gemframework.model.vo
 * @Date: ${date}
 * @Version: v1.0
 * @Description: VO

 * @Author: gemteam
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class SysLogVo extends BaseVo {

    private Long id;
    private String username;
    private String account;
    private String clientIp;
    private String address;
    private Integer operateType;
    private Integer operateStatus;
    private String requestUrl;
    private String requestMothod;
    private String requestArgs;

}
