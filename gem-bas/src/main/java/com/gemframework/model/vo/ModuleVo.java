package com.gemframework.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Title: TableVo.java
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
public class ModuleVo extends BaseVo {

    private Long id;

    @NotBlank(message = "包名不能为空！")
    private String packageName;

    @NotBlank(message = "英文名称不能为空！")
    private String nameEn;

    @NotBlank(message = "中文名称不能为空！")
    private String nameCn;

    @NotBlank(message = "主键名称不能为空！")
    private String pkNane;

    @NotNull(message = "请设置是否具备添加功能！")
    private Integer isAdd;

    @NotNull(message = "请设置是否具备编辑功能！")
    private Integer isEdit;

    @NotNull(message = "请设置是否具备删除功能！")
    private Integer isDel;

    @NotNull(message = "请设置是否具备查询功能！")
    private Integer isQuery;

    private Integer pageWidth;
    private Integer pageHeight;

    //作者
    private String author;

    private List<ModuleAttrVo> moduleAttrs;

    //是否生成Code
    private Integer isGenerate;

}
