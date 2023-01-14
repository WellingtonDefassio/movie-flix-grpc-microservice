DROP TABLE IF EXISTS movie;

create table movie as select * from CSVREAD('classpath:movie.csv')