DROP DATABASE IF EXISTS t1;

CREATE DATABASE t1;

USE t1;

CREATE TABLE article(
    id INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title CHAR(200) NOT NULL,
    `body` TEXT NOT NULL,
    `mid` INT(5) NOT NULL,
    hit INT(5) NOT NULL,
    regDate DATETIME NOT NULL
);

CREATE TABLE `member`(
    id INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    loginId CHAR(50) UNIQUE NOT NULL,
    loginPw CHAR(50) NOT NULL,
    nickname CHAR(50) NOT NULL,
    regDate DATETIME NOT NULL
);

CREATE TABLE reply(
    id INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    aid INT(5) UNSIGNED NOT NULL,
    `body` CHAR(255) NOT NULL,
    `mid` INT(5) UNSIGNED NOT NULL,
    regDate DATETIME NOT NULL
);

CREATE TABLE `like`(
    aid INT(5) UNSIGNED NOT NULL,
    `mid` INT(5) UNSIGNED NOT NULL,
    regDate DATETIME NOT NULL
);

INSERT INTO article 
SET title = '제목1',
`body` = '제목1',
`mid` = 1,
hit = 0,
regDate = NOW();

INSERT INTO article 
SET title = '제목2',
`body` = '제목2',
`mid` = 1,
hit = 0,
regDate = NOW();

INSERT INTO article 
SET title = '제목3',
`body` = '제목3',
`mid` = 2,
hit = 0,
regDate = NOW();


INSERT INTO `member`
SET loginId = 'user1',
loginPw = 'pass1',
nickname = '홍길동',
regDate = NOW();

INSERT INTO `member`
SET loginId = 'user2',
loginPw = 'pass2',
nickname = '홍길순',
regDate = NOW();


INSERT INTO reply
SET aid = 1,
`body` = '안녕하세요 홍길동입니다.',
`mid` = 1,
regDate = NOW();

INSERT INTO reply
SET aid = 1,
`body` = '안녕하세요 홍길순입니다.',
`mid` = 2,
regDate = NOW();

INSERT INTO reply
SET aid = 2,
`body` = '무플 방지',
`mid` = 2,
regDate = NOW();


INSERT INTO `like` 
SET aid = 1,
`mid` = 1, 
regDate = NOW();

INSERT INTO `like` 
SET aid = 2,
`mid` = 1, 
regDate = NOW();

INSERT INTO `like` 
SET aid = 1,
`mid` = 2, 
regDate = NOW();
