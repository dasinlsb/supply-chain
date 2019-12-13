DROP TABLE IF EXISTS `organizations`;

CREATE TABLE `organizations` (
  `orgId` varchar(64) NOT NULL,
  `orgName` varchar(100) NOT NULL,
  `orgType` varchar(12) NOT NULL,
  `corporationIdCardNumber` varchar(18) NOT NULL,
  `phoneNumber` varchar(13) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `createTime` varchar(128) NOT NULL,
  PRIMARY KEY (`orgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织';
