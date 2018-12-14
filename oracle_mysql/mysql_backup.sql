/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : match

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2018-01-18 12:35:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `schedule`
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `scheduleid` int(4) NOT NULL AUTO_INCREMENT,
  `matchdate` date DEFAULT NULL,
  `matchtime` varchar(5) DEFAULT NULL,
  `hometeamid` int(2) DEFAULT NULL,
  `awayteamid` int(2) DEFAULT NULL,
  `result` varchar(5) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `count` int(4) DEFAULT NULL,
  PRIMARY KEY (`scheduleid`),
  KEY `fk_hometeamid` (`hometeamid`),
  KEY `fk_awayteamid` (`awayteamid`),
  CONSTRAINT `fk_awayteamid` FOREIGN KEY (`awayteamid`) REFERENCES `team` (`teamid`),
  CONSTRAINT `fk_hometeamid` FOREIGN KEY (`hometeamid`) REFERENCES `team` (`teamid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('1', '2018-01-18', '15:00', '1', '2', '3:1', '北京工体', '20');
INSERT INTO `schedule` VALUES ('2', '2018-01-18', '16:00', '1', '3', '2:1', '北京工体', '21');
INSERT INTO `schedule` VALUES ('3', '2018-01-18', '17:00', '1', '4', '3:4', '北京工体', '22');
INSERT INTO `schedule` VALUES ('4', '2018-01-19', '15:00', '2', '3', '0:1', '长春奥体中心', '23');
INSERT INTO `schedule` VALUES ('5', '2018-01-19', '16:00', '2', '4', '2:1', '长春奥体中心', '24');
INSERT INTO `schedule` VALUES ('6', '2018-01-20', '15:00', '3', '4', '0:1', '广州工体', '25');

-- ----------------------------
-- Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `teamid` int(2) NOT NULL AUTO_INCREMENT,
  `teamname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`teamid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1', '北京国安');
INSERT INTO `team` VALUES ('2', '长春亚泰');
INSERT INTO `team` VALUES ('3', '广州恒大');
INSERT INTO `team` VALUES ('4', '山东鲁能');
