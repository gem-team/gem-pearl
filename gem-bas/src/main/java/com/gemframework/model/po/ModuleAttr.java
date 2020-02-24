package com.gemframework.model.po;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "gem_module_attr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ModuleAttr extends BasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long moduleId;

    @Column(columnDefinition = "varchar(30) not null comment '字段名称'",nullable = false)
    private String attrName;

    @Column(columnDefinition = "int(2) not null comment '字段顺序'",nullable = false)
    private Integer attrSort;

    @Column(columnDefinition = "varchar(30) not null comment '字段描述'",nullable = false)
    private String comment;

    @Column(columnDefinition = "varchar(30) not null comment '字段类型'",nullable = false)
    private String attrType;

    @Column(columnDefinition = "varchar(300) comment '下拉框内容'")
    private String options;

    @Column(columnDefinition = "int(10) not null comment '字段最小长度'",nullable = false)
    private Integer minLength;

    @Column(columnDefinition = "int(10) not null comment '字段最大长度'",nullable = false)
    private Integer maxLength;

    @Column(columnDefinition = "varchar(10)  DEFAULT 'text' comment '编辑类型'")
    private String editType; // "text";//列表支持编辑类型

    @Column(columnDefinition = "tinyint(1) not null DEFAULT 1 comment '是否为空 1允许为空 0不允许为空'",nullable = false)
    private Integer isNull; // true;//是否允许为空

    @Column(columnDefinition = "tinyint(1) not null DEFAULT 1 comment '是否可见 1可见 0不可见'",nullable = false)
    private Integer isVisit;//列表显示

    @Column(columnDefinition = "tinyint(1) not null DEFAULT 1 comment '是否支持排序 1支持 0不支持'",nullable = false)
    private Integer isSort;//支持排序显示

    @Column(columnDefinition = "tinyint(1) not null DEFAULT 1 comment '是否支持查询 1支持 0不支持'",nullable = false)
    private Integer isSearch; // true;//支持查询
}
