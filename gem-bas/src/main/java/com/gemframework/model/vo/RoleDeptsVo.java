package com.gemframework.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Title: RoleDeptsVo.java
 * @Package: com.gemframework.model.vo
 * @Date: 2019/11/30 22:40
 * @Version: v1.0
 * @Description: VO

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RoleDeptsVo extends BaseVo {

    private Long id;

    private String roleId;

    private String deptId;
}
