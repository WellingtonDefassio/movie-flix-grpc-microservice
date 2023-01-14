drop table if exists user_db;

create table user_db as select * from CSVREAD('classpath:user.csv');


-- insert into usuario (login, name, genre) values ('joao123', 'joao', 'TERROR');
-- insert into usuario (login, name, genre) values ('maria123', 'maria', 'COMEDY');