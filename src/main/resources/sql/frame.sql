use e8admin;

-- DROP TABLE IF EXISTS `t_user`;
--
-- create TABLE t_user(
--  `id` char(32) NOT NULL ,
--   `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `createTime` datetime NULL DEFAULT NULL,
--   `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `enabled` bigint(20) NULL DEFAULT NULL,
--   `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `lastPasswordResetTime` datetime NULL DEFAULT NULL,
--   PRIMARY KEY (`id`) USING BTREE,
--   UNIQUE INDEX `UK_kpubos9gc2cvtkb0thktkbkes`(`email`) USING BTREE,
--   UNIQUE INDEX `username`(`username`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
--
-- -- ----------------------------
-- -- Records of user
-- -- ----------------------------
-- INSERT INTO `t_user` VALUES ('1', 'https://i.loli.net/2018/12/31/5c297270b20e2.jpg', '2018-08-23 09:11:56', 'zhengjie@tom.com', 1, '14e1b600b1fd579f47433b88e8d85291', 'admin', '2018-11-23 10:12:36');
-- INSERT INTO `t_user` VALUES ('3', 'https://i.loli.net/2018/12/30/5c2871d6aa101.jpg', '2018-12-27 20:05:26', 'test@qq.com', 1, '14e1b600b1fd579f47433b88e8d85291', 'test', NULL);
--
-- DROP TABLE IF EXISTS `t_role`;
-- CREATE TABLE `t_role`  (
--   `id`  char(32) NOT NULL ,
--   `createTime` datetime NULL DEFAULT NULL,
--   `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
--   `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
--
-- -- ----------------------------
-- -- Records of role
-- -- ----------------------------
-- INSERT INTO `t_role` VALUES ('1', '2018-11-23 11:04:37', '超级管理员', '超级管理员');
-- INSERT INTO `t_role` VALUES ('2', '2018-11-23 13:09:06', '普通用户', '普通用户');
--
-- -- ----------------------------
-- -- Table structure for permission
-- -- ----------------------------
-- DROP TABLE IF EXISTS `t_permission`;
-- CREATE TABLE `t_permission`  (
--   `id` char(32) NOT NULL ,
--   `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `createTime` datetime NULL DEFAULT NULL,
--   `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `pid` char(32) NOT NULL,
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
--
-- -- ----------------------------
-- -- Records of permission
-- -- ----------------------------
-- INSERT INTO `t_permission` VALUES ('1', '超级管理员', '2018-12-03 12:27:48', 'ADMIN', '0');
-- INSERT INTO `t_permission` VALUES ('2', '用户管理', '2018-12-03 12:28:19', 'USER_ALL', '0');
-- INSERT INTO `t_permission` VALUES ('3', '用户查询', '2018-12-03 12:31:35', 'USER_SELECT', '2');
-- INSERT INTO `t_permission` VALUES ('4', '用户创建', '2018-12-03 12:31:35', 'USER_CREATE', '2');
-- INSERT INTO `t_permission` VALUES ('5', '用户编辑', '2018-12-03 12:31:35', 'USER_EDIT', '2');
-- INSERT INTO `t_permission` VALUES ('6', '用户删除', '2018-12-03 12:31:35', 'USER_DELETE', '2');
-- INSERT INTO `t_permission` VALUES ('7', '角色管理', '2018-12-03 12:28:19', 'ROLES_ALL', '0');
-- INSERT INTO `t_permission` VALUES ('8', '角色查询', '2018-12-03 12:31:35', 'ROLES_SELECT', '7');
-- INSERT INTO `t_permission` VALUES ('10', '角色创建', '2018-12-09 20:10:16', 'ROLES_CREATE', '7');
-- INSERT INTO `t_permission` VALUES ('11', '角色编辑', '2018-12-09 20:10:42', 'ROLES_EDIT', '7');
-- INSERT INTO `t_permission` VALUES ('12', '角色删除', '2018-12-09 20:11:07', 'ROLES_DELETE', '7');
-- INSERT INTO `t_permission` VALUES ('13', '权限管理', '2018-12-09 20:11:37', 'PERMISSION_ALL', '0');
-- INSERT INTO `t_permission` VALUES ('14', '权限查询', '2018-12-09 20:11:55', 'PERMISSION_SELECT', '13');
-- INSERT INTO `t_permission` VALUES ('15', '权限创建', '2018-12-09 20:14:10', 'PERMISSION_CREATE', '13');
-- INSERT INTO `t_permission` VALUES ('16', '权限编辑', '2018-12-09 20:15:44', 'PERMISSION_EDIT', '13');
-- INSERT INTO `t_permission` VALUES ('29', '菜单管理', '2018-12-28 17:34:31', 'MENU_ALL', '0');
-- INSERT INTO `t_permission` VALUES ('30', '菜单查询', '2018-12-28 17:34:41', 'MENU_SELECT',' 29');
-- INSERT INTO `t_permission` VALUES ('31', '菜单创建', '2018-12-28 17:34:52', 'MENU_CREATE', '29');
-- INSERT INTO `t_permission` VALUES ('32', '菜单编辑', '2018-12-28 17:35:20', 'MENU_EDIT', '29');
-- INSERT INTO `t_permission` VALUES ('33', '菜单删除', '2018-12-28 17:35:29', 'MENU_DELETE', '29');
--
--
-- -- ----------------------------
-- -- Table structure for menu
-- -- ----------------------------
-- DROP TABLE IF EXISTS `t_menu`;
-- CREATE TABLE `t_menu`  (
--   `id` char(32) NOT NULL  ,
--   `createTime` datetime NULL DEFAULT NULL,
--   `iFrame` bit(1) NULL DEFAULT NULL,
--   `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `pid` char(32) NOT NULL,
--   `sort` bigint(20) NOT NULL,
--   `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
--
-- -- ----------------------------
-- -- Records of menu
-- -- ----------------------------
-- INSERT INTO `t_menu` VALUES ('1', '2018-12-18 15:11:29', 0, '系统管理', NULL, '0', 1, 'system', 'system');
-- INSERT INTO `t_menu` VALUES ('2', '2018-12-18 15:14:44', 0, '用户管理', 'system/user/index', '1', 2, 'peoples', 'user');
-- INSERT INTO `t_menu` VALUES ('3', '2018-12-18 15:16:07', 0, '角色管理', 'system/role/index', '1', 3, 'role', 'role');
-- INSERT INTO `t_menu` VALUES ('4', '2018-12-18 15:16:45', 0, '权限管理', 'system/permission/index', '1', 4, 'permission', 'permission');
-- INSERT INTO `t_menu` VALUES ('5', '2018-12-18 15:17:28', 0, '菜单管理', 'system/menu/index', '1', 5, 'menu', 'menu');
-- INSERT INTO `t_menu` VALUES ('7', '2018-12-18 15:18:26', 0, '系统日志', 'monitor/log/index', '6', 11, 'log', 'logs');
-- INSERT INTO `t_menu` VALUES ('21', '2019-01-04 16:22:03', 0, '多级菜单', '', '0', 900, 'menu', 'nested');
-- INSERT INTO `t_menu` VALUES ('22','2019-01-04 16:23:29', 0, '二级菜单1', 'nested/menu1/index', '21', 999, 'menu', 'menu1');
-- INSERT INTO `t_menu` VALUES ('23', '2019-01-04 16:23:57', 0, '二级菜单2', 'nested/menu2/index', '21', 999, 'menu', 'menu2');
-- INSERT INTO `t_menu` VALUES ('24', '2019-01-04 16:24:48', 0, '三级菜单1', 'nested/menu1/menu1-1', '22', 999, 'menu', 'menu1-1');
-- INSERT INTO `t_menu` VALUES ('27', '2019-01-07 17:27:32', 0, '三级菜单2', 'nested/menu1/menu1-2', '22', 999, 'menu', 'menu1-2');
--
-- ----------------------------
-- Table structure for menus_roles
-- ----------------------------
-- DROP TABLE IF EXISTS `t_menus_roles`;
-- CREATE TABLE `t_menus_roles`  (
--   `menu_id` char(32) NOT NULL,
--   `role_id` char(32) NOT NULL,
--   PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
--   INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_id`) USING BTREE,
--   CONSTRAINT `FKcngg2qadojhi3a651a5adkvbq` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
--   CONSTRAINT `FKq1knxf8ykt26we8k331naabjx` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
-- ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
--
-- INSERT INTO `t_menus_roles` VALUES ('1','1');
-- INSERT INTO `t_menus_roles` VALUES('2','1');
-- INSERT INTO `t_menus_roles` VALUES('3','1');INSERT INTO `t_menus_roles` VALUES
-- ('4','1');INSERT INTO `t_menus_roles` VALUES
-- ('5','1');INSERT INTO `t_menus_roles` VALUES
-- ('6','1');INSERT INTO `t_menus_roles` VALUES
-- ('7','1');INSERT INTO `t_menus_roles` VALUES
-- ('1','2');
-- INSERT INTO `t_menus_roles` VALUES('2','2');INSERT INTO `t_menus_roles` VALUES
-- ('3','2');INSERT INTO `t_menus_roles` VALUES
-- ('4','2');INSERT INTO `t_menus_roles` VALUES
-- ('5','2');
-- INSERT INTO `t_menus_roles` VALUES('6','2');


-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `t_roles_permissions`;
CREATE TABLE `t_roles_permissions`  (
  `role_id` char(32) NOT NULL,
  `permission_id` char(32) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FKboeuhl31go7wer3bpy6so7exi`(`permission_id`) USING BTREE,
  CONSTRAINT `FK4hrolwj4ned5i7qe8kyiaak6m` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKboeuhl31go7wer3bpy6so7exi` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

INSERT INTO `t_roles_permissions` VALUES ('1','1'),('2','3'),('2','8'),('2','14'),('2','29');

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_users_roles`;
CREATE TABLE `t_users_roles`  (
  `user_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id`) USING BTREE,
  CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `t_users_roles` VALUES ('1', '1');
INSERT INTO `t_users_roles` VALUES ('3', '2');

DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` char(32) NOT NULL ,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `exceptionDetail` text,
  `logType` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `params` text,
  `requestIp` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5177 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

