
use `moviedb`;

DROP TABLE IF EXISTS `course`;

CREATE TABLE `userRating` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,user_ratingratings
  `username` varchar(128) DEFAULT NULL,
  
  
  PRIMARY KEY (`user_id`),
  
  UNIQUE KEY `TITLE_UNIQUE` (`username`)
 
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

use `moviedb`;

CREATE TABLE `ratings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` varchar(256) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_USER_ID_idx` (`user_id`),

  CONSTRAINT `FK_USER` 
  FOREIGN KEY (`user_id`) 
  REFERENCES `userrating` (`user_id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE ratings;
