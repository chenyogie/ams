/*
Navicat MySQL Data Transfer

Source Server         : 139.155.119.186
Source Server Version : 50560
Source Host           : 139.155.119.186:3306
Source Database       : asm

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-08-26 18:56:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_borrow
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow`;
CREATE TABLE `t_borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `borrow_num` varchar(255) DEFAULT NULL COMMENT '借阅编号',
  `borrower_id` bigint(20) DEFAULT NULL COMMENT '借阅人id',
  `record_id` bigint(11) DEFAULT NULL COMMENT '借阅的档案id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '操作人员、工作人员',
  `date` datetime DEFAULT NULL COMMENT '借阅日期',
  `requiredate` datetime DEFAULT NULL COMMENT '要求归还日期',
  `status` bit(1) DEFAULT NULL COMMENT '归还状态：0未归还；1已归还',
  `notes` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_borrow
-- ----------------------------
INSERT INTO `t_borrow` VALUES ('1', 'BY-ZZB-083', '1', '1', '1', '2019-08-02 14:34:45', '2019-08-12 00:00:00', '', '借阅的备注信息');
INSERT INTO `t_borrow` VALUES ('2', 'BY-ZZB-081', '2', '2', '1', '2019-08-02 14:34:45', '2019-08-06 00:00:00', '', '借阅的备注信息');
INSERT INTO `t_borrow` VALUES ('3', 'BY-ZZB-036', '1', '2', '1', '2019-08-02 14:34:45', '2019-08-06 00:00:00', '', '???????');
INSERT INTO `t_borrow` VALUES ('4', 'BY-ZZB-025', '2', '5', '1', '2019-08-02 14:34:45', '2019-08-13 00:00:00', '', '借阅的备注信息');
INSERT INTO `t_borrow` VALUES ('5', 'BY-ZZB-524', '2', '1', '1', '2019-08-02 14:34:45', '2019-08-11 21:09:00', '', '借阅的备注信息');
INSERT INTO `t_borrow` VALUES ('6', 'BY-ZZB-152', '2', '6', '1', '2019-08-02 14:34:45', '2019-08-06 00:00:00', '', '借阅的备注信息');
INSERT INTO `t_borrow` VALUES ('7', 'BY-ZZB-112', '2', '3', '1', '2019-08-02 14:34:45', '2019-08-06 00:00:00', '', '借阅的备注信息');
INSERT INTO `t_borrow` VALUES ('8', 'BY-ZZB-001', '1', '4', '1', '2019-08-02 14:34:45', '2019-08-06 00:00:00', '\0', '借阅的备注信息');
INSERT INTO `t_borrow` VALUES ('28', 'JY-20190809-3a490c92', '20', '1', '1', '2019-08-09 22:53:41', '2019-08-26 00:00:00', '\0', '大发发');
INSERT INTO `t_borrow` VALUES ('29', 'JY-20190809-3a490c91', '21', '1', '1', '2019-08-11 15:10:29', '2019-08-14 00:00:00', '\0', '没有备注');
INSERT INTO `t_borrow` VALUES ('30', 'JY-20190811-a9df3f59', '22', '4', '1', '2019-08-11 22:05:44', '2019-08-26 00:00:00', '\0', '阿什顿发啊');
INSERT INTO `t_borrow` VALUES ('31', 'JY-20190814-1d2a8687', '23', '3', '1', '2019-08-14 13:08:45', '2019-08-20 00:00:00', '\0', '一定要还');
INSERT INTO `t_borrow` VALUES ('32', 'JY-20190814-e97d143e', '24', '1', '1', '2019-08-14 16:28:17', '2019-08-22 00:00:00', '\0', '11');

-- ----------------------------
-- Table structure for t_borrower
-- ----------------------------
DROP TABLE IF EXISTS `t_borrower`;
CREATE TABLE `t_borrower` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '借阅人id',
  `name` varchar(255) DEFAULT NULL COMMENT '借阅人姓名',
  `certificate_type` varchar(255) DEFAULT NULL COMMENT '借阅人证件类型id',
  `certificate_num` varchar(255) DEFAULT NULL COMMENT '证件编号',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_borrower
-- ----------------------------
INSERT INTO `t_borrower` VALUES ('1', '龙鹏超', '5', '510998765687263746', '18378091273');
INSERT INTO `t_borrower` VALUES ('2', '网二哈', '4', '510998765687263746', '18378098926');
INSERT INTO `t_borrower` VALUES ('20', '张为', '6', '2341123123', '1231231252');
INSERT INTO `t_borrower` VALUES ('21', '成是非', '8', '873002384728346', '17988273653');
INSERT INTO `t_borrower` VALUES ('22', '王尼玛', '7', '89829348672834782', '082-0982730');
INSERT INTO `t_borrower` VALUES ('23', '自行车', '6', '423434', '111');
INSERT INTO `t_borrower` VALUES ('24', '这种', null, '11', '11');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `deptPath` varchar(30) DEFAULT NULL,
  `superior_id` bigint(10) DEFAULT NULL,
  `principal_id` bigint(10) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('1', '工程部', '123123', '传真', '上级路径', '1', '1', '备注', null);
INSERT INTO `t_department` VALUES ('2', '经营部', '21344', '3434', '1/12', '2', '2', 'rrwre', null);
INSERT INTO `t_department` VALUES ('8', '3123', null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('9', '12323', null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('12', '1232', null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('18', '222', null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('19', '???', null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('20', '杀杀杀', null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('21', null, null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('25', null, null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('26', null, null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('27', null, null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('28', null, null, null, null, null, null, null, null);
INSERT INTO `t_department` VALUES ('29', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_destroydoc
-- ----------------------------
DROP TABLE IF EXISTS `t_destroydoc`;
CREATE TABLE `t_destroydoc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '销毁编号',
  `doc_id` varchar(255) DEFAULT NULL COMMENT '档案编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户编号',
  `destroydate` datetime DEFAULT NULL COMMENT '销毁日期',
  `dictionarydetail_id` bigint(20) DEFAULT NULL COMMENT '销毁原因',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_destroydoc
-- ----------------------------
INSERT INTO `t_destroydoc` VALUES ('1', '1', '1', '2019-08-03 11:05:50', '5', '这是一条备注');
INSERT INTO `t_destroydoc` VALUES ('2', '6', '2', '2019-08-01 11:54:00', '5', '看不爽');
INSERT INTO `t_destroydoc` VALUES ('4', '7', '2', '2019-03-28 12:00:00', '4', '悔过，无悔');
INSERT INTO `t_destroydoc` VALUES ('18', '3', '2', '2019-08-14 16:30:29', null, '');

-- ----------------------------
-- Table structure for t_dictionarydetail
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionarydetail`;
CREATE TABLE `t_dictionarydetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sncode` varchar(255) DEFAULT NULL COMMENT '编码，不可变',
  `centralVal` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称，可变',
  `parent_id` bigint(11) DEFAULT NULL,
  `types_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dictionarydetail
-- ----------------------------
INSERT INTO `t_dictionarydetail` VALUES ('1', 'damage', '-3', '已损坏', null, '1');
INSERT INTO `t_dictionarydetail` VALUES ('2', 'lose', '-4', '已丢失', null, '1');
INSERT INTO `t_dictionarydetail` VALUES ('3', 'getBack', '1', '已找回', null, '1');
INSERT INTO `t_dictionarydetail` VALUES ('4', 'overdue', null, '已废弃', null, '3');
INSERT INTO `t_dictionarydetail` VALUES ('5', 'abandon', null, '已过期', null, '3');
INSERT INTO `t_dictionarydetail` VALUES ('6', 'idcard', null, '居民身份证', null, '2');
INSERT INTO `t_dictionarydetail` VALUES ('7', 'gangaotai', null, '港澳台通行证', null, '2');
INSERT INTO `t_dictionarydetail` VALUES ('8', 'soldier', null, '军官证', null, '2');
INSERT INTO `t_dictionarydetail` VALUES ('9', 'passport', null, '护照', null, '2');
INSERT INTO `t_dictionarydetail` VALUES ('10', 'access', null, '可借阅', null, '4');
INSERT INTO `t_dictionarydetail` VALUES ('11', 'unaccess', null, '不可借阅', null, '4');
INSERT INTO `t_dictionarydetail` VALUES ('12', 'used', '0', '使用中', null, '1');
INSERT INTO `t_dictionarydetail` VALUES ('13', 'destroied', '-2', '已销毁', null, '1');
INSERT INTO `t_dictionarydetail` VALUES ('14', 'deleted', '-1', '已删除', null, '1');
INSERT INTO `t_dictionarydetail` VALUES ('19', 'expire', null, '已经到期', null, '5');
INSERT INTO `t_dictionarydetail` VALUES ('20', 'today', null, '今天到期', null, '5');
INSERT INTO `t_dictionarydetail` VALUES ('21', 'three', null, '3天内到期', null, '5');
INSERT INTO `t_dictionarydetail` VALUES ('22', 'aweek', null, '1周内到期', null, '5');
INSERT INTO `t_dictionarydetail` VALUES ('23', 'twoweek', null, '2周内到期', null, '5');
INSERT INTO `t_dictionarydetail` VALUES ('24', 'onemonth', null, '1个月内到期', null, '5');
INSERT INTO `t_dictionarydetail` VALUES ('25', 'threemonth', null, '3个月内到期', null, '5');
INSERT INTO `t_dictionarydetail` VALUES ('26', 'halfayear', null, '半年内到期', null, '5');
INSERT INTO `t_dictionarydetail` VALUES ('27', 'ayear', null, '一年内到期', null, '5');

-- ----------------------------
-- Table structure for t_dictionarytype
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionarytype`;
CREATE TABLE `t_dictionarytype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sncode` varchar(255) DEFAULT NULL COMMENT '分类编号(不变)',
  `name` varchar(255) DEFAULT NULL COMMENT '分类名称（可变）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dictionarytype
-- ----------------------------
INSERT INTO `t_dictionarytype` VALUES ('1', 'docstatetype', '档案状态类型');
INSERT INTO `t_dictionarytype` VALUES ('2', 'certificatetype', '证件类型');
INSERT INTO `t_dictionarytype` VALUES ('3', 'destroyreason', '销毁原因');
INSERT INTO `t_dictionarytype` VALUES ('4', 'borrowstatus', '借阅状态');
INSERT INTO `t_dictionarytype` VALUES ('5', 'expiretype', '到期时间管理');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `sn` bigint(10) DEFAULT NULL COMMENT '菜单编号',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(50) DEFAULT NULL COMMENT '资源路径,如：/role/index',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单编号',
  `status` int(10) DEFAULT NULL COMMENT '使用状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '1', '系统管理', '/2', '/2', null, '0');
INSERT INTO `t_menu` VALUES ('2', '2', '字典管理', null, null, '1', '0');
INSERT INTO `t_menu` VALUES ('3', '3', '部门管理', null, null, '1', null);
INSERT INTO `t_menu` VALUES ('4', '4', '用户管理', null, null, '1', '0');
INSERT INTO `t_menu` VALUES ('5', '5', '角色管理', null, null, '1', '0');
INSERT INTO `t_menu` VALUES ('6', '6', '权限管理', null, null, '1', '0');
INSERT INTO `t_menu` VALUES ('7', '7', '系统菜单管理', null, null, '1', '0');
INSERT INTO `t_menu` VALUES ('8', '8', '系统资源管理', null, null, '1', '0');
INSERT INTO `t_menu` VALUES ('9', '9', '修改密码', null, null, '1', '0');
INSERT INTO `t_menu` VALUES ('10', '10', '系统配置', null, null, '1', '0');
INSERT INTO `t_menu` VALUES ('11', '11', '档案管理', null, null, null, null);
INSERT INTO `t_menu` VALUES ('12', '12', '存档点管理', null, null, '11', '-1');
INSERT INTO `t_menu` VALUES ('13', '13', '分类管理', null, null, '11', null);
INSERT INTO `t_menu` VALUES ('14', '14', '档案录入', null, null, '11', '-1');
INSERT INTO `t_menu` VALUES ('15', '15', '档案查询', null, null, '11', null);
INSERT INTO `t_menu` VALUES ('16', '16', '档案借阅', null, null, '11', '-1');
INSERT INTO `t_menu` VALUES ('17', '17', '档案归还', null, null, '11', null);
INSERT INTO `t_menu` VALUES ('18', '18', '借阅归还查询', null, null, '11', null);
INSERT INTO `t_menu` VALUES ('19', '19', '档案导出', null, null, '11', null);
INSERT INTO `t_menu` VALUES ('20', '20', '档案附件上传', null, null, '11', null);
INSERT INTO `t_menu` VALUES ('21', '21', '档案鉴定', null, null, null, null);
INSERT INTO `t_menu` VALUES ('22', '22', '销毁登记', '/destroyDoc', null, '21', null);
INSERT INTO `t_menu` VALUES ('23', '23', '损坏丢失', '/problematicDoc', null, '21', null);
INSERT INTO `t_menu` VALUES ('25', '25', '数据维护', null, null, null, null);
INSERT INTO `t_menu` VALUES ('26', '26', '数据备份', null, null, '25', null);
INSERT INTO `t_menu` VALUES ('27', '27', '数据恢复', null, null, '25', null);
INSERT INTO `t_menu` VALUES ('28', '28', '系统日志', null, null, '25', null);
INSERT INTO `t_menu` VALUES ('29', '29', '档案到期管理', null, null, '11', null);
INSERT INTO `t_menu` VALUES ('30', '39', '销毁查询', '/destroyDoc', null, '21', null);
INSERT INTO `t_menu` VALUES ('31', '31', '丢失找回', '/problematicDoc', null, '21', null);
INSERT INTO `t_menu` VALUES ('32', '32', '损坏丢失查询', null, null, '21', null);
INSERT INTO `t_menu` VALUES ('38', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('39', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('40', null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('41', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `sn` varchar(50) DEFAULT NULL COMMENT '权限编号',
  `identifying` bigint(10) DEFAULT NULL COMMENT '权限标识--用于标识是什么权限',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径,如：/user/save或/user/index',
  `status` int(10) DEFAULT NULL COMMENT '使用状态:0--使用，-1---禁用',
  `parent_id` bigint(10) DEFAULT NULL COMMENT '父节点id',
  `menu_id` bigint(10) DEFAULT NULL COMMENT '权限对应的菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'archives:management', '1', '档案管理', null, '-1', null, '1');
INSERT INTO `t_permission` VALUES ('2', null, '2', '存档点管理', null, '0', '1', '2');
INSERT INTO `t_permission` VALUES ('3', null, '3', '档案分类管理', null, '0', '1', '3');
INSERT INTO `t_permission` VALUES ('4', null, '4', '档案录入', null, '0', '1', '4');
INSERT INTO `t_permission` VALUES ('6', null, '5', '档案查询', null, '-1', '1', '5');
INSERT INTO `t_permission` VALUES ('7', null, '6', '借阅归还查询', null, null, '1', '6');
INSERT INTO `t_permission` VALUES ('8', null, '7', '档案导出', null, null, '1', '7');
INSERT INTO `t_permission` VALUES ('9', null, '8', '扫描件上传', null, null, '1', '8');
INSERT INTO `t_permission` VALUES ('10', null, '9', '档案到期管理', null, null, '1', '9');
INSERT INTO `t_permission` VALUES ('11', null, '10', '档案借阅', null, null, '1', '10');
INSERT INTO `t_permission` VALUES ('12', null, '11', '档案归还', null, null, '1', '11');
INSERT INTO `t_permission` VALUES ('13', 'archives:authenticate', '12', '档案鉴定', null, null, null, null);
INSERT INTO `t_permission` VALUES ('14', null, '13', '销毁登记', '/destroyDoc', null, '13', null);
INSERT INTO `t_permission` VALUES ('15', null, '14', '销毁查询', '/destroyDoc', null, '13', null);
INSERT INTO `t_permission` VALUES ('16', null, '15', '损坏丢失登记', '/problematicDoc', null, '13', null);
INSERT INTO `t_permission` VALUES ('17', null, '16', '损坏丢失查询', '/problematicDoc', null, '13', null);
INSERT INTO `t_permission` VALUES ('18', null, '17', '丢失找回登记', '/problematicDoc', null, '13', null);
INSERT INTO `t_permission` VALUES ('19', 'data:maintain', '18', '数据维护', null, null, null, null);
INSERT INTO `t_permission` VALUES ('20', null, '19', '数据备份', null, null, '19', null);
INSERT INTO `t_permission` VALUES ('21', null, '20', '数据恢复', null, null, '19', null);
INSERT INTO `t_permission` VALUES ('22', 'system:management', '21', '系统管理', null, null, null, null);
INSERT INTO `t_permission` VALUES ('23', null, '22', '系统配置', null, null, '21', null);
INSERT INTO `t_permission` VALUES ('24', null, '23', '数据字典管理', null, null, '21', null);
INSERT INTO `t_permission` VALUES ('25', null, '24', '角色管理', null, null, '21', null);
INSERT INTO `t_permission` VALUES ('26', null, '25', '员工用户管理', null, null, '21', null);
INSERT INTO `t_permission` VALUES ('27', null, '27', '部门管理', null, null, '21', null);
INSERT INTO `t_permission` VALUES ('28', null, '28', '修改登录密码', null, null, '21', null);
INSERT INTO `t_permission` VALUES ('29', null, '29', '操作日志管理', null, null, '22', null);
INSERT INTO `t_permission` VALUES ('30', null, null, null, null, null, null, null);
INSERT INTO `t_permission` VALUES ('31', null, null, null, null, null, null, null);
INSERT INTO `t_permission` VALUES ('32', null, null, null, null, null, null, null);
INSERT INTO `t_permission` VALUES ('33', null, null, null, null, null, null, null);
INSERT INTO `t_permission` VALUES ('34', null, null, null, null, null, null, null);
INSERT INTO `t_permission` VALUES ('35', null, null, null, null, null, null, null);
INSERT INTO `t_permission` VALUES ('36', null, null, null, null, null, null, null);
INSERT INTO `t_permission` VALUES ('37', null, null, null, null, null, null, null);
INSERT INTO `t_permission` VALUES ('38', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_problematicdoc
-- ----------------------------
DROP TABLE IF EXISTS `t_problematicdoc`;
CREATE TABLE `t_problematicdoc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '档案编号',
  `dictionarydetail_id` bigint(20) DEFAULT NULL COMMENT '类型',
  `user_id` bigint(20) DEFAULT NULL COMMENT '操作用户',
  `accidentdate` datetime DEFAULT NULL COMMENT '事故日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_problematicdoc
-- ----------------------------
INSERT INTO `t_problematicdoc` VALUES ('1', '2', '1', '1', '2019-08-03 11:44:59', '不找了，找不到了');
INSERT INTO `t_problematicdoc` VALUES ('2', '4', '3', '1', '2019-08-14 16:30:16', '已找回');
INSERT INTO `t_problematicdoc` VALUES ('3', '3', '3', '1', '2019-07-08 11:46:36', '忘记放哪里了');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `site_id` bigint(20) DEFAULT NULL COMMENT '档案地点',
  `num` varchar(255) DEFAULT NULL COMMENT '档案编号',
  `headline` varchar(255) DEFAULT NULL COMMENT '档案标题',
  `createuser_id` bigint(20) DEFAULT NULL COMMENT '建档用户',
  `createdate` datetime DEFAULT NULL COMMENT '创建日期',
  `updateuser_id` bigint(20) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL COMMENT '修改日期',
  `borrowstatus` bit(1) DEFAULT NULL COMMENT '借阅状态：0，不可借阅，1：可借阅。',
  `expirationdate` datetime DEFAULT NULL COMMENT '过期时间',
  `expirationnotice` bit(1) DEFAULT NULL COMMENT '是否提醒',
  `advancedate` datetime DEFAULT NULL COMMENT '提前通知天数',
  `content` varchar(255) DEFAULT NULL COMMENT '档案内容',
  `introduce` varchar(255) DEFAULT NULL COMMENT '简介',
  `oftenuse` bit(1) DEFAULT NULL COMMENT '是否常用',
  `f_num` varchar(255) DEFAULT NULL COMMENT '文件编号',
  `c_department` bigint(20) DEFAULT NULL COMMENT '来文部门',
  `g_department` bigint(20) DEFAULT NULL COMMENT '发文部门',
  `ratify` datetime DEFAULT NULL COMMENT '批准时间',
  `authenticate` datetime DEFAULT NULL COMMENT '鉴定时间',
  `product_num` varchar(255) DEFAULT NULL COMMENT '产品批号',
  `raw_num` varchar(255) DEFAULT NULL COMMENT '原料批号',
  `f_count` int(11) DEFAULT NULL COMMENT '档案数量',
  `f_page` int(11) DEFAULT NULL COMMENT '页数',
  `patent` varchar(255) DEFAULT NULL COMMENT '专利号',
  `check_user_id` bigint(20) DEFAULT NULL COMMENT '年度检验人员',
  `f_status` int(11) DEFAULT NULL COMMENT '档案状态：0代表使用中，-1代表已删除，-2已销毁，-3损坏，-4丢失',
  `type_id` bigint(20) DEFAULT NULL COMMENT '档案类型',
  `ogm_user` bigint(20) DEFAULT NULL COMMENT '发文人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('1', null, 'DB-NH-08136', '关于召开员工培训会的通知1', null, '2019-08-05 18:37:30', null, '2019-08-06 18:37:44', '\0', '2019-08-06 18:39:25', '\0', null, null, null, '\0', null, null, null, null, null, null, null, null, null, null, null, '-2', null, null);
INSERT INTO `t_record` VALUES ('3', null, 'DB-NH-08238', '关于召开员2培训会的通知1', null, '2019-08-05 19:41:03', null, null, '\0', '2019-08-29 19:46:58', '\0', '2019-08-05 19:41:03', null, null, '\0', null, null, null, null, null, null, null, null, null, null, null, '-2', null, null);
INSERT INTO `t_record` VALUES ('4', '1', 'DB-NH-08239', '关于召开员工培训会的通知2', null, '2019-08-06 22:20:05', null, null, '', '2019-08-21 19:47:02', '\0', null, null, null, '\0', null, null, null, null, null, null, null, null, null, null, null, '0', '2', null);
INSERT INTO `t_record` VALUES ('5', '1', 'DB-NH-08233', '关于召开员工培2训会的通知1', null, '2019-09-04 22:20:10', null, null, '', '2019-08-18 19:47:07', '\0', null, null, null, '\0', null, null, null, null, null, null, null, null, null, null, null, '-1', '3', null);
INSERT INTO `t_record` VALUES ('6', null, 'DB-NH-08232', '关于召开员工培训会的通知2', null, '2019-08-18 22:20:13', null, null, '', '2019-10-24 19:47:10', '\0', null, null, null, '\0', null, null, null, null, null, null, null, null, null, null, null, '-2', null, null);
INSERT INTO `t_record` VALUES ('7', '6', 'DB-NH-08241', '关于召开员工培训会的通知2', null, null, null, null, null, null, null, '1970-01-01 08:00:00', '123', '123', '', '', null, null, null, null, '', '', null, null, '', null, '-2', '1', null);
INSERT INTO `t_record` VALUES ('9', '1', '十大', '关于召开员工培训会的通知2', null, null, null, null, null, null, null, '1970-01-01 08:00:00', '大大撒', '', null, '', null, null, null, null, '', '', null, null, '', null, null, '4', null);
INSERT INTO `t_record` VALUES ('16', '1', '1', '1', null, null, null, null, null, null, null, '1970-01-01 08:00:00', '11', '11', null, '', null, null, null, null, '', '', null, null, '', null, null, null, null);
INSERT INTO `t_record` VALUES ('17', '1', '11', '11', null, null, null, null, null, null, null, '1970-01-01 08:00:00', '11', '', null, '', null, null, null, null, '', '', null, null, '', null, null, null, null);

-- ----------------------------
-- Table structure for t_record_type
-- ----------------------------
DROP TABLE IF EXISTS `t_record_type`;
CREATE TABLE `t_record_type` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `sncode` varchar(255) DEFAULT NULL COMMENT '档案编号',
  `name` varchar(255) DEFAULT NULL COMMENT '档案类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record_type
-- ----------------------------
INSERT INTO `t_record_type` VALUES ('1', 'rensh', '人事档案');
INSERT INTO `t_record_type` VALUES ('2', 'shangfangrrr', '上访专卷');
INSERT INTO `t_record_type` VALUES ('3', 'fupin', '扶贫专卷');
INSERT INTO `t_record_type` VALUES ('4', 'xingshi', '刑事专卷');

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `sn` bigint(10) DEFAULT NULL COMMENT '资源编号',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `status` int(10) DEFAULT NULL COMMENT '资源状态 0/-1',
  `descs` varchar(255) DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_revert
-- ----------------------------
DROP TABLE IF EXISTS `t_revert`;
CREATE TABLE `t_revert` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `revert_num` varchar(255) DEFAULT NULL COMMENT '归还记录编号',
  `borrow_id` bigint(20) DEFAULT NULL COMMENT '借阅编号',
  `user_id` int(11) DEFAULT NULL COMMENT '归还操作的工作人员',
  `revertdate` datetime DEFAULT NULL COMMENT '归还时间',
  `note` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_revert
-- ----------------------------
INSERT INTO `t_revert` VALUES ('1', 'GH-20190811-ec81232c', '1', '1', '2019-08-07 23:49:19', '阿斯顿发送到');
INSERT INTO `t_revert` VALUES ('2', 'GH-20190811-ec8c732c', '1', '1', null, '阿什顿发');
INSERT INTO `t_revert` VALUES ('3', 'GH-20190811-311af4a4', '2', '1', null, '就换个划分决胜巅峰');
INSERT INTO `t_revert` VALUES ('4', 'GH-20190811-f6259e81', '2', '1', null, '家说的很对发生');
INSERT INTO `t_revert` VALUES ('5', 'GH-20190811-adf5147c', '28', '1', null, 'ASDFASDF ');
INSERT INTO `t_revert` VALUES ('6', 'GH-20190811-fb8539ec', '7', '1', null, '123123123123');
INSERT INTO `t_revert` VALUES ('7', 'GH-20190811-7793638e', '4', '1', null, '阿什顿发斯蒂芬');
INSERT INTO `t_revert` VALUES ('8', 'GH-20190811-2835f4cb', '8', '1', null, '阿什顿发斯蒂芬');
INSERT INTO `t_revert` VALUES ('9', 'GH-20190811-c6624519', '1', '1', null, '大发的算法');
INSERT INTO `t_revert` VALUES ('10', 'GH-20190811-ad2b8f48', '6', '1', null, '阿什顿发');
INSERT INTO `t_revert` VALUES ('11', 'GH-20190814-f3da9261', '3', '1', null, '????');
INSERT INTO `t_revert` VALUES ('12', 'GH-20190814-c58527b7', '3', '1', null, '');
INSERT INTO `t_revert` VALUES ('13', 'GH-20190814-91565e4e', '6', '1', null, '已归还');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `sn` varchar(50) DEFAULT NULL COMMENT '角色编号',
  `identifying` bigint(10) DEFAULT NULL COMMENT '角色标识',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `status` int(2) DEFAULT NULL COMMENT '使用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'sysAdmin', '1', '系统管理员', '-1');
INSERT INTO `t_role` VALUES ('2', 'filingClerk', '2', '档案管理员', '-1');
INSERT INTO `t_role` VALUES ('3', 'dataMaintainer', '3', '数据维护员', '-1');
INSERT INTO `t_role` VALUES ('4', 'staff', '4', '员工', '-1');
INSERT INTO `t_role` VALUES ('5', 'test', '5', 'tt', '0');
INSERT INTO `t_role` VALUES ('6', 'test', '5', 'tt', '0');
INSERT INTO `t_role` VALUES ('7', 'test', '5', 'tt', '0');
INSERT INTO `t_role` VALUES ('8', 'test', '5', 'tt', '0');
INSERT INTO `t_role` VALUES ('9', '123', '6', '123', '0');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_id` bigint(10) DEFAULT '0',
  `menu_id` bigint(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '1');
INSERT INTO `t_role_menu` VALUES ('2', '2');
INSERT INTO `t_role_menu` VALUES ('1', '2');
INSERT INTO `t_role_menu` VALUES ('1', '3');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `role_identifying` bigint(10) DEFAULT NULL,
  `permission_identifying` bigint(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '1');
INSERT INTO `t_role_permission` VALUES ('2', '18');
INSERT INTO `t_role_permission` VALUES ('1', '2');
INSERT INTO `t_role_permission` VALUES ('1', '3');
INSERT INTO `t_role_permission` VALUES ('6', '1');
INSERT INTO `t_role_permission` VALUES ('6', '2');

-- ----------------------------
-- Table structure for t_site
-- ----------------------------
DROP TABLE IF EXISTS `t_site`;
CREATE TABLE `t_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL COMMENT '档案存放地点',
  `name` varchar(255) DEFAULT NULL COMMENT '存档点名称',
  `linkman` varchar(255) DEFAULT NULL COMMENT '联系人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_site
-- ----------------------------
INSERT INTO `t_site` VALUES ('1', '四川省成都市档案局16号档案室5架11排', '流动摊贩', '陈勇jie', '22');
INSERT INTO `t_site` VALUES ('2', '四川省成都市档案局15号档案室', '小商小贩们', '余航', '120');
INSERT INTO `t_site` VALUES ('4', '说', 'ggg', '余航', '大王喊我来吃饭');
INSERT INTO `t_site` VALUES ('6', '大大', '大', '大大', '登山涉水事实上');

-- ----------------------------
-- Table structure for t_sysConfig
-- ----------------------------
DROP TABLE IF EXISTS `t_sysConfig`;
CREATE TABLE `t_sysConfig` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `sysname` varchar(10) DEFAULT NULL,
  `companyName` varchar(10) DEFAULT NULL,
  `companyPhone` varchar(20) DEFAULT NULL,
  `companySite` varchar(20) DEFAULT NULL,
  `companyFax` varchar(20) DEFAULT NULL,
  `companyURL` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sysConfig
-- ----------------------------
INSERT INTO `t_sysConfig` VALUES ('1', '系统名称', '公司名称', '12321424', '四川成都', '123232', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL COMMENT '登陆名',
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL COMMENT '真是姓名',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `dept_id` bigint(10) DEFAULT NULL COMMENT '所属部门',
  `hiredate` datetime DEFAULT NULL COMMENT '入职时间',
  `phone` varchar(20) DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL COMMENT '公司地址',
  `sex` bit(1) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `remark` varchar(20) DEFAULT NULL,
  `status` int(10) DEFAULT NULL COMMENT '状态',
  `role_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '航仔', '9527', '搜素', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('2', '四夕', null, '士大夫', '213231', null, '2019-08-07 11:30:59', '1231231232', '本科', null, null, '2019-08-16 11:31:56', null, null, null);
INSERT INTO `t_user` VALUES ('3', 'zcb', null, null, null, '0', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('5', 'test', 'dc4778f2ba28ec4f49080b5085b1206f', null, null, null, null, null, null, null, null, null, null, null, '1');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` bigint(10) DEFAULT NULL,
  `role_id` bigint(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
INSERT INTO `t_user_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for t_wxuser
-- ----------------------------
DROP TABLE IF EXISTS `t_wxuser`;
CREATE TABLE `t_wxuser` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(150) DEFAULT NULL,
  `sex` bit(10) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `headimgurl` varchar(200) DEFAULT NULL,
  `privilega` varchar(200) DEFAULT NULL,
  `unionid` bigint(20) DEFAULT NULL,
  `user_id` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wxuser
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'dasda');
INSERT INTO `user` VALUES ('2', '1mc');
