create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- Задание 1
create
or replace function add_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure add_tax();

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 100);
insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

-- Задание 2
create
or replace function add_tax_row()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_row_trigger
    before insert
    on products
    for each row
    execute procedure add_tax_row();

insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 8, 100);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 3, 50);	

-- Задание 3

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function add_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price,date) 
			VALUES (NEW.name, NEW.price,CURRENT_DATE);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_history_trigger
    after insert
    on products
    for each row
    execute procedure add_history();

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 3, 500);