
insert into roles(role) values ('role1');
insert into roles(role) values ('role2');

insert into rules(rule) values ('rule1');
insert into rules(rule) values ('rule2');

insert into roles_rules(role_id,rule_id) values (1,1);
insert into roles_rules(role_id,rule_id) values (1,2);
insert into roles_rules(role_id,rule_id) values (2,1);

insert into users(name,lastname,role_id) values ('Ivanov','Ivan',1);
insert into users(name,lastname,role_id) values ('Borisov','Boris',2);

insert into categories(category) values ('category1');
insert into categories(category) values ('category2');

insert into states(state) values ('state1');
insert into states(state) values ('state2');

insert into items(item,user_id,category_id,state_id) values (10,1,1,1);
insert into items(item,user_id,category_id,state_id) values (11,2,2,2);

insert into comments(comment,item_id) values ('comment1',10);
insert into comments(comment,item_id) values ('comment2',10);
insert into comments(comment,item_id) values ('comment3',11);
insert into comments(comment,item_id) values ('comment4',11);

insert into attachs(file,item_id) values ('file1',10);
insert into attachs(file,item_id) values ('file2',10);
insert into attachs(file,item_id) values ('file3',11);
