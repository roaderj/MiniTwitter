CREATE database IF NOT EXISTS MiniTwitterDB;

USE MiniTwitterDB;

DROP TABLE IF EXISTS tweets;
DROP TABLE IF EXISTS follows;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	uid int(11) NOT NULL AUTO_INCREMENT,
	uname varchar(45) NOT NULL UNIQUE,
	password varchar(45) NOT NULL,
	PRIMARY KEY (uid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO users (uname,password) VALUES ('admin','admin');
INSERT INTO users (uname,password) VALUES ('user','user');

CREATE TABLE tweets (
	tid int(11) NOT NULL AUTO_INCREMENT,
	uname varchar(45) NOT NULL,
	tweet TEXT,
	PRIMARY KEY (tid),
	FOREIGN KEY (uname) REFERENCES users(uname)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE follows (
	follower varchar(45) NOT NULL,
	following varchar(45) NOT NULL,
	FOREIGN KEY (follower) REFERENCES users(uname),
	FOREIGN KEY (following) REFERENCES users(uname),
	UNIQUE (follower,following)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;