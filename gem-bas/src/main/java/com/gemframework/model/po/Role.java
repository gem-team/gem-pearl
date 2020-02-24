package com.gemframework.model.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @Title: User.java
 * @Package: com.gemframework.model.po
 * @Date: 2019/11/30 17:54
 * @Version: v1.0
 * @Description: 用户信息

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Entity
@Table(name = "gem_role")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Role extends BasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(10) not null comment '角色名称'",nullable = false, unique = true)
    private String rolename;

    @Column(columnDefinition = "varchar(10) not null comment '标识'",nullable = false, unique = true)
    private String flag;

    @Column(columnDefinition = "varchar(100) not null comment '描述'",nullable = false)
    private String desp;

    @Column(columnDefinition = "int(2) not null comment '数据范围'",nullable = false)
    private Integer datarange;

    @Transient
    private List<Menu> menus;

    @Transient
    private List<Dept> depts;

}
