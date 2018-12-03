
create database diudiu;
use diudiu;

create table delivery_address_area
(
	id bigint auto_increment comment 'id'
		primary key,
	pid varchar(100) null comment '父id',
	name varchar(100) null comment '名称',
	pname varchar(100) null comment '父名称',
	create_date datetime null comment '创建时间',
	update_by varchar(64) null comment '更新者',
	update_date datetime null comment '更新时间',
	del_flag char default '0' not null comment '删除标记',
	create_by varchar(64) null comment '创建者',
	type char default '0' null comment '地区类型，0省，1市，2区或县'
)
comment '地址区域表' charset=utf8mb4
;

create table retail_store
(
	id bigint auto_increment
		primary key,
	store_name varchar(64) not null comment '商店名称',
	store_tel varchar(16) not null comment '电话',
	province_id int not null comment '省份ID',
	city_id int not null comment '城市/城区ID',
	addr varchar(128) not null comment '详细地址',
	business_hours varchar(32) not null comment '营业时间',
	create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
	modify_time datetime null comment '修改时间'
)
comment '零售店表'
;


select * from retail_store;
