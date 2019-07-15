/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.62 : Database - test1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test1`;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) NOT NULL,
  `parent_id` int(11) NOT NULL COMMENT '父菜单编号',
  `level` int(11) DEFAULT NULL COMMENT '菜单级别',
  `url` varchar(60) DEFAULT NULL COMMENT '地址',
  `state` int(11) NOT NULL COMMENT '状态（0启动，1禁止）',
  `remarks` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `keyword` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`name`,`keyword`,`description`) values (1,'test_role','findAll',NULL);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` varchar(100) DEFAULT NULL,
  `productNum` varchar(100) DEFAULT NULL,
  `productName` varchar(100) DEFAULT NULL,
  `cityName` varchar(100) DEFAULT NULL,
  `departureTime` datetime DEFAULT NULL,
  `departureTimeStr` varchar(100) DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `productDesc` varchar(100) DEFAULT NULL,
  `productStatus` int(11) DEFAULT NULL,
  `productStatusStr` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`productNum`,`productName`,`cityName`,`departureTime`,`departureTimeStr`,`productPrice`,`productDesc`,`productStatus`,`productStatusStr`) values ('1','test','test','test','2019-07-10 10:21:58','test',12,'test',1,'1');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `keyword` varchar(10) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`keyword`,`description`) values (1,'test_role',NULL,'测试');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`permissionid`,`roleid`) values (1,1,1);

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phoneNum` varchar(15) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `statusStr` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`id`,`userName`,`password`,`phoneNum`,`status`,`statusStr`,`email`) values (1,'customer','','13331333',1,NULL,'customer@163.com');

/*Table structure for table `userinfo_role` */

DROP TABLE IF EXISTS `userinfo_role`;

CREATE TABLE `userinfo_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo_role` */

insert  into `userinfo_role`(`id`,`userId`,`roleId`) values (2,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
