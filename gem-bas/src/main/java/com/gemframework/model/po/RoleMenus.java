package com.gemframework.model.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Title: RoleMenus.java
 * @Package: com.gemframework.model.po
 * @Date: 2019/11/30 17:54
 * @Version: v1.0
 * @Description: 用户信息

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@Entity
@Table(name = "gem_role_menus")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RoleMenus extends BasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "bigint(20) not null comment '角色ID'")
    private Long roleId;

    @Column(columnDefinition = "bigint(20) not null comment '菜单ID'")
    private Long menuId;

}
