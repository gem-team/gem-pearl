package com.gemframework.model.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Title: UserDepts.java
 * @Package: com.gemframework.model.po
 * @Date: 2019/11/30 17:54
 * @Version: v1.0
 * @Description: 用户信息

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Entity
@Table(name = "gem_user_depts")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class UserDepts extends BasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "bigint(20) not null comment '用户ID'")
    private Long userId;

    @Column(columnDefinition = "bigint(20) not null comment '角色ID'")
    private Long deptId;
}
