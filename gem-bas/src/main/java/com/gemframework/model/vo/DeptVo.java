package com.gemframework.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Title: RoleVo.java
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
public class DeptVo extends BaseVo {

    private Long id;

    @NotBlank(message = "部门名称不能为空！")
    @Size(min = 2,max = 10,message = "部门名称长度限制2~10个数字之间")
    private String name;

    private Long pid;
    //父级名称
    private String pname;

    private Integer level;

    private String desp;

    //系列 用于归类 存放家族一级分类ID 一级分类存自己ID
    private String series;
    //路径 1-2-1
    private String idPath;
    //父级的路径 1-2-1
    private String parentIdPath;

    List<DeptVo> childs;

    //详情字段
    private String num;
    private String boss;
    private String abbr;
    private String type;
    private String tel;
    private String email;
}
