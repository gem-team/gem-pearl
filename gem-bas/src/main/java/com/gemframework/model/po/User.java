package com.gemframework.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
@Table(name = "gem_user")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class User extends BasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(20) not null comment '用户名'",nullable = false)
    private String username;

    @Column(columnDefinition = "varchar(150) not null comment '密码'",nullable = false)
    private String password;

    @Column(columnDefinition = "int(5) not null comment '工号'",nullable = false)
    private Integer worknum;

    @Column(columnDefinition = "int(5) not null comment '归属部门ID'",nullable = false)
    private Long dept_id;

    @Column(columnDefinition = "varchar(5) not null comment '岗位'",nullable = false)
    private String post;

    @Column(columnDefinition = "varchar(10) not null comment '用户名'",nullable = false)
    private String realname;

    @Column(columnDefinition = "int(1) comment '性别'")
    private Integer sex;

    @Column(columnDefinition = "datetime comment '生日'")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    @Column(columnDefinition = "int(5) comment '省份code'")
    private Integer province;
    @Column(columnDefinition = "int(5) comment '城市code'")
    private Integer city;
    @Column(columnDefinition = "int(5) comment '区县code'")
    private Integer area;
    @Column(columnDefinition = "varchar(80) comment '地址'")
    private String address;

    @Column(columnDefinition = "varchar(15) not null comment '手机号'",nullable = false, unique = true)
    private String phone;

    @Column(columnDefinition = "varchar(11) comment '座机号'")
    private String tel;

    @Column(columnDefinition = "varchar(30) comment '邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(15) comment 'QQ'")
    private String qq;
    @Column(columnDefinition = "varchar(100) comment '其他备注'")
    private String desp;

    @Transient
    private List<Role> roles;
    @Transient
    private List<String> roleNames;
    @Transient
    private List<Dept> depts;
    @Transient
    private List<String> deptNames;
}
