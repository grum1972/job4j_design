create table departments 
(
    id   serial primary key,
    name varchar(255)
);

create table employees
(
    id       serial primary key,
    name     varchar(255),
    department_id int references departments(id)
);


insert into departments(name) values ('Logistic'),('Finance');
insert into departments(name) values ('Goods'),('Support');
insert into employees(name,department_id) values ('Ivanov',1);
insert into employees(name,department_id) values ('Petrov',1);
insert into employees(name,department_id) values ('Sidorov',2);
insert into employees(name,department_id) values ('Borisov',2);

select * from employees ;

select * from departments d
         left join employees e on d.id = e.department_id;
		 		 
select * from departments d
         right join employees e on d.id = e.department_id;

select * from departments d
         full join employees e on d.id = e.department_id;		 

select * from departments , employees;
         
		 

select d.name from departments d
         left join employees e on d.id = e.department_id
		 where e.name is null;		

select * from departments d
         left join employees e on d.id = e.department_id;
		 		 
select * from employees e
         right join departments d on d.id = e.department_id;

create table teens(
	id   serial primary key,
    name varchar(255),
	gender varchar(255)
);

insert into teens(name,gender) values ('Ivan','male');
insert into teens(name,gender) values ('Petr','male');
insert into teens(name,gender) values ('Olga','female');
insert into teens(name,gender) values ('Anna','female');
insert into teens(name,gender) values ('Mary','female');

select * from teens t
	cross join teens t1 
	where t.gender!=t1.gender and t.id<t1.id;
	


