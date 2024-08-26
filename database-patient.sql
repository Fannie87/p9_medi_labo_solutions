drop database if exists p9patient;
create database p9patient;
use p9patient;

CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON mediscreen.* TO 'user'@'localhost';


create table patient(
	id int not null auto_increment,
	nom varchar (30),
	prenom varchar (30),
	date_naissance date,
	genre char (1),
	adresse_postale varchar (100),
	numero_telephone varchar (30),
	primary key (id)
);


insert into patient (nom, prenom, date_naissance, genre, adresse_postale, numero_telephone )
values ('TestNone', 'Test', '1966-12-31', 'F', '1 Brookside St', '100-222-3333');

insert into patient (nom, prenom, date_naissance, genre, adresse_postale, numero_telephone )
values ('TestBorderline', 'Test', '1945-06-24', 'M', '2 High St', '200-333-4444');

insert into patient (nom, prenom, date_naissance, genre, adresse_postale, numero_telephone )
values ('TestInDanger', 'Test', '2004-06-18', 'M', '3 Club Road', '300-444-5555');

insert into patient (nom, prenom, date_naissance, genre, adresse_postale, numero_telephone )
values ('TestEarlyOnset', 'Test', '2002-06-28', 'F', '4 Valley Dr', '400-555-6666');
