create database teste;

create table if not exists authors(
    id bigserial primary key not null,
    nome varchar(200) not null,
    editora varchar(200) null,
    phone_number bigint references phone_number(id)
);

create table if not exists phone_number(
    id bigserial primary key not null,
    number varchar(16) not null
)

insert into phone_number values (1, '(55) 9 8800-1122');
insert into phone_number values (2, '(55) 0202-3311');

insert into authors values (1, 'Teste 1', 'Teste 1', 1);
insert into authors values (2, 'Teste 2', 'Teste 2', 2);
insert into authors values (3, 'Teste 3', 'Teste 3', null);
insert into authors values (4, 'Teste 4', 'Teste 4', null);