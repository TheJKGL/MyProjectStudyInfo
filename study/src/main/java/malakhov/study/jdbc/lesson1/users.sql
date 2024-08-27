create table if not exists USERS (
    id identity primary key,
    first_name varchar (255) not null,
    last_name varchar (255)
);

truncate table USERS;
insert into USERS (first_name, last_name) values ('Marco', 'Polo');
insert into USERS (first_name, last_name) values ('Lisa', 'Nelson');