create table fauna(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);
INSERT INTO fauna(name,avg_age,discovery_date) values ('catfish',8000, '1970-09-01');
INSERT INTO fauna(name,avg_age,discovery_date) values ('lion',15000, '1960-10-01');
INSERT INTO fauna(name,avg_age,discovery_date) values ('elefant',20000, '1930-10-01');
INSERT INTO fauna(name,avg_age,discovery_date) values ('bird',1000, '1940-10-01');
INSERT INTO fauna(name,avg_age,discovery_date) values ('dino',100000,null);

select * from fauna where name like('%fish%');
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null; 
select * from fauna where discovery_date < '1950-01-01';