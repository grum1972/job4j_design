create table type
(
    id   serial primary key,
    name varchar(255)
);

create table product
(
    id    serial primary key,
    name  varchar(255),
	expired_date date,
    price float,
	type_id int references type(id)
);


insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('МОРОЖЕНОЕ');

insert into product(name,expired_date,price,type_id) values ('Сыр плавленый','2024-09-02',30.00,1);
insert into product(name,expired_date,price,type_id) values ('Сыр моцарелла','2024-09-07',200.00,1);
insert into product(name,expired_date,price,type_id) values ('Сыр российский','2024-09-08',100.00,1);
insert into product(name,expired_date,price,type_id) values ('Молоко 2,5%','2024-09-06',50.00,2);
insert into product(name,expired_date,price,type_id) values ('Молоко 3,5%','2024-09-03',60.00,2);
insert into product(name,expired_date,price,type_id) values ('Молоко топленое','2024-09-08',200.00,2);
insert into product(name,expired_date,price,type_id) values ('Мороженое пломбир','2024-10-08',100.00,3);
insert into product(name,expired_date,price,type_id) values ('Мороженое эскимо','2024-10-08',60.00,3);


select p.name,expired_date,price from product as p
	join type as t on type_id=t.id
	where t.name='СЫР';

select * from product
	where name like '%Мороженое%';

select * from product
	where expired_date < CURRENT_DATE;

select * from product
	where price in (
	select max(price) from product
	);

select t.name,count(*) from product as p
	join type as t on type_id=t.id
	group by t.name;

select p.name,expired_date,price from product as p
	join type as t on type_id=t.id
	where t.name in ('СЫР','МОЛОКО');

select t.name,count(*) from product as p
	join type as t on type_id=t.id
	group by t.name
	having count(*) < 10;	

select p.name,expired_date,price,t.name from product as p
	join type as t on type_id=t.id;
