
create table roles (
    id serial primary key,
    role char(30)
);

create table rules (
    id serial primary key,
    rule char(30)
);

create table roles_rules(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table users (
    id serial primary key,
    name char(30),
  	lastname char(30),
    role_id int references roles(id)
);

create table categories (
    id serial primary key,
    category char(30)
);

create table states (
    id serial primary key,
    state char(30)
);


create table items (
    id serial primary key,
    item int,
  	user_id int references users(id),
  	category_id int references categories(id),
  	state_id int references states(id)
);

create table comments  (
    id serial primary key,
    comment char(30),
  	item_id int references items(id)
);

create table attachs   (
    id serial primary key,
    file char(30),
  	item_id int references items(id)
);