CREATE TABLE IF NOT EXISTS company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id,name) values (1,'company1');
INSERT INTO company (id,name) values (2,'company2');
INSERT INTO company (id,name) values (3,'company3');
INSERT INTO company (id,name) values (4,'company4');
INSERT INTO company (id,name) values (5,'company5');

INSERT INTO person (id,name,company_id) values (1,'person1',1);
INSERT INTO person (id,name,company_id) values (2,'person2',1);
INSERT INTO person (id,name,company_id) values (3,'person3',1);

INSERT INTO person (id,name,company_id) values (4,'person4',2);
INSERT INTO person (id,name,company_id) values (5,'person5',2);

INSERT INTO person (id,name,company_id) values (6,'person6',3);
INSERT INTO person (id,name,company_id) values (7,'person7',3);
INSERT INTO person (id,name,company_id) values (8,'person8',3);
INSERT INTO person (id,name,company_id) values (9,'person9',4);

INSERT INTO person (id,name,company_id) values (10,'person10',5);
-- задание 1
select p.name as name_person, c.name as company_name from person as p
left join company as c on p.company_id=c.id
where p.company_id<>5;

-- задание 2
select c.name as company_name,count(company_id) as max_person from person as p
left join company as c on p.company_id=c.id
group by c.name,company_id
having count(p.id)=(
	select count(company_id) from person
	group by company_id
	ORDER BY count(company_id) DESC
	LIMIT 1);
	
	


