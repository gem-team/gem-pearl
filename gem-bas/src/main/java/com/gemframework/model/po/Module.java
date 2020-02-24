package com.gemframework.model.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gem_module")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Module extends BasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(30) not null comment '包名'",nullable = false)
    private String packageName;

    @Column(columnDefinition = "varchar(30) not null comment '模块英文名称'",nullable = false)
    private String nameEn;

    @Column(columnDefinition = "varchar(60) not null comment '模块中文名称'",nullable = false)
    private String nameCn;

    @Column(columnDefinition = "varchar(60) not null comment '主键名称'",nullable = false)
    private String pkNane;

    @Column(columnDefinition = "tinyint(1) not null DEFAULT 1 comment '是否具备添加功能 1：是 0：否'",nullable = false)
    private Integer isAdd;

    @Column(columnDefinition = "tinyint(1) not null DEFAULT 1 comment '是否具备编辑功能 1：是 0：否'",nullable = false)
    private Integer isEdit;

    @Column(columnDefinition = "tinyint(1) not null DEFAULT 1 comment '是否具备删除功能 1：是 0：否'",nullable = false)
    private Integer isDel;

    @Column(columnDefinition = "tinyint(1) not null DEFAULT 1 comment '是否具备查询功能 1：是 0：否'",nullable = false)
    private Integer isQuery;

    @Column(columnDefinition = "varchar(10) comment '作者签名'")
    private String author;

    @Column(columnDefinition = "int(10) DEFAULT 550 comment '编辑页面宽度'")
    private Integer pageWidth; // 添加框宽度

    @Column(columnDefinition = "int(10) DEFAULT 450 comment '编辑页面高度'")
    private Integer pageHeight; // 添加框高度

    @Column(columnDefinition = "tinyint(1) DEFAULT 0 comment '是否生成CODE, 1：是 0：否'")
    private Integer isGenerate;     //是否生成Code

    @Transient
    private List<ModuleAttr> moduleAttrs;
}
