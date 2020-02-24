package com.gemframework.model.po;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "gem_sysLog")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class SysLog extends BasePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(20) comment '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(10) comment '操作帐号'")
    private String account;

    @Column(columnDefinition = "tinyint(1) comment '操作类型 0登录 1其他'")
    private Integer operateType;

    @Column(columnDefinition = "tinyint(1) comment '操作状态 0成功 1失败'")
    private Integer operateStatus;

    @Column(columnDefinition = "varchar(32) comment '客户端IP'")
    private String clientIp;

    @Column(columnDefinition = "varchar(32) comment '地理地址'")
    private String address;

    @Column(columnDefinition = "varchar(100) comment '请求URL'")
    private String requestUrl;

    @Column(columnDefinition = "varchar(10) comment '请求方法'")
    private String requestMothod;

    @Column(columnDefinition = "varchar(100) comment '请求参数'")
    private String requestArgs;

}
