create table receiver(
id int not null primary key auto_increment,
first_name varchar(255) not null,
last_name varchar(255) not null,
address varchar(300) not null,
postal_code varchar(10) not null,
city varchar(20) not null,
email varchar(255) not null,
customer_type varchar(40) default 'private',
mobile_number varchar(255)
);