create table students
(
    id       serial primary key,
    name     varchar(50),
    course   integer
  
);

insert into students (name, course) VALUES ('Ivanov', 3);
insert into students (name, course) VALUES ('Petrov', 3);
insert into students (name, course) VALUES ('Borisov', 1);

select * from students;

