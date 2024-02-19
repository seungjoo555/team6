drop database if exists community;

create database if not exists community;

use community;

DROP TABLE IF EXISTS `member`;

CREATE TABLE IF NOT EXISTS`member` (
   `me_id`   varchar(15)   primary key NOT NULL,
    `me_pw`   varchar(20)   NOT NULL,
   `me_email`   varchar(30)   NOT NULL,
    `me_authority` varchar(5) NOT NULL DEFAULT 'USER',
   `me_address`   varchar(30)   NOT NULL,
   `me_phoneNum`   varchar(13)   NOT NULL,
    `me_name`   varchar(30)   NOT NULL
);

ALTER TABLE `member`
ADD COLUMN `me_ms_state` VARCHAR(10) NOT NULL DEFAULT '가입요청' AFTER `me_name`;

DROP TABLE IF EXISTS `member_state`;

CREATE TABLE `member_state` (
   `ms_state` varchar(10)   primary key
);

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
    `bo_num` INT PRIMARY KEY AUTO_INCREMENT,
    `bo_name` VARCHAR(10) NOT NULL,
    `bo_ca_num` INT NOT NULL
);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
    `po_num` INT PRIMARY KEY AUTO_INCREMENT,
    `po_title` VARCHAR(20) NOT NULL,
    `po_content` TEXT NOT NULL,
    `po_view` int NOT NULL DEFAULT 0,
    `po_me_id` VARCHAR(15) NOT NULL,
    `po_bo_num` INT NOT NULL
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
    `co_num` INT PRIMARY KEY AUTO_INCREMENT,
    `co_content` VARCHAR(100) NOT NULL,
    `co_me_id` VARCHAR(15) NOT NULL,
    `co_po_num` INT NOT NULL
);

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
    `ca_num` INT PRIMARY KEY AUTO_INCREMENT,
    `ca_title` VARCHAR(10) NULL
);

ALTER TABLE `member` ADD CONSTRAINT `FK_member_state_TO_member_1` FOREIGN KEY (
   `me_ms_state`
)
REFERENCES `member_state` (
   `ms_state`
);

ALTER TABLE `board` ADD CONSTRAINT `FK_category_TO_board_1` FOREIGN KEY (
    `bo_ca_num`
)
REFERENCES `category` (
	`ca_num`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_member_TO_post_1` FOREIGN KEY (
   `po_me_id`
)
REFERENCES `member` (
   `me_id`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_board_TO_post_1` FOREIGN KEY (
   `po_bo_num`
)
REFERENCES `board` (
   `bo_num`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_member_TO_comment_1` FOREIGN KEY (
	`co_me_id`
)
REFERENCES `member` (
	`me_id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_post_TO_comment_1` FOREIGN KEY (
	`co_po_num`
)
REFERENCES `post` (
	`po_num`
);

ALTER TABLE `community`.`board` 
DROP FOREIGN KEY `FK_category_TO_board_1`;
ALTER TABLE `community`.`board` 
ADD CONSTRAINT `FK_category_TO_board_1`
  FOREIGN KEY (`bo_ca_num`)
  REFERENCES `community`.`category` (`ca_num`)
  ON DELETE CASCADE;

ALTER TABLE `community`.`comment` 
DROP FOREIGN KEY `FK_post_TO_comment_1`;
ALTER TABLE `community`.`comment` 
ADD CONSTRAINT `FK_post_TO_comment_1`
  FOREIGN KEY (`co_po_num`)
  REFERENCES `community`.`post` (`po_num`)
  ON DELETE CASCADE;

ALTER TABLE `community`.`post` 
DROP FOREIGN KEY `FK_board_TO_post_1`;
ALTER TABLE `community`.`post` 
ADD CONSTRAINT `FK_board_TO_post_1`
  FOREIGN KEY (`po_bo_num`)
  REFERENCES `community`.`board` (`bo_num`)
  ON DELETE CASCADE;

insert into member_state values('가입요청'), ('회원'), ('이용정지'),('관리자');

# 관리자계정 만들어두기
insert into `member` values('admin','admin','admin@admin.com','ADMIN','admin시 admin구 admin동','01099999999','어드민','관리자');
insert into `member` values('jkh123','!jkh1234','jkh123@admin.com','USER','서울시 강남구 역삼동','01099939999','정경호','회원');
insert into `member` values('lbh123','!lbh1234','jkh123@admin.com','USER','서울시 강남구 역삼동','01099939299','임병훈','이용정지');
insert into `member` values('lcb123','!lcb1234','jkh123@admin.com','USER','서울시 강남구 역삼동','01099939599','이철범','이용정지');
#킹스맨  카페 카테고리
insert into `category`(ca_title) values('킹스맨');
insert into `category`(ca_title) values('키스맨');

#킹스맨 카페 게시판
insert into `board`(bo_name, bo_ca_num) values('킹스맨공지게시판',1);
insert into `board`(bo_name, bo_ca_num) values('키스맨공지게시판',2);

insert into `post`(po_title, po_content, po_me_id, po_bo_num) values('ㅋㅋㅋ','ㅋㅋㅋㅋㅋ','jkh123',1);

insert into comment(co_content, co_me_id, co_po_num) values('좋습니다', 'jkh123', 1);
insert into comment(co_content, co_me_id, co_po_num) values('좋아요', 'lbh123', 1);