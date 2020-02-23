
use `simple-payment`;

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `balance`float
  
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `TITLE_UNIQUE` (`name`)
 
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

use `simple-payment`;

CREATE TABLE `transactions` (
  `trans_id` int(11) NOT NULL AUTO_INCREMENT,
  `trans_type` varchar(256) NOT NULL,
  `amount` float  NOT NULL,
  `account_id` int(11) NOT NULL

  PRIMARY KEY (`trans_id`),

  KEY `FK_USER_ID_idx` (`account_id`),

  CONSTRAINT `FK_ACCOUNT` 
  FOREIGN KEY (`account_id`) 
  REFERENCES `accounts` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

