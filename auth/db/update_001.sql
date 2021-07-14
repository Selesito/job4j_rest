create table person (
    id serial primary key not null,
    login varchar(2000),
    password varchar(2000)
);

create table employee (
    id serial primary key not null,
    firstname varchar(2000),
    surname varchar(2000),
    inn integer,
    created timestamp without time zone not null default now(),
    persons varchar(2000)
);

create table employee_person (
    employee_id INT REFERENCES employee(id),
    person_id INT REFERENCES person(id)
);

insert into person (login, password) values ('parsentev', '123');
insert into person (login, password) values ('ban', '123');
insert into person (login, password) values ('ivan', '123');
insert into person (login, password) values ('petr', '123');

insert into employee (firstname, surname, inn) values ('Петр', 'Арсен', 11223344);
insert into employee (firstname, surname, inn) values ('Виталий', 'Мор', 11223344);

insert into employee_person (employee_id, person_id) values (1 , 1);
insert into employee_person (employee_id, person_id) values (1 , 2);
insert into employee_person (employee_id, person_id) values (2 , 3);
insert into employee_person (employee_id, person_id) values (2 , 4);

