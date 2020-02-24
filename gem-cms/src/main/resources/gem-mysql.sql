/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : gem

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2020-02-23 22:24:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gem_dept
-- ----------------------------
DROP TABLE IF EXISTS `gem_dept`;
CREATE TABLE `gem_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abbr` varchar(20) DEFAULT NULL COMMENT '简称',
  `boss` varchar(10) DEFAULT NULL COMMENT '负责人',
  `desp` varchar(300) DEFAULT NULL COMMENT '描述',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `id_path` varchar(20) DEFAULT NULL COMMENT 'ID路径',
  `level` tinyint(1) DEFAULT NULL COMMENT '级别',
  `name` varchar(10) NOT NULL COMMENT '部门名称',
  `num` varchar(20) DEFAULT NULL COMMENT '部门编号',
  `pid` int(20) DEFAULT NULL COMMENT '父ID',
  `series` varchar(20) DEFAULT NULL COMMENT '所属系列',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='系统部门表';

-- ----------------------------
-- Records of gem_dept
-- ----------------------------
INSERT INTO `gem_dept` VALUES ('1', 'BJ', '张三', '北京分公司有限公司，位于中国缝制设备制造之都-，自1999年创建以来，经过二十年不懈的努力发展，公司在缝制行业树立起自己的形象和品牌。今天的中工缝制设备制造，已经涵盖了自动化缝制设备 特种机缝制设备。', 'fwg.bj@wanyong.com', '01', '1', '北京分公司', '010', '0', '01', '010-66889988', '二级单位', null, null);
INSERT INTO `gem_dept` VALUES ('2', '', '', '', '', '02', '1', '广东分公司', '', '0', '02', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('3', '', '', '', '', '02-03', '2', '深圳分公司', '', '2', '02', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('4', '', '', '', '', '02-04', '2', '广州分公司', '', '2', '02', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('5', '海淀', '李四', '海淀办事处', 'wanyongedu@163.com', '01-05', '2', '海淀办事处', '01001', '1', '01', '010-66889988', '内部', null, '2020-02-23 21:35:25');
INSERT INTO `gem_dept` VALUES ('6', '', '', '', '', '06', '1', '上海分公司', '', '0', '06', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('7', '', '1212', '', '', '07', '1', '河北分公司', '1212', '0', '07', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('8', '', '', '', '', '08', '1', '天津分公司', '', '0', '08', '', '', null, '2020-02-23 15:58:54');
INSERT INTO `gem_dept` VALUES ('10', '', '', '', '', '10', '1', '重庆分公司', '', '0', '10', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('11', '', '', '', '', '06-11', '2', '浦东分公司', '', '6', '06', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('12', 'SHFGSI', '张松', '', 'shanghai@qq.com', '06-12', '2', '松江分公司', '1001', '6', '06', '022-80898213', '自营', null, null);
INSERT INTO `gem_dept` VALUES ('13', '', '', '', '', '07-13', '2', '邯郸分公司', '', '7', '07', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('14', '', '', '', '', '07-14', '2', '石家庄分公司', '', '7', '07', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('15', '', '', '', '', '08-15', '2', '静海分公司', '', '8', '08', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('16', '', '', '', '', '07-16', '2', '保定分公司', '', '7', '07', '', '', null, null);
INSERT INTO `gem_dept` VALUES ('17', '', '', '', '', '10-17', '2', '重庆', '', '10', '10', '', '', null, null);

-- ----------------------------
-- Table structure for gem_menu
-- ----------------------------
DROP TABLE IF EXISTS `gem_menu`;
CREATE TABLE `gem_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) DEFAULT NULL COMMENT '是否选中 0 未选中 1 选中',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  `id_path` varchar(20) DEFAULT NULL COMMENT 'ID路径',
  `level` tinyint(1) DEFAULT NULL COMMENT '级别，最大支持三级',
  `link` varchar(50) DEFAULT NULL COMMENT '菜单/按钮链接',
  `name` varchar(10) DEFAULT NULL COMMENT '菜单/按钮名称',
  `pid` bigint(20) DEFAULT '0' COMMENT '父级ID',
  `series` varchar(20) DEFAULT NULL COMMENT '所属系列',
  `sort_number` int(10) DEFAULT NULL COMMENT '排序编号',
  `tag` varchar(30) DEFAULT NULL COMMENT '菜单/按钮标签',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型 0菜单 1按钮 2其他',
  `sort_path` varchar(150) DEFAULT NULL COMMENT '排序编号路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';

-- ----------------------------
-- Records of gem_menu
-- ----------------------------
INSERT INTO `gem_menu` VALUES ('2', null, 'fab fa-angular', '02', '1', '#', '权限管理', '0', '02', '8', 'menu_sys', '0', '08-02', null, '2020-02-23 21:48:06');
INSERT INTO `gem_menu` VALUES ('3', null, 'fas fa-user-circle', '02-03', '2', 'user/list.html', '用户管理', '2', '02', '5', 'menu_user', '0', '08-02-05-02-03', null, null);
INSERT INTO `gem_menu` VALUES ('4', null, 'fas fa-sitemap', '02-04', '2', 'dept/list.html', '部门管理', '2', '02', '4', 'menu_dept', '0', '08-02-04-02-04', null, null);
INSERT INTO `gem_menu` VALUES ('5', null, 'fas fa-rocket', '02-05', '2', 'role/list.html', '角色管理', '2', '02', '2', 'menu_role', '0', '08-02-02-02-05', null, null);
INSERT INTO `gem_menu` VALUES ('6', null, 'fab fa-adn', '02-06', '2', 'menu/list.html', '菜单管理', '2', '02', '1', 'menu_menu', '0', '08-02-01-02-06', null, '2020-02-23 21:35:36');
INSERT INTO `gem_menu` VALUES ('7', null, 'fas fa-box', '38-07', '2', '#', '外部网站', '38', '07', '998', 'menu_website', '0', '998-07', null, '2020-02-23 15:30:17');
INSERT INTO `gem_menu` VALUES ('8', null, 'fab fa-angular', '07-08', '2', 'http://www.baidu.com', '百度一下', '7', '07', '4', 'menu_api', '0', '998-07-04-07-08', null, null);
INSERT INTO `gem_menu` VALUES ('9', null, 'fab fa-apple', '07-09', '2', 'http://www.gemframework.com/shouquan.html', '商业授权', '7', '07', '3', 'menu_app', '0', '998-07-03-07-09', null, null);
INSERT INTO `gem_menu` VALUES ('10', null, 'fab fa-amilia', '10', '1', '#', '一级菜单', '0', '10', '999', 'menu_1', '0', '999-10', null, '2020-02-23 15:26:50');
INSERT INTO `gem_menu` VALUES ('11', null, 'fab fa-amazon', '10-11', '2', '123', '二级菜单', '10', '10', '99', 'menu_2', '0', '999-10-99-10-11', null, null);
INSERT INTO `gem_menu` VALUES ('12', null, 'fas fa-angle-double-right', '10-11-12', '3', 'HTTP://JSON.CN', '三级菜单', '11', '10', '99', 'menu_json', '0', '999-10-99-10-11-99-10-11-12', null, null);
INSERT INTO `gem_menu` VALUES ('14', null, '', '02-06-14', '3', 'menu/delete', '删除按钮', '6', '02', '3', 'btn_menu_del', '1', '08-02-01-02-06-03-02-06-14', null, null);
INSERT INTO `gem_menu` VALUES ('15', null, '', '02-05-15', '3', 'role/pageByParams', '分页查询', '5', '02', '1', 'fun_role_page', '2', '08-02-02-02-05-01-02-05-15', null, null);
INSERT INTO `gem_menu` VALUES ('16', null, '', '02-04-16', '3', 'findAllDeptTree', '左侧菜单树', '4', '02', '99', 'fun_dept_tree', '2', '08-02-04-02-04-99-02-04-16', null, null);
INSERT INTO `gem_menu` VALUES ('17', null, '', '02-03-17', '3', 'user/pageByParams', '分页查询', '3', '02', '1', 'fun_user_page', '2', '08-02-05-02-03-01-02-03-17', null, null);
INSERT INTO `gem_menu` VALUES ('18', null, '', '02-05-18', '3', 'role/add', '添加/编辑', '5', '02', '99', 'fun_role_add', '2', '08-02-02-02-05-99-02-05-18', null, null);
INSERT INTO `gem_menu` VALUES ('19', null, '', '02-04-19', '3', 'dept/getOne', '查询单个部门', '4', '02', '99', 'fun_dept_get', '2', '08-02-04-02-04-99-02-04-19', null, null);
INSERT INTO `gem_menu` VALUES ('20', null, '', '02-04-20', '3', 'dept/add', '添加/编辑', '4', '02', '99', 'fun_dept_add', '2', '08-02-04-02-04-99-02-04-20', null, null);
INSERT INTO `gem_menu` VALUES ('21', null, '', '02-06-21', '3', 'menu/add', '添加/编辑', '6', '02', '1', 'fun_menu_add', '2', '08-02-01-02-06-01-02-06-21', null, null);
INSERT INTO `gem_menu` VALUES ('23', null, '', '02-05-23', '3', 'role/list', '下拉框角色列表', '5', '02', '991', 'fun_role_list', '2', '08-02-02-02-05-991-02-05-23', null, null);
INSERT INTO `gem_menu` VALUES ('24', null, '', '02-03-24', '3', 'user/add', '添加/编辑', '3', '02', '99', 'fun_user_add', '2', '08-02-05-02-03-99-02-03-24', null, null);
INSERT INTO `gem_menu` VALUES ('25', null, '', '02-06-25', '3', 'menu/add.html', '添加按钮', '6', '02', '99', 'btn_menu_add', '1', '08-02-01-02-06-99-02-06-25', null, null);
INSERT INTO `gem_menu` VALUES ('26', null, '', '02-06-26', '3', 'menu/edit.html', '编辑按钮', '6', '02', '99', 'btn_menu_edit', '1', '08-02-01-02-06-99-02-06-26', null, null);
INSERT INTO `gem_menu` VALUES ('27', null, '', '02-05-27', '3', 'role/add.html', '添加按钮', '5', '02', '99', 'btn_role_add', '1', '08-02-02-02-05-99-02-05-27', null, null);
INSERT INTO `gem_menu` VALUES ('28', null, '', '02-05-28', '3', 'role/edit.html', '编辑按钮', '5', '02', '99', 'btn_role_edit', '1', '08-02-02-02-05-99-02-05-28', null, null);
INSERT INTO `gem_menu` VALUES ('29', null, '', '02-05-29', '3', 'role/delete', '删除按钮', '5', '02', '99', 'btn_role_del', '1', '08-02-02-02-05-99-02-05-29', null, null);
INSERT INTO `gem_menu` VALUES ('30', null, '', '02-03-30', '3', 'user/add.html', '添加按钮', '3', '02', '99', 'btn_user_add', '1', '08-02-05-02-03-99-02-03-30', null, null);
INSERT INTO `gem_menu` VALUES ('31', null, '', '02-03-31', '3', 'user/edit.html', '编辑按钮', '3', '02', '99', 'btn_user_edit', '1', '08-02-05-02-03-99-02-03-31', null, null);
INSERT INTO `gem_menu` VALUES ('32', null, 'fas fa-globe', '38-32', '2', '#', '源码下载', '38', '32', '997', 'menu_src', '0', '997-32', null, '2020-02-23 15:30:33');
INSERT INTO `gem_menu` VALUES ('33', null, 'fab fa-github', '32-33', '2', 'https://gitee.com/gemteam/gemframe', '码云地址', '32', '32', '2', 'menu_gitee', '0', '997-32-02-32-33', null, null);
INSERT INTO `gem_menu` VALUES ('34', null, 'fab fa-windows', '32-34', '2', 'http://www.gemframework.com', 'GemFrame官网', '32', '32', '3', 'menu_gem', '0', '997-32-03-32-34', null, null);
INSERT INTO `gem_menu` VALUES ('35', null, 'fas fa-basketball-ball', '32-35', '2', 'http://www.gemframework.com/bbs', '官方社区', '32', '32', '1', 'menu_bbs', '0', '997-32-01-32-35', null, null);
INSERT INTO `gem_menu` VALUES ('36', null, 'fas fa-desktop', '36', '1', '112', '系统监控', '0', '36', '99', 'menu_monitor', '0', '99-36', null, '2020-02-21 20:42:24');
INSERT INTO `gem_menu` VALUES ('37', null, 'fas fa-database', '36-37', '2', 'druid', 'MySQL监控', '36', '36', '99', 'menu_druid', '0', '99-36-99-36-37', null, null);
INSERT INTO `gem_menu` VALUES ('38', null, 'fas fa-video', '38', '1', '#', '示例演示', '0', '38', '99', 'menu_demo', '0', '99-38', null, null);
INSERT INTO `gem_menu` VALUES ('39', null, 'fab fa-first-order', '38-39', '2', 'demo/pages/redis', 'Redis示例', '38', '38', '1', 'menu_redis', '0', '99-38-99-38-39', null, '2020-02-23 15:27:44');
INSERT INTO `gem_menu` VALUES ('40', null, 'fas fa-lightbulb', '36-40', '2', 'doc.html', '接口调试', '36', '36', '99', 'menu_swagger', '0', '99-36-99-36-40', null, '2020-02-21 19:56:39');
INSERT INTO `gem_menu` VALUES ('41', null, '', '02-41', '2', 'orderInfo', '首页订单信息', '2', '02', '99', 'fun_index_order', '2', '08-02-99-02-41', null, null);
INSERT INTO `gem_menu` VALUES ('45', null, 'fas fa-file-code', '38-45', '2', 'generate/code/list.html', '代码生成', '38', '45', '99', 'menu_code', '0', '99-45', null, '2020-02-23 21:24:55');
INSERT INTO `gem_menu` VALUES ('51', null, 'fab fa-modx', '38-45-84-51', '3', 'module/list.html', '模块管理', '84', '45', '99', 'menu_module', '2', '99-45-99-45-84-99-45-84-51', null, '2020-02-23 15:28:59');
INSERT INTO `gem_menu` VALUES ('52', null, '', '45-84-51-52', '3', 'module/pageByParams', '分页查询', '51', '45', '99', 'fun_module_page', '1', '99-45-99-45-84-99-45-84-51-99-45-84-51-52', null, '2020-02-21 20:53:15');
INSERT INTO `gem_menu` VALUES ('53', null, '', '45-84-51-53', '3', 'module/add.html', '添加按钮', '51', '45', '99', 'btn_module_add', '1', '99-45-99-45-84-99-45-84-51-99-45-84-51-53', null, '2020-02-21 20:55:11');
INSERT INTO `gem_menu` VALUES ('54', null, '', '45-84-51-54', '3', 'module/edit.html', '编辑按钮', '51', '45', '99', 'btn_module_edit', '1', '99-45-99-45-84-99-45-84-51-99-45-84-51-54', null, '2020-02-21 20:55:13');
INSERT INTO `gem_menu` VALUES ('55', null, '', '45-84-51-55', '3', 'module/delete', '删除按钮', '51', '45', '99', 'btn_module_del', '1', '99-45-99-45-84-99-45-84-51-99-45-84-51-55', null, '2020-02-21 20:55:17');
INSERT INTO `gem_menu` VALUES ('56', null, '', '45-84-51-56', '3', 'module/add', '添加/编辑', '51', '45', '99', 'fun_module_add', '1', '99-45-99-45-84-99-45-84-51-99-45-84-51-56', null, '2020-02-21 20:55:20');
INSERT INTO `gem_menu` VALUES ('57', null, 'fas fa-certificate', '45-84-57', '3', 'moduleAttr/list.html', '属性管理', '84', '45', '99', 'menu_module_attr', '2', '99-45-99-45-84-99-45-84-57', null, '2020-02-22 21:17:37');
INSERT INTO `gem_menu` VALUES ('58', null, '', '45-84-57-58', '3', 'moduleAttr/pageByParams', '分页查询', '57', '45', '99', 'fun_moduleAttr_page', '1', '99-45-99-45-84-99-45-84-57-99-45-84-57-58', null, '2020-02-21 20:53:47');
INSERT INTO `gem_menu` VALUES ('59', null, '', '45-84-57-59', '3', 'moduleAttr/add.html', '添加按钮', '57', '45', '99', 'btn_moduleAttr_add', '1', '99-45-99-45-84-99-45-84-57-99-45-84-57-59', null, '2020-02-21 20:53:41');
INSERT INTO `gem_menu` VALUES ('60', null, '', '45-84-57-60', '3', 'moduleAttr/edit.html', '编辑按钮', '57', '45', '99', 'btn_moduleAttr_edit', '1', '99-45-99-45-84-99-45-84-57-99-45-84-57-60', null, '2020-02-21 20:53:37');
INSERT INTO `gem_menu` VALUES ('61', null, '', '45-84-57-61', '3', 'moduleAttr/add', '添加/编辑', '57', '45', '99', 'fun_moduleAttr_add', '1', '99-45-99-45-84-99-45-84-57-99-45-84-57-61', null, '2020-02-21 20:53:30');
INSERT INTO `gem_menu` VALUES ('62', null, '', '45-84-57-62', '3', 'moduleAttr/delete', '删除按钮', '57', '45', '99', 'btn_moduleAttr_delete', '1', '99-45-99-45-84-99-45-84-57-99-45-84-57-62', null, '2020-02-21 20:53:28');
INSERT INTO `gem_menu` VALUES ('63', null, '', '45-84-57-63', '3', 'module/list', '获取模块列表', '57', '45', '99', 'fun_module_list', '1', '99-45-99-45-84-99-45-84-57-99-45-84-57-63', null, '2020-02-21 20:53:25');
INSERT INTO `gem_menu` VALUES ('64', null, '', '45-84-51-64', '3', 'module/generateCode', '生成代码按钮', '51', '45', '99', 'btn_code_generate', '1', '99-45-99-45-84-99-45-84-51-99-45-84-51-64', null, '2020-02-21 20:55:05');
INSERT INTO `gem_menu` VALUES ('65', null, '', '45-84-51-65', '3', 'module/downloadCode', '代码下载', '51', '45', '99', 'btn_module_download', '1', '99-45-99-45-84-99-45-84-51-99-45-84-51-65', null, '2020-02-21 20:54:52');
INSERT INTO `gem_menu` VALUES ('66', null, '', '45-84-51-66', '3', 'module/deleteBatch', '批量删除', '51', '45', '99', 'plsc', '1', '99-45-99-45-84-99-45-84-51-99-45-84-51-66', null, '2020-02-21 20:53:55');
INSERT INTO `gem_menu` VALUES ('67', null, '', '45-84-57-67', '3', 'moduleAttr/deleteBatch', '批量删除', '57', '45', '99', 'plsc1', '1', '99-45-99-45-84-99-45-84-57-99-45-84-57-67', null, '2020-02-21 20:53:21');
INSERT INTO `gem_menu` VALUES ('68', null, 'fas fa-exclamation-circle', '38-68', '2', 'errorpages', '错误页面', '38', '68', '99', 'errorpages', '0', '99-68', null, '2020-02-23 15:29:46');
INSERT INTO `gem_menu` VALUES ('69', null, 'far fa-flag', '68-69', '2', '404', '公益404', '68', '68', '99', '404', '0', '99-68-99-68-69', null, '2020-02-22 20:25:25');
INSERT INTO `gem_menu` VALUES ('70', null, 'fas fa-flag-checkered', '68-70', '2', '403', '常规403', '68', '68', '99', '403', '0', '99-68-99-68-70', null, '2020-02-22 20:26:49');
INSERT INTO `gem_menu` VALUES ('71', null, 'fas fa-th-large', '36-71', '2', 'sysLog/list.html', '系统日志', '36', '36', '99', 'log', '0', '99-36-99-36-71', null, null);
INSERT INTO `gem_menu` VALUES ('73', null, '', '36-71-73', '3', 'sysLog/pageByParams', '分页查询日志', '71', '36', '99', 'logpage', '1', '99-36-99-36-71-99-36-71-73', null, null);
INSERT INTO `gem_menu` VALUES ('74', null, '', '36-71-74', '3', 'sysLog/deleteBatch', '清空日志', '71', '36', '99', 'deleteBatch', '1', '99-36-99-36-71-99-36-71-74', null, null);
INSERT INTO `gem_menu` VALUES ('75', null, '', '02-06-75', '3', 'menu/addChild.html', '添加子节点', '6', '02', '99', 'addChild', '1', '08-02-01-02-06-99-02-06-75', null, null);
INSERT INTO `gem_menu` VALUES ('79', null, '', '02-03-79', '3', 'user/resetPassword', '重置密码', '3', '02', '99', 'user/resetPasswor', '1', '08-02-05-02-03-99-02-03-79', null, null);
INSERT INTO `gem_menu` VALUES ('81', null, '', '02-06-81', '3', 'common/resetSideMenus', '同步侧菜单信息', '6', '02', '99', 'resetSideMenus', '1', '08-02-01-02-06-99-02-06-81', null, null);
INSERT INTO `gem_menu` VALUES ('82', null, 'fas fa-file-alt', '38-82', '2', 'demo/pages/pageStyle', '页面风格', '38', '38', '10', 'pagestyle', '0', '99-38-99-38-82', null, '2020-02-23 15:27:35');
INSERT INTO `gem_menu` VALUES ('83', null, 'far fa-comments', '83', '1', 'doc.html', '消息管理', '0', '83', '99', 'message', '0', '99-83', null, '2020-02-21 19:47:38');
INSERT INTO `gem_menu` VALUES ('84', null, 'fas fa-angle-double-right', '38-45-84', '2', 'module/list.html', '正向生成', '45', '45', '99', 'zxsc', '0', '99-45-99-45-84', null, '2020-02-23 15:28:51');
INSERT INTO `gem_menu` VALUES ('85', null, 'fas fa-angle-double-left', '45-85', '2', 'code/list.html', '逆向生成', '45', '45', '99', 'nxsc', '0', '99-45-99-45-85', null, '2020-02-22 13:18:02');
INSERT INTO `gem_menu` VALUES ('86', null, '', '45-85-86', '3', 'code/page', '分页查询', '85', null, '99', 'code/page', '1', null, '2020-02-23 20:00:29', '2020-02-23 20:00:29');

-- ----------------------------
-- Table structure for gem_module
-- ----------------------------
DROP TABLE IF EXISTS `gem_module`;
CREATE TABLE `gem_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(10) DEFAULT NULL COMMENT '作者签名',
  `is_add` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否具备添加功能 1：是 0：否',
  `is_del` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否具备删除功能 1：是 0：否',
  `is_edit` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否具备编辑功能 1：是 0：否',
  `is_query` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否具备查询功能 1：是 0：否',
  `name_cn` varchar(60) NOT NULL COMMENT '模块中文名称',
  `name_en` varchar(30) NOT NULL COMMENT '模块英文名称',
  `package_name` varchar(30) NOT NULL COMMENT '包名',
  `page_height` int(10) DEFAULT '450' COMMENT '编辑页面高度',
  `page_width` int(10) DEFAULT '550' COMMENT '编辑页面宽度',
  `pk_nane` varchar(60) NOT NULL COMMENT '主键名称',
  `is_generate` tinyint(1) DEFAULT '0' COMMENT '是否生成CODE, 1：是 0：否',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='系统模块表';

-- ----------------------------
-- Records of gem_module
-- ----------------------------
INSERT INTO `gem_module` VALUES ('4', 'gems', '1', '1', '1', '1', '组织管理', 'org', 'com.test', '500', '400', 'name', '1', null, '2020-02-23 21:56:16');
INSERT INTO `gem_module` VALUES ('39', 'gemteam', '0', '1', '0', '1', '系统日志', 'sysLog', 'com.gemframework', '450', '550', 'name', '1', null, null);

-- ----------------------------
-- Table structure for gem_module_attr
-- ----------------------------
DROP TABLE IF EXISTS `gem_module_attr`;
CREATE TABLE `gem_module_attr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attr_name` varchar(30) NOT NULL COMMENT '字段名称',
  `attr_type` varchar(30) NOT NULL COMMENT '字段类型',
  `comment` varchar(30) NOT NULL COMMENT '字段描述',
  `edit_type` varchar(10) DEFAULT 'text' COMMENT '编辑类型',
  `is_null` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否为空 1允许为空 0不允许为空',
  `is_search` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否支持查询 1支持 0不支持',
  `is_sort` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否支持排序 1支持 0不支持',
  `is_visit` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可见 1可见 0不可见',
  `max_length` int(10) NOT NULL COMMENT '字段最大长度',
  `min_length` int(10) NOT NULL COMMENT '字段最小长度',
  `module_id` bigint(20) DEFAULT NULL,
  `options` varchar(300) DEFAULT NULL COMMENT '下拉框内容',
  `attr_sort` int(2) NOT NULL COMMENT '字段顺序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COMMENT='模块属性表';

-- ----------------------------
-- Records of gem_module_attr
-- ----------------------------
INSERT INTO `gem_module_attr` VALUES ('42', 'client_ip', 'text', '日志名称', null, '1', '1', '1', '1', '20', '1', '39', null, '4', null, null);
INSERT INTO `gem_module_attr` VALUES ('43', 'type', 'text', '日志类型', null, '1', '1', '1', '1', '2', '1', '39', null, '5', null, null);
INSERT INTO `gem_module_attr` VALUES ('44', 'desp', 'text', '日志内容', null, '1', '1', '1', '1', '200', '0', '39', null, '6', null, null);
INSERT INTO `gem_module_attr` VALUES ('45', 'id', 'text', '自增ID', null, '0', '0', '0', '0', '20', '1', '39', null, '1', null, null);
INSERT INTO `gem_module_attr` VALUES ('46', 'account', 'text', '系统账号', null, '1', '1', '1', '1', '20', '0', '39', null, '3', null, null);
INSERT INTO `gem_module_attr` VALUES ('47', 'user_id', 'text', '用户ID', null, '1', '1', '1', '1', '20', '0', '39', null, '2', null, null);
INSERT INTO `gem_module_attr` VALUES ('48', 'id', 'text', '组织ID', null, '1', '1', '1', '1', '20', '2', '4', null, '99', null, '2020-02-23 21:56:16');
INSERT INTO `gem_module_attr` VALUES ('49', 'name', 'text', '组织名称', null, '1', '1', '1', '1', '10', '2', '4', null, '99', null, '2020-02-23 21:56:16');
INSERT INTO `gem_module_attr` VALUES ('50', 'logo', 'text', '组织Logo', null, '1', '1', '1', '1', '20', '0', '4', null, '99', null, '2020-02-23 21:56:16');
INSERT INTO `gem_module_attr` VALUES ('51', 'slogan', 'text', '组织口号', null, '1', '1', '1', '1', '50', '0', '4', null, '99', null, '2020-02-23 21:56:16');

-- ----------------------------
-- Table structure for gem_role
-- ----------------------------
DROP TABLE IF EXISTS `gem_role`;
CREATE TABLE `gem_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datarange` int(2) NOT NULL COMMENT '数据范围',
  `desp` varchar(100) NOT NULL COMMENT '描述',
  `flag` varchar(10) NOT NULL COMMENT '标识',
  `rolename` varchar(10) NOT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qt12q8vffue7qn0fgvb42kljf` (`flag`),
  UNIQUE KEY `UK_5ojc0xjc1im8mccxp9njmjvft` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of gem_role
-- ----------------------------
INSERT INTO `gem_role` VALUES ('1', '3', '管理员', 'admin', '管理员', null, '2020-02-23 22:07:45');
INSERT INTO `gem_role` VALUES ('18', '0', '测试演示角色', 'test', '测试角色', null, null);

-- ----------------------------
-- Table structure for gem_role_depts
-- ----------------------------
DROP TABLE IF EXISTS `gem_role_depts`;
CREATE TABLE `gem_role_depts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=588 DEFAULT CHARSET=utf8mb4 COMMENT='角色部门关系表';

-- ----------------------------
-- Records of gem_role_depts
-- ----------------------------
INSERT INTO `gem_role_depts` VALUES ('57', '1', '2', null, null);
INSERT INTO `gem_role_depts` VALUES ('58', '5', '2', null, null);
INSERT INTO `gem_role_depts` VALUES ('59', '2', '2', null, null);
INSERT INTO `gem_role_depts` VALUES ('60', '3', '2', null, null);
INSERT INTO `gem_role_depts` VALUES ('61', '4', '2', null, null);
INSERT INTO `gem_role_depts` VALUES ('62', '1', '4', null, null);
INSERT INTO `gem_role_depts` VALUES ('63', '5', '4', null, null);
INSERT INTO `gem_role_depts` VALUES ('64', '2', '5', null, null);
INSERT INTO `gem_role_depts` VALUES ('65', '3', '5', null, null);
INSERT INTO `gem_role_depts` VALUES ('66', '4', '5', null, null);
INSERT INTO `gem_role_depts` VALUES ('67', '6', '5', null, null);
INSERT INTO `gem_role_depts` VALUES ('68', '11', '5', null, null);
INSERT INTO `gem_role_depts` VALUES ('69', '1', '11', null, null);
INSERT INTO `gem_role_depts` VALUES ('70', '5', '11', null, null);
INSERT INTO `gem_role_depts` VALUES ('71', '2', '11', null, null);
INSERT INTO `gem_role_depts` VALUES ('72', '3', '11', null, null);
INSERT INTO `gem_role_depts` VALUES ('73', '2', '13', null, null);
INSERT INTO `gem_role_depts` VALUES ('74', '3', '13', null, null);
INSERT INTO `gem_role_depts` VALUES ('75', '4', '13', null, null);
INSERT INTO `gem_role_depts` VALUES ('150', '1', '17', null, null);
INSERT INTO `gem_role_depts` VALUES ('151', '5', '17', null, null);
INSERT INTO `gem_role_depts` VALUES ('168', '1', '19', null, null);
INSERT INTO `gem_role_depts` VALUES ('169', '5', '19', null, null);
INSERT INTO `gem_role_depts` VALUES ('170', '1', '20', null, null);
INSERT INTO `gem_role_depts` VALUES ('171', '5', '20', null, null);
INSERT INTO `gem_role_depts` VALUES ('572', '1', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('573', '5', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('574', '2', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('575', '3', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('576', '4', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('577', '6', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('578', '11', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('579', '12', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('580', '7', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('581', '13', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('582', '14', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('583', '16', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('584', '8', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('585', '15', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('586', '10', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_depts` VALUES ('587', '17', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');

-- ----------------------------
-- Table structure for gem_role_menus
-- ----------------------------
DROP TABLE IF EXISTS `gem_role_menus`;
CREATE TABLE `gem_role_menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of gem_role_menus
-- ----------------------------
INSERT INTO `gem_role_menus` VALUES ('106', '10', '2', null, null);
INSERT INTO `gem_role_menus` VALUES ('107', '11', '2', null, null);
INSERT INTO `gem_role_menus` VALUES ('108', '12', '2', null, null);
INSERT INTO `gem_role_menus` VALUES ('109', '7', '4', null, null);
INSERT INTO `gem_role_menus` VALUES ('110', '9', '4', null, null);
INSERT INTO `gem_role_menus` VALUES ('111', '8', '4', null, null);
INSERT INTO `gem_role_menus` VALUES ('112', '10', '4', null, null);
INSERT INTO `gem_role_menus` VALUES ('113', '11', '4', null, null);
INSERT INTO `gem_role_menus` VALUES ('114', '12', '4', null, null);
INSERT INTO `gem_role_menus` VALUES ('115', '7', '5', null, null);
INSERT INTO `gem_role_menus` VALUES ('116', '9', '5', null, null);
INSERT INTO `gem_role_menus` VALUES ('117', '8', '5', null, null);
INSERT INTO `gem_role_menus` VALUES ('118', '7', '11', null, null);
INSERT INTO `gem_role_menus` VALUES ('119', '8', '11', null, null);
INSERT INTO `gem_role_menus` VALUES ('120', '2', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('121', '6', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('122', '5', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('123', '4', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('124', '3', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('125', '7', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('126', '9', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('127', '8', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('128', '10', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('129', '11', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('130', '12', '13', null, null);
INSERT INTO `gem_role_menus` VALUES ('216', '2', '16', null, null);
INSERT INTO `gem_role_menus` VALUES ('217', '6', '16', null, null);
INSERT INTO `gem_role_menus` VALUES ('218', '5', '16', null, null);
INSERT INTO `gem_role_menus` VALUES ('219', '4', '16', null, null);
INSERT INTO `gem_role_menus` VALUES ('220', '3', '16', null, null);
INSERT INTO `gem_role_menus` VALUES ('221', '10', '16', null, null);
INSERT INTO `gem_role_menus` VALUES ('222', '11', '16', null, null);
INSERT INTO `gem_role_menus` VALUES ('223', '12', '16', null, null);
INSERT INTO `gem_role_menus` VALUES ('281', '7', '17', null, null);
INSERT INTO `gem_role_menus` VALUES ('282', '9', '17', null, null);
INSERT INTO `gem_role_menus` VALUES ('283', '8', '17', null, null);
INSERT INTO `gem_role_menus` VALUES ('330', '2', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('331', '6', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('332', '21', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('333', '14', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('334', '25', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('335', '26', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('336', '5', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('337', '15', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('338', '18', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('339', '27', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('340', '28', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('341', '29', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('342', '23', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('343', '4', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('344', '16', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('345', '19', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('346', '20', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('347', '3', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('348', '17', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('349', '24', '19', null, null);
INSERT INTO `gem_role_menus` VALUES ('350', '2', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('351', '6', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('352', '21', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('353', '14', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('354', '25', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('355', '26', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('356', '5', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('357', '15', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('358', '18', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('359', '27', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('360', '28', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('361', '29', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('362', '23', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('363', '4', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('364', '16', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('365', '19', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('366', '20', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('367', '3', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('368', '17', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('369', '24', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('370', '30', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('371', '31', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('372', '41', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('373', '10', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('374', '11', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('375', '12', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('376', '7', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('377', '9', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('378', '8', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('379', '32', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('380', '35', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('381', '33', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('382', '34', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('383', '36', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('384', '37', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('385', '38', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('386', '39', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('387', '40', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('388', '44', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('389', '45', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('390', '46', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('391', '47', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('392', '48', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('393', '49', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('394', '50', '20', null, null);
INSERT INTO `gem_role_menus` VALUES ('975', '2', '18', '2020-02-23 22:03:49', '2020-02-23 22:03:49');
INSERT INTO `gem_role_menus` VALUES ('976', '3', '18', '2020-02-23 22:03:49', '2020-02-23 22:03:49');
INSERT INTO `gem_role_menus` VALUES ('977', '17', '18', '2020-02-23 22:03:49', '2020-02-23 22:03:49');
INSERT INTO `gem_role_menus` VALUES ('978', '24', '18', '2020-02-23 22:03:49', '2020-02-23 22:03:49');
INSERT INTO `gem_role_menus` VALUES ('979', '45', '18', '2020-02-23 22:03:49', '2020-02-23 22:03:49');
INSERT INTO `gem_role_menus` VALUES ('980', '2', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('981', '6', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('982', '21', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('983', '14', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('984', '5', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('985', '15', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('986', '18', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('987', '23', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('988', '4', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('989', '16', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('990', '19', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('991', '20', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('992', '3', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('993', '17', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('994', '24', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('995', '7', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('996', '9', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('997', '8', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('998', '10', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('999', '11', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');
INSERT INTO `gem_role_menus` VALUES ('1000', '12', '1', '2020-02-23 22:07:45', '2020-02-23 22:07:45');

-- ----------------------------
-- Table structure for gem_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `gem_sys_log`;
CREATE TABLE `gem_sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `account` varchar(10) DEFAULT NULL COMMENT '操作帐号',
  `address` varchar(32) DEFAULT NULL COMMENT '地理地址',
  `client_ip` varchar(32) DEFAULT NULL COMMENT '客户端IP',
  `operate_status` tinyint(1) DEFAULT NULL COMMENT '操作状态 0成功 1失败',
  `operate_type` tinyint(1) DEFAULT NULL COMMENT '操作类型 0登录 1其他',
  `request_args` varchar(100) DEFAULT NULL COMMENT '请求参数',
  `request_mothod` varchar(10) DEFAULT NULL COMMENT '请求方法',
  `request_url` varchar(100) DEFAULT NULL COMMENT '请求URL',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- ----------------------------
-- Records of gem_sys_log
-- ----------------------------
INSERT INTO `gem_sys_log` VALUES ('1', '2020-02-20 18:21:38', '2020-02-20 18:21:38', 'admin', '内网IP,内网IP', '127.0.0.1', '0', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('2', '2020-02-20 20:33:47', '2020-02-20 20:33:47', 'admin', '获取IP地址异常：null', '127.0.0.1', '0', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('3', '2020-02-20 22:35:33', '2020-02-20 22:35:33', 'admin', '内网IP,内网IP', '127.0.0.1', '1', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('4', '2020-02-20 22:35:37', '2020-02-20 22:35:37', 'admin', '内网IP,内网IP', '127.0.0.1', '0', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('5', '2020-02-21 18:57:00', '2020-02-21 18:57:00', 'admin', '获取IP地址异常：null', '127.0.0.1', '0', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('6', '2020-02-21 19:46:57', '2020-02-21 19:46:57', 'admin', '内网IP,内网IP', '127.0.0.1', '0', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('7', '2020-02-21 19:50:08', '2020-02-21 19:50:08', 'admin', '内网IP,内网IP', '127.0.0.1', '0', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('9', '2020-02-21 19:55:47', '2020-02-21 19:55:47', 'admin', '获取IP地址异常：null', '127.0.0.1', '0', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('13', '2020-02-23 22:13:39', '2020-02-23 22:13:39', 'admin', '获取IP地址异常：null', '127.0.0.1', '1', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');
INSERT INTO `gem_sys_log` VALUES ('14', '2020-02-23 22:23:51', '2020-02-23 22:23:51', 'admin', '获取IP地址异常：null', '127.0.0.1', '0', '0', null, 'POST', 'http://127.0.0.1:8088/admin/login', 'admin');

-- ----------------------------
-- Table structure for gem_user
-- ----------------------------
DROP TABLE IF EXISTS `gem_user`;
CREATE TABLE `gem_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(80) DEFAULT NULL COMMENT '地址',
  `area` int(5) DEFAULT NULL COMMENT '区县code',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `city` int(5) DEFAULT NULL COMMENT '城市code',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(150) NOT NULL COMMENT '密码',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `post` varchar(5) NOT NULL COMMENT '岗位',
  `province` int(5) DEFAULT NULL COMMENT '省份code',
  `qq` varchar(11) DEFAULT NULL COMMENT 'QQ',
  `realname` varchar(10) NOT NULL COMMENT '用户名',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `tel` varchar(11) DEFAULT NULL COMMENT '座机号',
  `username` varchar(10) NOT NULL COMMENT '用户名',
  `worknum` int(5) NOT NULL COMMENT '工号',
  `desp` varchar(11) DEFAULT NULL COMMENT '其他备注',
  `dept_id` int(5) NOT NULL COMMENT '归属部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ioc195vl2wbh2o986iimitfp3` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- ----------------------------
-- Records of gem_user
-- ----------------------------
INSERT INTO `gem_user` VALUES ('1', '北京市市辖区朝阳区', '110105', '2020-01-01 00:00:00', '110100', 'gem@163.com', '$2a$10$qg0H7hcsvzy7BlFkq2YQwu4dMJH3GqRxPrEP7oO.0fAKrL.o.vaRq', '18522299991', '1', '110000', '', 'gem', '0', '', 'admin', '2001', '', '15', null, '2020-02-23 22:23:26');
INSERT INTO `gem_user` VALUES ('7', '北京市市辖区顺义区北京庄园', '110113', null, '110100', '769990999@qq.com', '$2a$10$rMuB65K93Ch2QQ.mcklt..rROCWThLWRsLnbL2fv4xt2FjNAYLIHa', '13338880040', '1', '110000', '', '张先生', '1', '', 'zhangysh', '1001', '123', '5', null, null);
INSERT INTO `gem_user` VALUES ('8', '河北省秦皇岛市山海关区', '130303', '2020-01-31 00:00:00', '130300', '769922@qq.com', '$2a$10$g5HO4XFXVmvvqRRC10EkwOM0cewbXLzasjV4YQSjEFI8QA1sfPvO6', '13338880041', '1', '130000', '', '刘三', '0', '', 'zhaoxy', '1002', '', '8', null, null);
INSERT INTO `gem_user` VALUES ('9', '河北省唐山市路南区', '130202', null, '130200', 'wukong@qq.com', '', '13338880022', '3', '130000', '', '孙悟空', '1', '022-1233212', 'sunwuk', '1003', '', '4', null, null);
INSERT INTO `gem_user` VALUES ('10', '123456', '140303', '2019-12-28 00:00:00', '140300', '11233344444@33', '$2a$10$C7dt6zjXEmZKipEbgLaWEu9p6oYS1AahvVi1ZOxM1fzhDGudDaeQ2', '18500029040', '4', '140000', '', 'LISI', '0', '1', 'lisi', '1005', '21212', '4', null, null);
INSERT INTO `gem_user` VALUES ('12', '河北省秦皇岛市山海关区', '130303', '2019-01-01 00:00:00', '130300', 'zhubajie@qq.com', '', '18500029043', '1', '130000', 'zhubajie521', '猪八戒', '0', '', 'zhubajie', '1004', '天蓬元帅下凡', '4', null, null);
INSERT INTO `gem_user` VALUES ('13', '山西省阳泉市矿区', '140303', '2017-01-02 00:00:00', '140300', 'q4444@33', '$2a$10$2RHRFoltZ2KuqHkrWzCRGu.F7w70WC1JG8aQOxX59Cy4u0u1PkUV2', '13381022222', '3', '140000', '2bajie521', '121212', '0', '', 'lixs', '1006', '23423WWW', '4', null, null);
INSERT INTO `gem_user` VALUES ('14', '北京市市辖区东城区', '110101', '2020-12-31 00:00:00', '110100', 'jindong@163.com', '$2a$10$DgfwOcfsNYWSCaQMLObIs.3UpdYbjwVqr5z7/5nQ2Q1rn.vd79ReS', '18500038222', '4', '110000', '2', '靳东', '0', '1', 'jindong', '1009', '当红男演员', '5', null, null);
INSERT INTO `gem_user` VALUES ('18', '北京市市辖区东城区', '110101', '2020-12-31 00:00:00', '110100', 'ww@183.com', '$2a$10$1WyEWj//I6Q0Z0DP4OBNK.VgtzwuDrQgGgdQsZNYG4SsTgY0gAQp2', '18522299999', '1', '110000', '', '王宝强', '0', '', 'wangbao', '1010', '', '5', null, null);
INSERT INTO `gem_user` VALUES ('19', '北京市县延庆县', '110229', null, '110200', '7699122@qq.com', '$2a$10$khKKTy/UvAAu09M8.AVnQOKokwgMZd20qLZLmcNgiZfKufotUV0AK', '13338320042', '1', '110000', '', '1111', '0', '', 'sunwuk1', '111', '', '4', null, null);
INSERT INTO `gem_user` VALUES ('21', '天津市县宁河县', '120221', '2020-01-02 00:00:00', '120200', 'gem1@163.com', '$2a$10$WMzYbiWtTYLnuufpZ13Oz.6poiZ.Ovk2P9XrwkLvMMw1h8lVmC31C', '13338880141', '2', '120000', '', '苏西', '1', '', 'suxi', '2002', '', '11', null, null);
INSERT INTO `gem_user` VALUES ('22', '河北省石家庄市市辖区', '130101', '2020-01-01 00:00:00', '130100', 'ajiao@qq.com', '$2a$10$Uf4cuqGrGBnUclvgSsUBeOox.vFtfBkvXKYz2zPsn/7TeTwkJH3bW', '18429340234', '1', '130000', '', '阿娇', '1', '', 'ajiao', '2003', '', '2', null, null);
INSERT INTO `gem_user` VALUES ('23', '吉林省四平市铁西区', '220302', '2020-01-08 00:00:00', '220300', 'aiqim@163.com', '$2a$10$geGwqvAzYeI70fFlBPzhSe6IntHDuficNpbEIMO6PvyrRDxmkKXn2', '13338280040', '3', '220000', '', '爱奇', '1', '', 'aiqi', '2003', '', '7', null, null);
INSERT INTO `gem_user` VALUES ('24', '河北省邢台市临城县', '130522', '2020-01-26 00:00:00', '130500', 'ls@qq.com', '$2a$10$4VCCFN2laJK8AdmTFusbr.5YHyspDOT37N8alPCLDXU5m2ZGRLJXy', '18338880022', '1', '130000', '', '刘飒', '0', '', 'liusa', '2004', '', '5', null, null);
INSERT INTO `gem_user` VALUES ('25', '山东省烟台市莱山区', '370613', null, '370600', 'niuize@qq.com', '$2a$10$/5a5yufxiJf66faZUF9ffuYyBVmVxZxQp4saIpAs76UySGQouBLmG', '15338880042', '1', '370000', '', '妞紫', '1', '', 'niuniu', '2005', '', '10', null, null);
INSERT INTO `gem_user` VALUES ('26', '河北省秦皇岛市昌黎县', null, null, null, 'sem@163.com', '$2a$10$GnFgIefqqi9uKmJRAM5V.utKr43RqVA6y1GxtHiFHnxofGM99GzXu', '13338880010', '3', null, '', '史小菲', '1', '', 'shixiaof', '2008', '', '5', null, '2020-02-23 21:34:31');
INSERT INTO `gem_user` VALUES ('27', '内蒙古自治区乌海市市辖区', '150301', '2020-01-24 00:00:00', '150300', 'lem@163.com', '$2a$10$zdvFAj69Rrau6E1HoNfYmufBBRDwaPdBkew/nsZf4a7F8AYO5R22i', '13338880031', '1', '150000', '', '李库', '1', '', 'liku', '2009', '', '13', null, null);
INSERT INTO `gem_user` VALUES ('29', '北京市县延庆县山西省吉林省', null, null, null, '1358882222@qq.com', '$2a$10$9RAnOoz/LI9Al4YpjKQGwO4z4jYiK5DWkdpFmJpQStoDhPNzzJCl2', '18200029040', '1', null, '', '刘松', '0', '', 'liusong', '2011', '', '13', null, '2020-02-23 22:08:03');
INSERT INTO `gem_user` VALUES ('31', '北京市县延庆县山西省吉林省', null, null, null, '1338345222@qq.com', '$2a$10$af4ISQ2FvQCaFEtlwOfxE.D1.uYW/seB2IG2.F/bVjbnnHsRryI6m', '18200034040', '1', null, '', '赵本山', '0', '', 'zhaobens', '2011', '', '3', null, '2020-02-23 22:23:13');

-- ----------------------------
-- Table structure for gem_user_depts
-- ----------------------------
DROP TABLE IF EXISTS `gem_user_depts`;
CREATE TABLE `gem_user_depts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` bigint(20) NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='用户部门关系表';

-- ----------------------------
-- Records of gem_user_depts
-- ----------------------------
INSERT INTO `gem_user_depts` VALUES ('16', '2', '18', null, null);
INSERT INTO `gem_user_depts` VALUES ('17', '3', '18', null, null);
INSERT INTO `gem_user_depts` VALUES ('18', '4', '18', null, null);
INSERT INTO `gem_user_depts` VALUES ('19', '6', '18', null, null);
INSERT INTO `gem_user_depts` VALUES ('20', '11', '18', null, null);
INSERT INTO `gem_user_depts` VALUES ('21', '2', '19', null, null);
INSERT INTO `gem_user_depts` VALUES ('22', '3', '19', null, null);
INSERT INTO `gem_user_depts` VALUES ('23', '1', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('24', '5', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('25', '2', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('26', '3', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('27', '4', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('28', '6', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('29', '11', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('30', '12', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('31', '7', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('32', '13', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('33', '14', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('34', '8', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('35', '15', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('36', '10', '20', null, null);
INSERT INTO `gem_user_depts` VALUES ('37', '2', '21', null, null);
INSERT INTO `gem_user_depts` VALUES ('38', '3', '21', null, null);
INSERT INTO `gem_user_depts` VALUES ('39', '4', '21', null, null);
INSERT INTO `gem_user_depts` VALUES ('40', '2', '22', null, null);
INSERT INTO `gem_user_depts` VALUES ('41', '4', '22', null, null);
INSERT INTO `gem_user_depts` VALUES ('42', '7', '23', null, null);
INSERT INTO `gem_user_depts` VALUES ('43', '14', '23', null, null);
INSERT INTO `gem_user_depts` VALUES ('44', '2', '24', null, null);
INSERT INTO `gem_user_depts` VALUES ('45', '3', '24', null, null);
INSERT INTO `gem_user_depts` VALUES ('46', '4', '24', null, null);
INSERT INTO `gem_user_depts` VALUES ('47', '8', '25', null, null);
INSERT INTO `gem_user_depts` VALUES ('48', '15', '25', null, null);

-- ----------------------------
-- Table structure for gem_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `gem_user_roles`;
CREATE TABLE `gem_user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of gem_user_roles
-- ----------------------------
INSERT INTO `gem_user_roles` VALUES ('50', '1', '24', null, null);
INSERT INTO `gem_user_roles` VALUES ('52', '1', '28', null, null);
INSERT INTO `gem_user_roles` VALUES ('56', '18', '32', null, null);
INSERT INTO `gem_user_roles` VALUES ('57', '1', '30', '2020-02-21 20:36:16', '2020-02-21 20:36:16');
INSERT INTO `gem_user_roles` VALUES ('58', '18', '30', '2020-02-21 20:36:16', '2020-02-21 20:36:16');
INSERT INTO `gem_user_roles` VALUES ('61', '1', '20', '2020-02-23 21:32:56', '2020-02-23 21:32:56');
INSERT INTO `gem_user_roles` VALUES ('62', '1', '31', '2020-02-23 21:33:18', '2020-02-23 21:33:18');
INSERT INTO `gem_user_roles` VALUES ('63', '1', '26', '2020-02-23 21:34:31', '2020-02-23 21:34:31');
INSERT INTO `gem_user_roles` VALUES ('64', '1', '29', '2020-02-23 22:08:03', '2020-02-23 22:08:03');
INSERT INTO `gem_user_roles` VALUES ('65', '1', '1', '2020-02-23 22:14:33', '2020-02-23 22:14:33');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');
