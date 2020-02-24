#### 项目简介
Pearl 是Gem生态中的成员之一，基于SpringBoot2.2+开发的用户权限系统，内置强大基础业务能力，完善的权限控制体系，高效稳定的底层支撑。为企业提供拿来即用的基础框架，也是您学习Java企业级微服务平台的最佳学习案例。
http://www.gemframework.com

基础能力建设：精细化权限管理，自定义菜单配置，安全身份认证，系统监控，代码生成，示例演示等。官方提供完善的API文档、部署文档、架构介绍文档以及视频教程帮助您快速学习入门，快速上手使用。

GemFrame团队致力为全球中小型企业提供更多基础框架最全面的解决方案！

```
   ______                          ________
 .' ___  |                        |_   __  |
/ .'   \_|   .---.   _ .--..--.     | |_ \_|  _ .--.   ,--.    _ .--..--.    .---.
| |   ____  / /__\\ [ `.-. .-. |    |  _|    [ `/'`\] `'_\ :  [ `.-. .-. |  / /__\\
\ `.___]  | | \__.,  | | | | | |   _| |_      | |     // | |,  | | | | | |  | \__.,
 `._____.'   '.__.' [___||__||__] |_____|    [___]    \'-;__/ [___||__||__]  '.__.'

         GemFrame一款基于SpringBoot优秀的国产开源框架 http://www.gemframework.com
```


#### 软件架构
![Gem架构图](https://images.gitee.com/uploads/images/2019/1215/223822_6d41d924_1388237.png "屏幕截图.png")

#### 代码结构
```
gem 
 |--gem-api 对外API支持；为前后端分离提供数据接口
 |--gem-bas 项目基础包
 |--gem-cms 管理后台Web

```

#### 项目特点

- 基于全新的Spring Boot2.0+、SpringSecurity生态技术高度整合，提供稳定、高效、安全的基础架构。
- 采用主流的BootStrap、Layui等前端模版框架，上手简单、美观大气、交互体验更完美。
- 多数据源驱动支持，可按需接入MySQL、Oracle、SQL Server等主流数据库。
- Maven多模块管理，组件独立且共享，可快速拼装，按需增减。真正解耦合，提高效率。
- 集成Swagger模块，自定义注解使用，代码侵入性极低，API测试So Easy。
- 持久层实体类采用@Table注解配置，JAP对象映射爽翻天，妈妈再也不用担心你的表结构。
- 完善的认证鉴权，密码策略、安全审计、日志收集体系，使业务处理更安全、更透明。
- 代码风格优雅简洁、通俗易懂，符合《阿里巴巴JAVA开发手册》规范要求，可作为企业代码规范


#### 技术选型
##### 前端集成

- BootStrap 4.x
- BootStrap.Table
- BootStrap.Multiselect
- JQuery 3.x
- JQuery.Treetable
- JQuery.Validate
- JQuery.Ztree
- Layui 2.5.x
- Layer 3.1.x
- Ionicons
- Googleapis
- FontUbuntu
- FontAwesome


##### 后端集成

- Java 1.8+
- Maven
- MySQL
- Redis
- SpringBoot 2.2+
- SpringSecurity
- Spring Data JPA
- Thymeleaf
- Devtools
- Fastjson
- Swagger
- Druid
- Kaptcha
- Lombok

#### 内置功能

- 资源管理：系统中的菜单，按钮，功能权限，查询权限等元素统称为系统资源。
- 角色管理：精细化资源授权和数据授权，实现菜单，按钮，自定义数据权限的控制。
- 用户管理：登录系统的帐号亦称“系统用户”，用户可以关联一个或多个角色。
- 部门管理：部门也可以称为“组织”，是将系统用户进行组织架构划分的模块。
- 字典管理：系统字典管理，常量管理。
- 日志管理：系统业务操作日志，API调用日志，用户登录日志等。
- 监控管理：系统链路监控，服务TPS，QPS监控，SQL连接池监控等。
- 代码生成：自定义模块信息一键生成全流程代码，拿来即用，减少80%重复工作量。


#### 功能特点

- 强大的角色能力，菜单级，按钮级，表单级，数据级进行精细化权限控制
- 丰富的报表形式，通过快速配置，实现曲线图，柱状图，饼状图等数据报表
- 支持多种文件格式处理能力，实现上传，下载，播放加载，导入导出等功能
- 灵活的日志管理，含登录日志、操作日志、异常日志，主便审计及BUG定位


#### 安装教程

- 通过git下载源码
- 创建数据库gem，数据库编码为UTF8
- 执行resrouce/gem-mysql.sql文件，初始化表数据。【手动可选】
- 修改application.properties/application.yml文件，更新MySQL账号和密码
- 在gemframe目录下，执行mvn clean install
- Eclipse、IDEA打开项目
- 运行gem-cms中的CmsApplication.java
- gem-cms访问地址：http://localhost:xxxx/admin/login
- 账号密码：admin/123
- swagger接口测试：http://localhost:[业务端口]/swagger-ui.html

> 如果用户名密码不正确
    修改配置
    gem:
      security:
        open:  **false** 关闭权限校验


#### 演示地址

[http://demo.gemframework.com](http://www.gemframework.com)

#### 系统截图

- 开发中...

#### 使用说明

- 暂无

#### 问题反馈

1.  开发文档：暂无
2.  项目文档：暂无
3.  官方社区：https://www.gemframework.com/bbs
4.  gitee仓库：https://gitee.com/zzimo/gem
5.  github仓库：暂无
6.  官方网站：http://www.gemframework.com
7.  官方QQ群：72940788、446017307
8.  如需关注项目最新动态，请Watch、Star项目，同时也是对项目最好的支持 技术讨论、二次开发等咨询、问题和建议，请移步到官方社区，我会在第一时间进行解答和回复！
