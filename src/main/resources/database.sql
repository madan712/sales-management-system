-- Create schema
create schema sms;

-- Switch to this schema
use sms;


-- Create tables
create table users
(
	userid int not null auto_increment, primary key (userid),
	username varchar(50) not null,
	password varchar(50) not null,
	role varchar(20) not null, -- SALES, ADMIN
	unique(username)
);

insert into users(username,password,role) values ('ram','test','SALES');
insert into users(username,password,role) values ('shyam','test','ADMIN');

create table product
(
	productid int not null auto_increment, primary key (productid),
	barcode varchar(50) not null,
	productname varchar(50) not null,
	color varchar(20) not null,
	productdesc varchar(200) not null,
	
	unavailablereason varchar(200) null,
	
	price decimal(6,2) not null,
	quantity int not null,
	unique(barcode)

);

insert into product(barcode,productname,color,productdesc,unavailablereason,price,quantity) values ('12346812','Cross paded purse','black','all black leather with gold buttons and gold zipper','NA',120,10);
insert into product(barcode,productname,color,productdesc,unavailablereason,price,quantity) values ('4525235','Jeans straight x100','blue','faded blue on left side with a little distress on right side below knee area','NA',65,0);
insert into product(barcode,productname,color,productdesc,unavailablereason,price,quantity) values ('8796245','Cap Adidas','Red','Adidas cap with red stripe','NA',100,3);


create table orders
(
	orderid int not null auto_increment, primary key (orderid),
	totalquantity int not null,
	totalprice decimal(6,2) not null,
	userid int not null, foreign key (userid) references users(userid) ON DELETE CASCADE,
	orderat datetime not null
);


create table orderitem
(
	orderitemid int not null auto_increment, primary key (orderitemid),
	orderid int not null, foreign key (orderid) references orders(orderid) ON DELETE CASCADE,
	productid int not null, foreign key (productid) references product(productid) ON DELETE CASCADE,
	quantity int not null,
	subtotal decimal(6,2) not null,
	tax decimal(6,2) not null,
	total decimal(6,2) not null
);