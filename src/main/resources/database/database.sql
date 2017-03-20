DROP TABLE IF EXISTS `user_role_mapping`;
DROP TABLE IF EXISTS `userrole`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE  `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `firstName` varchar(24) NOT NULL,
  `lastName` varchar(24) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `userEmail` varchar(100) NOT NULL,
  `userName` varchar(24) NOT NULL,
  `userPhone` varchar(10) DEFAULT NULL,
  `userPwd` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE  `userrole` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE  `user_role_mapping` (
  `User_id` bigint(20) NOT NULL,
  `userRoles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`User_id`,`userRoles_id`),
  KEY `FK5D9A9F9181C6E86` (`User_id`),
  KEY `FK5D9A9F9995F6035` (`userRoles_id`),
  CONSTRAINT `FK5D9A9F9995F6035` FOREIGN KEY (`userRoles_id`) REFERENCES `userrole` (`id`),
  CONSTRAINT `FK5D9A9F9181C6E86` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





INSERT INTO `USER`
(`id`,`createdDate`,`enabled`,`firstName`,`lastName`,`modifiedDate`,`userEmail`,`userName`,`userPhone`,`userPwd`)
VALUES(1,'2015-01-01',1,'Sooraj','Thomas','2015-01-01','soorajgthomas@gmail.com','sooraj','9846726696','sooraj')
     ,(2,'2015-01-01',1,'admin','example','2015-01-01','admin@example.com','admin','9846726696','admin')
     ,(3,'2015-01-01',1,'user','example','2015-01-01','user@example.com','user','9846726696','user');


INSERT INTO `USERROLE`(`ID`,`NAME`)
VALUES(1,'ROLE_SUPERADMIN')
     ,(2,'ROLE_ADMIN')
     ,(3,'ROLE_USER');

INSERT INTO `user_role_mapping`(`User_id`,`userRoles_id`)
VALUES((SELECT ID FROM USER WHERE USERNAME='sooraj'),(SELECT ID FROM USERROLE WHERE NAME ='ROLE_SUPERADMIN'))
     ,((SELECT ID FROM USER WHERE USERNAME='admin'),(SELECT ID FROM USERROLE WHERE NAME ='ROLE_ADMIN'))
     ,((SELECT ID FROM USER WHERE USERNAME='user'),(SELECT ID FROM USERROLE WHERE NAME ='ROLE_USER'));