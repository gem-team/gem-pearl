package com.gemframework.model.vo;

import lombok.*;

/**
 * @Title: ModuleAttrVo.java
 * @Package: com.gemframework.model.vo
 * @Date: 2019/11/30 22:40
 * @Version: v1.0
 * @Description: VO

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ModuleAttrVo extends BaseVo {

    private Long id;
    private Long moduleId;
    private String attrName;
    private Integer attrSort;
    private String comment;
    private String attrType;
    private String options;
    private Integer minLength;
    private Integer maxLength;
    private String editType;//列表支持编辑类型
    private Integer isNull;//是否允许为空
    private Integer isVisit;//列表显示
    private Integer isSort;//支持排序显示
    private Integer isSearch;//支持查询
}
