create database education;
use education;

-- Creating table block
	-- Subject table
create table subject (
	id int(10) not null auto_increment primary key,
    name varchar(50) not null,
    descr mediumtext not null,
    valid  bit not null,
    date_of_cretion date not null
);

-- Test of exist table
	-- Subject table 
select * from subject;
select * from subject where id = 100;