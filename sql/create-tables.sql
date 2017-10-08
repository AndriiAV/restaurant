create table admin
(
  admin_id int auto_increment
    primary key,
  login varchar(20) not null,
  psw varchar(256) not null,
  constraint admin_admin_id_uindex
  unique (admin_id),
  constraint admin_login_uindex
  unique (login)
)
;

create table dish
(
  product_code int auto_increment
    primary key,
  product_name varchar(256) not null,
  price bigint not null,
  photo_id int null
)
;

create table order_item
(
  order_id int not null,
  product_code int not null,
  count int not null,
  price bigint not null,
  primary key (order_id, product_code)
)
;

create table orders
(
  order_id int auto_increment
    primary key,
  user_id int not null,
  date datetime not null,
  amount bigint not null,
  confirmed tinyint(1) default '0' not null
)
;

create index user_id_idx
  on orders (user_id)
;

create table users
(
  user_id int auto_increment
    primary key,
  login varchar(20) not null,
  user_name varchar(256) not null,
  phone_number varchar(45) not null,
  address varchar(256) null,
  psw varchar(256) not null,
  constraint users_login_uindex
  unique (login)
)
;

alter table orders
  add constraint user_id
foreign key (user_id) references restaurant_db.users (user_id)
;

