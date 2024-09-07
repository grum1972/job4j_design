CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);



INSERT INTO customers
VALUES (1, 'Иван', 'ИвановДжон', 25,'Россия'),
       (2, 'Игорь', 'Борисов', 50, 'Израйль'),
       (3, 'Смит', 'Сара', 25, 'США');

       
SELECT * FROM customers
  WHERE age=(SELECT min(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders
VALUES (1, 10, 1),
       (2, 2,2);
       
SELECT * FROM customers       
WHERE id NOT IN (       
                SELECT customer_id FROM orders
                  WHERE amount > 0
                );
