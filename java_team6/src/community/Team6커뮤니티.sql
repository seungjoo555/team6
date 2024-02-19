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
    `me_name`   varchar(30)   NOT NULL,
    `me_ms_state` VARCHAR(10) NOT NULL
);

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

insert into member_state values('가입요청'), ('회원'), ('이용정지'),('관리자');

insert into `member` values('admin','admin','admin@admin.com','ADMIN','admin시 admin구 admin동','01099999999','어드민','관리자');

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

ALTER TABLE `board` ADD CONSTRAINT `FK_community_TO_board_1` FOREIGN KEY (
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