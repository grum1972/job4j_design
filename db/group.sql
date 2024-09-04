create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name,price) values ('pc',300.00);
insert into devices(name,price) values ('laptop',800.00);
insert into devices(name,price) values ('hdd',50.00);
insert into devices(name,price) values ('printer',100.00);
insert into devices(name,price) values ('monitor',150.00);

insert into people(name) values ('Ivan');
insert into people(name) values ('Boris');

insert into devices_people(device_id,people_id) values (1,1);
insert into devices_people(device_id,people_id) values (1,2);
insert into devices_people(device_id,people_id) values (2,1);
insert into devices_people(device_id,people_id) values (4,2);
insert into devices_people(device_id,people_id) values (5,1);
insert into devices_people(device_id,people_id) values (5,2);

select avg(price) from devices;

select pp.name, avg(price) from devices_people as dp
	join devices as d on d.id=dp.device_id
	join people as pp on pp.id=dp.people_id
	group by pp.name;
	
select pp.name, avg(price) from devices_people as dp
	join devices as d on d.id=dp.device_id
	join people as pp on pp.id=dp.people_id
	group by pp.name
	having avg(price) > 200;
