create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);
select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

-- procedure delete 
create
or replace procedure delete_data(d_name varchar)
language 'plpgsql'
as $$
    BEGIN
        delete from products
            where name = d_name;
    END
$$;

call delete_data('product_2');

-- function delete
create
or replace function f_delete_data(d_count integer)
returns void
language 'plpgsql'
as
$$
    begin
        if d_count > 0 THEN
            delete from products
            where count>d_count;
        end if;
    end;
$$;

select f_delete_data(10);

select * from products;
