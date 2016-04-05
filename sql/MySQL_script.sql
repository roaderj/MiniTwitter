CREATE database IF NOT EXISTS MiniTwitterDB;

USE MiniTwitterDB;

DROP TABLE IF EXISTS tweets;
DROP TABLE IF EXISTS follows;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	uname varchar(45) NOT NULL UNIQUE,
	password varchar(45) NOT NULL
);

CREATE TABLE tweets (
	uname varchar(45) NOT NULL,
	tweet TEXT,
	FOREIGN KEY (uname) REFERENCES users(uname)
);

CREATE TABLE follows (
	follower varchar(45) NOT NULL,
	following varchar(45) NOT NULL,
	FOREIGN KEY (follower) REFERENCES users(uname),
	FOREIGN KEY (following) REFERENCES users(uname),
	UNIQUE (follower,following)
);

INSERT INTO users (uname,password) VALUES ('Chris','12345');
INSERT INTO users (uname,password) VALUES ('test','12345');
INSERT INTO users (uname,password) VALUES ('test1','12345');
INSERT INTO tweets (uname,tweet) VALUES ('Chris','hello!');
INSERT INTO tweets (uname,tweet) VALUES ('test','hello world');
INSERT INTO follows (follower,following) VALUES ('test','Chris');
INSERT INTO follows (follower,following) VALUES ('test1','test');


