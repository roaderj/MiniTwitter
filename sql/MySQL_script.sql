CREATE database IF NOT EXISTS MiniTwitterDB;

DROP TABLE IF EXISTS login;

CREATE TABLE login (
	uid int(11) NOT NULL AUTO_INCREMENT,
	uname varchar(45) NOT NULL,
	password varchar(45) NOT NULL,
	PRIMARY KEY (uid)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

INSERT INTO login (uname,password) VALUES ('admin','admin');