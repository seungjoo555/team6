DROP DATABASE IF EXISTS `community`;
CREATE 	DATABASE IF NOT EXISTS `community`;

use `community`;
DROP TABLE IF EXISTS `category`;

 
CREATE TABLE `category` (
    `ca_num` INT PRIMARY KEY AUTO_INCREMENT,
    `ca_title` VARCHAR(10) NOT NULL unique
);
DROP TABLE IF EXISTS `member`;

CREATE TABLE IF NOT EXISTS`member` (
	`me_id`   varchar(15)   primary key NOT NULL,
    `me_pw`   varchar(20)   NOT NULL,
	`me_email`   varchar(30)   NOT NULL,
    `me_authority` varchar(5) NOT NULL DEFAULT 'USER',
	`me_address`   varchar(30)   NOT NULL,
	`me_phoneNum`   varchar(11)   NOT NULL,
    `me_name`   varchar(30)   NOT NULL,
    `me_ms_state` VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`me_id`	varchar(15)	primary key,
	`me_name`	varchar(15)	NOT NULL,
	`me_pw`	varchar(20)	NOT NULL,
	`me_email`	varchar(30)	NOT NULL,
    `me_authority` varchar(5) NOT NULL DEFAULT 'USER',
	`me_address`	varchar(30)	NOT NULL,
	`me_phoneNum`	varchar(11)	NOT NULL
);

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
	`bo_num`	int	primary key auto_increment,
	`bo_name`	varchar(10)	NOT NULL,
    `bo_ca_num` int not null
);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
	`po_num`	int	primary key auto_increment,
	`po_title`	varchar(20)	NOT NULL,
	`po_content`	text	NOT NULL,
    `po_view` int NOT NULL DEFAULT 0,
	`po_me_id`	varchar(15)	NOT NULL,
	`po_bo_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
	`co_num`	int	primary key auto_increment,
	`co_content`	text	NOT NULL,
	`co_me_id`	varchar(15)	NOT NULL,
	`co_po_num`	int	NOT NULL
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

DROP TABLE IF EXISTS `member_state`;

CREATE TABLE `member_state` (
   `ms_state`   varchar(10)   primary key
);

ALTER TABLE `member` ADD CONSTRAINT `FK_member_state_TO_member_1` FOREIGN KEY (
   `me_ms_state`
)
REFERENCES `member_state` (
   `ms_state`
);

insert into member_state values('가입요청'), ('회원'), ('이용정지'),('관리자');

# 관리자계정 만들어두기
insert into `member` values('admin','admin','admin@admin.com','ADMIN','admin시 admin구 admin동','01099999999','어드민','관리자');