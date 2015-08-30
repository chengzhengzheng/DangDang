show variables like 'character%'

show variables like 'collation%';

set names utf8;

select * from d_book;

desc d_book;

select * from d_category;

desc d_category;

select * from d_product;

select * from d_user;

update d_user set last_login_time=2,last_login_ip='local' where id=1;

delete from d_user;

select * from d_product order by add_time limit 8;

select * from d_product where has_deleted=0 order by add_time limit 8

select dc.*,count(dcp.product_id) from d_category dc left outer join d_category_product dcp on(dc.id=dcp.cat_id) where parent_id=2 group by dc.id

select dp.*,db.* from d_category_product dcp join d_product dp on(dcp.product_id=dp.id) join d_book db on(dp.id=db.id) where dcp.cat_id=9

select * from d_product dp join d_book db on(dp.id=db.id) where dp.has_deleted=0;



desc d_item;

select * from d_item di join d_product dp on (di.product_id=dp.id) order by product_num desc limit 8;

delete from d_item;

insert into d_item values(1,1001,5,'上课头疼的故事',180,10,0);
insert into d_item values(2,1002,6,'上课头疼的故事',180,5,0);
insert into d_item values(3,1001,6,'上课头疼的故事',180,18,0);
insert into d_item values(4,1002,5,'上课头疼的故事',180,20,0);
insert into d_item values(5,1003,3,'上课头疼的故事',180,12,0);
insert into d_item values(6,1003,4,'上课头疼的故事',180,7,0);
insert into d_item values(7,1003,4,'上课头疼的故事',180,5,0);
insert into d_item values(8,1004,4,'上课头疼的故事',180,10,0);
insert into d_item values(9,1004,3,'上课头疼的故事',180,14,0);

select * from d_item;
desc d_item;

select dp.*,sum(di.product_num) as cnt from d_item di join d_product dp on (di.product_id=dp.id) group by dp.id order by cnt desc limit 8

select * from d_order;

delete from d_order;

desc d_order;

insert into d_order values(1001,1,1,1346125230501,'很好',0,'阿猫','西安','710000','13500000000','029-88888888');
insert into d_order values(1002,2,1,1346125230501,'很好',0,'阿猫','西安','710000','13500000000','029-88888888');
insert into d_order values(1003,3,1,1346125230501,'很好',0,'阿猫','西安','710000','13500000000','029-88888888');
insert into d_order values(1004,4,1,1346025230501,'很好',0,'阿猫','西安','710000','13500000000','029-88888888');


select dp.*,sum(di.product_num) as cnt from d_item di join d_product dp on (di.product_id=dp.id) join d_order dod on(di.order_id=dod.id) where dod.order_time>1346025230501 group by dp.id order by cnt desc limit 8 

desc d_item;

desc d_order;

desc d_receive_address
select * from d_receive_address; 


select dp.*,sum(di.product_num) as cnt from d_item di join d_product dp on (di.product_id=dp.id) join d_order dod on(di.order_id=dod.id) where dod.order_time>1344560322614 group by dp.id order by cnt desc limit 7


select * from d_order;



create table ATMUser(
 	cardId varchar(20) primary key,
 	accountBalance double,
 	identityId varchar(20),
 	password varchar(30));
 	
insert into ATMUser values('1001',8000.0,'321321199901010031','1234');
insert into ATMUser values('1002',6000.0,'32132119870101742X','1234');
insert into ATMUser values('1003',7000.0,'321321200008150031','1234');
insert into ATMUser values('1004',9000.0,'321321199003110031','1234');

select * from ATMUser;

