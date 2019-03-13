-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: e8admin
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` char(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `exception_detail` text,
  `log_type` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `params` text,
  `request_ip` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menu` (
  `id` char(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `iframe` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `pid` char(32) NOT NULL,
  `sort` bigint(20) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` VALUES ('23b9c73f4b8849455e8dc5e0634df','2019-01-04 16:23:57',_binary '\0','二级菜单2','nested/menu2/index','21',999,'menu','menu2'),('2wes3f4b8849455e8dc5e0634df7ewsa','2018-12-18 15:11:29',_binary '\0','系统管理',NULL,'0',1,'system','system'),('43233fyret5e8dc5e0634df722123','2019-01-04 16:23:29',_binary '\0','二级菜单1','nested/menu1/index','b9c73f4b882340634df7af49',999,'menu','menu1'),('54343f4b8849455e8dc5e0634df7e7yt','2018-12-18 15:16:07',_binary '\0','角色管理','system/role/index','2wes3f4b8849455e8dc5e0634df7ewsa',3,'role','role'),('62wes3f4b8849455e8dc5e0634df7ew6','2018-12-18 15:17:48',_binary '\0','系统监控',NULL,'0',10,'monitor','monitor'),('72wes3f4b8849455e8dc5e0634df7ew7','2018-12-18 15:18:26',_binary '\0','系统日志','monitor/log/index','62wes3f4b8849455e8dc5e0634df7ew6',11,'log','logs'),('73f4b8849455e8dc5e0634df7af49','2019-01-04 16:24:48',_binary '\0','三级菜单1','nested/menu1/menu1-1','43233fyret5e8dc5e0634df722123',999,'menu','menu1-1'),('b9c73f4b882340634df7af49','2019-01-04 16:22:03',_binary '\0','多级菜单','','0',900,'menu','nested'),('b9c73f4b8849455e8dc5e063af49','2019-01-07 17:27:32',_binary '\0','三级菜单2','nested/menu1/menu1-2','43233fyret5e8dc5e0634df722123',999,'menu','menu1-2'),('e32a3f4b8849455e8dc5e0634df32ewa','2018-12-18 15:14:44',_binary '\0','用户管理','system/user/index','2wes3f4b8849455e8dc5e0634df7ewsa',2,'peoples','user'),('g43534b8849455e8dc5e0634df7987u','2018-12-18 15:17:28',_binary '\0','菜单管理','system/menu/index','2wes3f4b8849455e8dc5e0634df7ewsa',5,'menu','menu'),('hew33f4b8849455e8dc5e0634df7654r','2018-12-18 15:16:45',_binary '\0','权限管理','system/permission/index','2wes3f4b8849455e8dc5e0634df7ewsa',4,'permission','permission');
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menus_roles`
--

DROP TABLE IF EXISTS `t_menus_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menus_roles` (
  `menu_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
  KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE,
  CONSTRAINT `FKcngg2qadojhi3a651a5adkvbq` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKq1knxf8ykt26we8k331naabjx` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menus_roles`
--

LOCK TABLES `t_menus_roles` WRITE;
/*!40000 ALTER TABLE `t_menus_roles` DISABLE KEYS */;
INSERT INTO `t_menus_roles` VALUES ('2wes3f4b8849455e8dc5e0634df7ewsa','b9c73f4b8849455e8dc5e0634df7af49'),('43233fyret5e8dc5e0634df722123','b9c73f4b8849455e8dc5e0634df7af49'),('54343f4b8849455e8dc5e0634df7e7yt','b9c73f4b8849455e8dc5e0634df7af49'),('62wes3f4b8849455e8dc5e0634df7ew6','b9c73f4b8849455e8dc5e0634df7af49'),('72wes3f4b8849455e8dc5e0634df7ew7','b9c73f4b8849455e8dc5e0634df7af49'),('73f4b8849455e8dc5e0634df7af49','b9c73f4b8849455e8dc5e0634df7af49'),('b9c73f4b882340634df7af49','b9c73f4b8849455e8dc5e0634df7af49'),('b9c73f4b8849455e8dc5e063af49','b9c73f4b8849455e8dc5e0634df7af49'),('e32a3f4b8849455e8dc5e0634df32ewa','b9c73f4b8849455e8dc5e0634df7af49'),('g43534b8849455e8dc5e0634df7987u','b9c73f4b8849455e8dc5e0634df7af49'),('hew33f4b8849455e8dc5e0634df7654r','b9c73f4b8849455e8dc5e0634df7af49');
/*!40000 ALTER TABLE `t_menus_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `id` char(32) NOT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pid` char(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES ('b9c73f4b8849455e8dc5e0634df7af49','超级管理员','2018-12-03 12:27:48','ADMIN','0');
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` char(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES ('b9c73f4b8849455e8dc5e0634df7af49','2018-11-23 11:04:37','超级管理员','超级管理员');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_roles_permissions`
--

DROP TABLE IF EXISTS `t_roles_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_roles_permissions` (
  `role_id` char(32) NOT NULL,
  `permission_id` char(32) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`) USING BTREE,
  KEY `FKboeuhl31go7wer3bpy6so7exi` (`permission_id`) USING BTREE,
  CONSTRAINT `FK4hrolwj4ned5i7qe8kyiaak6m` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKboeuhl31go7wer3bpy6so7exi` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_roles_permissions`
--

LOCK TABLES `t_roles_permissions` WRITE;
/*!40000 ALTER TABLE `t_roles_permissions` DISABLE KEYS */;
INSERT INTO `t_roles_permissions` VALUES ('b9c73f4b8849455e8dc5e0634df7af49','b9c73f4b8849455e8dc5e0634df7af49');
/*!40000 ALTER TABLE `t_roles_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` char(32) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `last_password_reset_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_kpubos9gc2cvtkb0thktkbkes` (`email`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('sadiusahdisadsd0634df7af49','https://i.loli.net/2018/12/31/5c297270b20e2.jpg','2018-08-23 09:11:56','7623039@qq.com',_binary '','14e1b600b1fd579f47433b88e8d85291','admin','2018-11-23 10:12:36');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_users_roles`
--

DROP TABLE IF EXISTS `t_users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_users_roles` (
  `user_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  KEY `FKq4eq273l04bpu4efj0jd0jb98` (`role_id`) USING BTREE,
  CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_users_roles`
--

LOCK TABLES `t_users_roles` WRITE;
/*!40000 ALTER TABLE `t_users_roles` DISABLE KEYS */;
INSERT INTO `t_users_roles` VALUES ('sadiusahdisadsd0634df7af49','b9c73f4b8849455e8dc5e0634df7af49');
/*!40000 ALTER TABLE `t_users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-28 18:38:28

drop table if exists t_employee;
create table t_employee(
	id char(32) PRIMARY key,
	name varchar(20) not null comment '员工名字',
	employee_id varchar(50) unique not null comment '员工编号',
	sex bit(1) not null comment '1表示男，0表示女',
  birthday datetime comment '出生日期',
	natives varchar(50) comment '籍贯',
	education varchar(20) comment '学历',
	email varchar(50) unique not null comment '邮箱',
	type bit(1) default 0 comment '0表示普通员工，1表示管理员',
	create_time datetime  comment '创建时间',
	stat bit(1) default 1 comment '逻辑删除，1表示不删除0表示删除'
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS `t_visits`;
CREATE TABLE `t_visits`  (
  `id` char(32) PRIMARY key,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_counts` bigint(20) NULL DEFAULT NULL,
  `pv_counts` bigint(20) NULL DEFAULT NULL,
  `week_day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS `t_quartz_job`;
CREATE TABLE `t_quartz_job` (
  `id` bigint(20) primary key AUTO_INCREMENT,
  `bean_name` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `is_pause` bit(1) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_quartz_log`;
CREATE TABLE `t_quartz_log`  (
   `id` bigint(20) primary key AUTO_INCREMENT,
   `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
   `createTime` datetime NULL DEFAULT NULL,
   `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
   `exceptionDetail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `is_success` bit(1) NULL DEFAULT NULL,
   `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
   `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
   `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
   `time` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB default CHARACTER SET = utf8;