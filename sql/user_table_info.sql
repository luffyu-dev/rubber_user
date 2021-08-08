

create database user_db;
use user_db;
CREATE TABLE t_user_account_info (
    id int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    uid int(11) unsigned NOT NULL default 0 COMMENT '用户id，自100000开始自增。',
    user_account varchar (50) NOT NULL default '' COMMENT '用户登录账号',
    user_password varchar (255) NOT NULL default '' COMMENT '用户登录密码',
    user_salt varchar (50) not null  default '' comment '登录盐值',
    third_account_id varchar (255) NOT NULL default '' COMMENT '第三方登录账户id',
    third_account_key varchar (500) NOT NULL default '' COMMENT '第三方登录账户相关密钥信息',
    account_type smallint (3) not null default 0 comment '用户登录类型 0表示账号密码登录 1表示手机验短登录 2表示微信扫码登录',
    user_phone varchar(11) not null default '' COMMENT '用户手机号',
    user_email varchar(50) NOT NULL default '' COMMENT '用户邮箱',
    user_status smallint(3) not null  default 0 COMMENT '用户状态 10表示正常 20表示停用 21表示注销',
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    modify_time datetime DEFAULT NULL COMMENT '最后更新时间',
    version int(11) not null  default 0 COMMENT '版本号',
    PRIMARY KEY (id),
    UNIQUE uniq_user_account (user_account),
    KEY idx_third_account (third_account_id,uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户表';

CREATE TABLE t_global_uid_generator (
    uid int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT  '用户id，自100000开始自增。',
    params varchar(50) DEFAULT NULL COMMENT '扩展参数',
    PRIMARY KEY (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='uid的全局自增生成器';
-- 默认从1000000开始算起 每满100w的用户 会分散在一个库中
insert into t_global_uid_generator (uid) value (1000000);


create database user_db_00;
create database user_db_01;
create database user_db_02;
create database user_db_03;
create database user_db_04;
create database user_db_05;
create database user_db_06;
create database user_db_07;
create database user_db_08;
create database user_db_09;


use user_db_00;
CREATE TABLE t_user_basic_info_00 (
    id int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    uid int(11) unsigned NOT NULL default 0 COMMENT '外表关联的uid',
    user_nick varchar(50) not null  default '' comment '用户昵称',
    user_motto varchar(255) not null  default '' comment '用户简介',
    user_avatar varchar(255) not null  default '' comment '用户头像地址',
    user_sex smallint(3) not null  default 0 comment '0表示男 1表示女 2表示未知',
    user_birthday datetime DEFAULT NULL COMMENT '生日',
    user_area varchar (255) not null default '' comment '用户地区',
    user_address varchar(255) not null DEFAULT '' COMMENT '用户地址',

    create_time datetime DEFAULT NULL COMMENT '创建时间',
    modify_time datetime DEFAULT NULL COMMENT '最后更新时间',
    version int(11) not null  default 0 COMMENT '版本号',
    PRIMARY KEY (id),
    KEY idx_user (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';


CREATE table t_user_basic_info_01 like t_user_basic_info_00;
CREATE table t_user_basic_info_02 like t_user_basic_info_00;
CREATE table t_user_basic_info_03 like t_user_basic_info_00;
CREATE table t_user_basic_info_04 like t_user_basic_info_00;
CREATE table t_user_basic_info_05 like t_user_basic_info_00;
CREATE table t_user_basic_info_06 like t_user_basic_info_00;
CREATE table t_user_basic_info_07 like t_user_basic_info_00;
CREATE table t_user_basic_info_08 like t_user_basic_info_00;
CREATE table t_user_basic_info_09 like t_user_basic_info_00;

mysqldump user_db_00 -u root -proot | mysql user_db_01 -u root -proot
mysqldump user_db_00 -u root -proot | mysql user_db_02 -u root -proot
mysqldump user_db_00 -u root -proot | mysql user_db_03 -u root -proot
mysqldump user_db_00 -u root -proot | mysql user_db_04 -u root -proot
mysqldump user_db_00 -u root -proot | mysql user_db_05 -u root -proot
mysqldump user_db_00 -u root -proot | mysql user_db_06 -u root -proot
mysqldump user_db_00 -u root -proot | mysql user_db_07 -u root -proot
mysqldump user_db_00 -u root -proot | mysql user_db_08 -u root -proot
mysqldump user_db_00 -u root -proot | mysql user_db_09 -u root -proot





