package com.gemframework.model.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.List;

/**
 * @Title: Dept.java
 * @Package: com.gemframework.model.po
 * @Date: 2019/11/30 17:54
 * @Version: v1.0
 * @Description: 部门（针对User）

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Entity
@Table(name = "gem_dept")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dept extends BasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(10) not null comment '部门名称'",nullable = false)
    private String name;

    @Column(columnDefinition = "int(20) comment '父ID'")
    private Long pid;

    @Column(columnDefinition = "tinyint(1) comment '级别'")
    private Integer level;

    @Column(columnDefinition = "varchar(300) comment '描述'")
    private String desp;

    //系列 用于归类 存放家族一级分类ID 一级分类存自己ID
    @Column(columnDefinition = "varchar(20) comment '所属系列'")
    private String series;

    //路径 1-2-1
    @Column(columnDefinition = "varchar(20) comment 'ID路径'")
    private String idPath;

    //详情字段
    @Column(columnDefinition = "varchar(20) comment '部门编号'")
    private String num;
    @Column(columnDefinition = "varchar(10) comment '负责人'")
    private String boss;
    @Column(columnDefinition = "varchar(20) comment '简称'")
    private String abbr;
    @Column(columnDefinition = "varchar(20) comment '类型'")
    private String type;
    @Column(columnDefinition = "varchar(20) comment '电话'")
    private String tel;
    @Column(columnDefinition = "varchar(20) comment '邮箱'")
    private String email;


    //父级的路径 1-2-1
    @Transient
    private String parentIdPath;
    @Transient
    List<Dept> childs;
}
