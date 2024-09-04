create table authors(
	id serial primary key,
	author char(30)
);

create table books (
	id serial primary key,
	book char(30),
	author_id int references authors(id)
);

insert into authors(author) values ('author1');
insert into authors(author) values ('author2');

insert into books(book,author_id) values ('book1',1);
insert into books(book,author_id) values ('book2',1);
insert into books(book,author_id) values ('book3',2);

select b.book, a.author
from books as b join authors as a on b.author_id = a.id;

select b.book as Книга, a.author as Автор
from books as b join authors as a on b.author_id = a.id;

select b.book as "Название книги", a.author as Автор
from books as b join authors as a on b.author_id = a.id;

