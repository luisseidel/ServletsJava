create database testeServlet;

create table if not exists authors(
    id bigserial primary key not null,
    nome varchar(200) not null,
    editora varchar(200) null
);

create table if not exists phone_number(
    id bigserial primary key not null,
    id_author bigint references authors(id),
    number varchar(16) not null
);


insert into authors values (1, 'Teste 1', 'Teste 1');
insert into authors values (2, 'Teste 2', 'Teste 2');
insert into authors values (3, 'Teste 3', 'Teste 3');
insert into authors values (4, 'Teste 4', 'Teste 4');

insert into phone_number values (1, 1, '(55) 9 1111-1111');
insert into phone_number values (2, 1, '(55) 3111-1111');
insert into phone_number values (3, 2, '(55) 2222-2222');
insert into phone_number values (4, 3, '(55) 9 3333-3333');
insert into phone_number values (5, 4, '(68) 9 4444-4444');
