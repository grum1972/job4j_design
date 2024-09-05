create table car_bodies(
	id serial primary key,
	name char(30)
);

create table car_engines(
	id serial primary key,
	name char(30)
);

create table car_transmissions (
	id serial primary key,
	name char(30)
);

create table cars  (
	id serial primary key,
	name char(30),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('Седан'),('Хэтчбек'),('Купе'),('Универсал');
insert into car_engines(name) values('V4'),('V6'),('V8');
insert into car_transmissions(name) values('Manual'),('Automatic'),('Vario');

insert into cars(name,body_id,engine_id,transmission_id) values('Audi A4',1,1,1);
insert into cars(name,body_id,engine_id,transmission_id) values('Audi A3',2,1,1);
insert into cars(name,body_id,engine_id,transmission_id) values('Audi A6',1,2,2);
insert into cars(name,body_id,transmission_id) values('Audi TT',3,2);

select c.id,c.name as car_name,cb.name as body_name,ce.name as engine_name,ct.name as transmission_name from cars as c
	left join car_bodies as cb on body_id=cb.id
	left join car_engines as ce on engine_id=ce.id
	left join car_transmissions as ct on transmission_id=ct.id;

select cb.name from car_bodies as cb
	left join cars as c 
	on cb.id=body_id
	where c.body_id is null;

select ce.name from car_engines as ce
	left join cars as c 
	on ce.id=engine_id
	where c.engine_id is null;

select ct.name from car_transmissions as ct
	left join cars as c 
	on ct.id=transmission_id
	where c.transmission_id is null;
