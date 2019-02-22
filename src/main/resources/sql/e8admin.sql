CREATE DATABASE  IF NOT EXISTS `e8admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `e8admin`;
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
  `iFrame` bit(1) DEFAULT NULL,
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
INSERT INTO `t_menu` VALUES ('1','2018-12-18 15:11:29',_binary '\0','系统管理',NULL,'0',1,'system','system'),('2','2018-12-18 15:14:44',_binary '\0','用户管理','system/user/index','1',2,'peoples','user'),('21','2019-01-04 16:22:03',_binary '\0','多级菜单','','0',900,'menu','nested'),('22','2019-01-04 16:23:29',_binary '\0','二级菜单1','nested/menu1/index','21',999,'menu','menu1'),('23','2019-01-04 16:23:57',_binary '\0','二级菜单2','nested/menu2/index','21',999,'menu','menu2'),('24','2019-01-04 16:24:48',_binary '\0','三级菜单1','nested/menu1/menu1-1','22',999,'menu','menu1-1'),('27','2019-01-07 17:27:32',_binary '\0','三级菜单2','nested/menu1/menu1-2','22',999,'menu','menu1-2'),('3','2018-12-18 15:16:07',_binary '\0','角色管理','system/role/index','1',3,'role','role'),('4','2018-12-18 15:16:45',_binary '\0','权限管理','system/permission/index','1',4,'permission','permission'),('5','2018-12-18 15:17:28',_binary '\0','菜单管理','system/menu/index','1',5,'menu','menu'),('6','2018-12-18 15:17:48',_binary '\0','系统监控',NULL,'0',10,'monitor','monitor'),('7','2018-12-18 15:18:26',_binary '\0','系统日志','monitor/log/index','6',11,'log','logs'),('f4e8b54e290443cd92fb750aecd68f6f','2019-02-22 18:57:02',_binary '\0','ces','','0',1000,'fwb','');
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
INSERT INTO `t_menus_roles` VALUES ('1','1'),('2','1'),('3','1'),('4','1'),('5','1'),('6','1'),('7','1'),('f4e8b54e290443cd92fb750aecd68f6f','1'),('1','2'),('2','2'),('3','2'),('4','2'),('5','2'),('6','2'),('f4e8b54e290443cd92fb750aecd68f6f','2');
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
INSERT INTO `t_permission` VALUES ('1','超级管理员','2018-12-03 12:27:48','ADMIN','0'),('10','角色创建','2018-12-09 20:10:16','ROLES_CREATE','7'),('11','角色编辑','2018-12-09 20:10:42','ROLES_EDIT','7'),('12','角色删除','2018-12-09 20:11:07','ROLES_DELETE','7'),('13','权限管理','2018-12-09 20:11:37','PERMISSION_ALL','0'),('14','权限查询','2018-12-09 20:11:55','PERMISSION_SELECT','13'),('15','权限创建','2018-12-09 20:14:10','PERMISSION_CREATE','13'),('16','权限编辑','2018-12-09 20:15:44','PERMISSION_EDIT','13'),('2','用户管理','2018-12-03 12:28:19','USER_ALL','0'),('29','菜单管理','2018-12-28 17:34:31','MENU_ALL','0'),('3','用户查询','2018-12-03 12:31:35','USER_SELECT','2'),('30','菜单查询','2018-12-28 17:34:41','MENU_SELECT',' 29'),('31','菜单创建','2018-12-28 17:34:52','MENU_CREATE','29'),('32','菜单编辑','2018-12-28 17:35:20','MENU_EDIT','29'),('33','菜单删除','2018-12-28 17:35:29','MENU_DELETE','29'),('4','用户创建','2018-12-03 12:31:35','USER_CREATE','2'),('5','用户编辑','2018-12-03 12:31:35','USER_EDIT','2'),('6','用户删除','2018-12-03 12:31:35','USER_DELETE','2'),('7','角色管理','2018-12-03 12:28:19','ROLES_ALL','0'),('8','角色查询','2018-12-03 12:31:35','ROLES_SELECT','7');
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
INSERT INTO `t_role` VALUES ('1','2018-11-23 11:04:37','超级管理员','超级管理员'),('2','2018-11-23 13:09:06','普通用户','普通用户');
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
INSERT INTO `t_roles_permissions` VALUES ('1','1'),('2','14'),('2','29'),('2','3'),('2','8');
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
INSERT INTO `t_user` VALUES ('1','https://i.loli.net/2018/12/31/5c297270b20e2.jpg','2018-08-23 09:11:56','zhengjie@tom.com',_binary '','14e1b600b1fd579f47433b88e8d85291','admin','2018-11-23 10:12:36'),('3','https://i.loli.net/2018/12/30/5c2871d6aa101.jpg','2018-12-27 20:05:26','test@qq.com',_binary '','14e1b600b1fd579f47433b88e8d85291','test',NULL);
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
INSERT INTO `t_users_roles` VALUES ('1','1'),('3','2');
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

-- Dump completed on 2019-02-22 18:59:19
