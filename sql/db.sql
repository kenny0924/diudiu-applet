
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
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '地区表';

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
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '零售店表';





create table user_sms_code
(
  id           int auto_increment
  comment '主键ID'
    primary key,
  uuid        varchar(32)   not null
  comment '短信UUID',
  sms_code     varchar(6)   not null
  comment '验证码',
  user_tel     varchar(11)  not null
  comment '手机号',
  create_time datetime     not null
  comment '创建时间'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '短信验证码表';



create table user_auth_token
(
  id    bigint auto_increment comment '主键'
    primary key,
  token_uuid varchar(64) not null
  comment 'Token UUID',
  user_id bigint(11) not null
  comment '用户UUId',
  token varchar(512) not null
  comment 'Token',
  modify_time datetime null
  comment '修改时间',
  create_time datetime not null
  comment '创建时间' default now(),
  constraint token_uuid
  unique (token_uuid),
  constraint user_uuid
  unique (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment 'Token表';


create table users
(
  id            bigint auto_increment primary key
  comment '主键',
  user_tel      varchar(11)                        not null
  comment '手机号',
  name          varchar(32)                        null
  comment '名称',
  head_portrait varchar(128)                       null
  comment '头像',
  wc_open_id    varchar(64)                        null
  comment '微信OpenID',
  vip_level        int       not null default 0
  comment '是否是会员 0:不是会员 1:普通会员 2:商户会员 商户会员要在后台管理录入 与当前用户绑定',
  diudiu_coin int   not null
  comment '丢丢币' default 0,
  create_time   datetime default CURRENT_TIMESTAMP not null
  comment '创建时间',
  modify_time   datetime                           null
  comment '修改时间'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '用户表';


create table images
(
  id      bigint  not null primary key auto_increment
  comment '主键',
  foreign_id  int   not null
  comment '外键ID',
  type  int not null
  comment '类型 1:商品banner图片',
  source  varchar(256)  not null
  comment '图片连接',
  create_time datetime not null
  comment '创建时间' default now()
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '图片表';


create table goods_content
(
  id    bigint    not null  primary key auto_increment
  comment '主键',
  goods_id  int not null
  comment '商品ID',
  type    int   not null
  comment '类型 1:图片 2:视频',
  paths   varchar(256)  null
  comment '图片路径',
  video_url varchar(256)  null
  comment '视频地址',
  cover_url varchar(256)  null
  comment '视频封面地址',
  create_time datetime not null
  comment '创建时间' default now()
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '商品内容表';


create table goods_size
(
  id    bigint    not null primary key auto_increment
  comment '主键',
  goods_id   int not null
  comment '商品ID',
  name  varchar(16) not null
  comment '名字',
  available_stock int   not null
  comment '库存',
  size  varchar(8)  not null
  comment '尺码大小',
  create_time   datetime  not null
  comment '创建时间' default now()
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '商品尺码表';

create table goods_type
(
  id      int primary key   auto_increment
    comment '主键',
  name    varchar(16) not null
    comment '名称',
  level   int   not null
    comment '类型级别' default 0,
  pid     int   not null
    comment '上级ID' default 0,
  create_time   datetime
    comment '创建时间' default now()
)ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '商品类型表';

create table goods_param
(
  id    bigint    not null primary key auto_increment
    comment '主键',
  goods_id  int not null
  comment '商品ID',
  name  varchar(16)   not null
    comment '参数名称',
  value varchar(64)   not null
    comment '参数值',
  create_time datetime  not null
    comment '创建时间' default now()
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '商品参数表';

create table goods
  (
  id  int   not null  primary key auto_increment
  comment '主键',
  name  varchar(64) not null
  comment '商品名称',
  sn varchar(32) not null
  comment '商品的产品号 一个商品可以有多种颜色/尺寸 但是产品号是一样的',
  image varchar(256) null
  comment '商品图片',
  color varchar(16) not null
  comment '颜色',
  real_price  double(10,2)  not null
  comment '实际价格',
  discount_price double(10, 2)  not null
  comment '折扣价格',
  diudiu_price  double(10, 2) not null
  comment '丢丢币价格',
  goods_type varchar(127) not null
  comment '商品类型 多个：1,3,4,6区分',
  sale_vol int not null
  comment '销量' default 0,
  status  int not null
  comment '是否下架 0:没有 1:下架' default 0,
  create_time datetime not null
  comment '创建时间' default now(),
  modify_time datetime  null
  comment '修改时间'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '商品表';




create table goods_collect (
  id    int   not null primary key auto_increment
  comment '主键',
  goods_id  int   not null
  comment '收藏的商品ID',
  user_id   int   not null
  comment '收藏的用户',
  create_time datetime  not null
  comment '收藏时间' default now(),
  index user_id_create_time(user_id, create_time desc )
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '收藏表';


create table tag (
  id    int   not null primary key auto_increment
  comment '主键',
  name  varchar(32) not null
  comment '标签名称',
  is_dir int    not null
  comment '是否是目录标签 0:不是 1:是' default 0,
  create_time datetime not null
  comment '创建时间' default now()
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '标签表';

create table goods_tag (
  id    int   not null primary key auto_increment
  comment '主键',
  goods_id  int   not null
  comment '商品ID',
  tag_id    int   not null
  comment '标签ID',
  create_time datetime not null
  comment '创建时间' default now()
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '商品标签表';

create table banner (
  id    int   not null primary key auto_increment
  comment '主键',
  title varchar(64)   not null
  comment '标题',
  img_url   varchar(256)  not null
  comment '图片路径',
  tag_id    int not null
  comment '跳转的标签ID',
  status    int   not null
  comment '状态 0:关闭 1:开启' default 1,
  create_time   datetime  not null
  comment '创建时间'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '首页banner表';

insert into banner (title, img_url, tag_id, status, create_time)
values ('新品预警', 'www.baidu.com', 1, 1, now());

create table navigation (
  id    int   not null primary key auto_increment
  comment '主键',
  title varchar(64) not null
  comment '标题',
  tag_id  int not null
  comment '标签ID',
  status  int not null
  comment '状态',
  create_time  datetime not null
  comment '创建时间',
  modify_time  datetime null
  comment '修改时间'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '首页导航表';

create table recommend (
  id   int not null primary key auto_increment
  comment '主键',
  title   varchar(64) not null
  comment '标题',
  tag_id  int   not null
  comment '标签ID',
  status  int not null
  comment '状态',
  create_time datetime not null
  comment '创建时间' default now(),
  modify_time datetime null
  comment '修改时间'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '首页推荐表';

create table goods_recommend (
  id  int not null primary key auto_increment
  comment '主键',
  goods_id  int not null
  comment '商品ID',
  recommend_id  int not null
  comment '推荐ID'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI
  comment '推荐商品表';
