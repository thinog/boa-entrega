create table if not exists customer
(
    id    bigserial    not null primary key,
    name  varchar(150) not null,
    email varchar(150) not null
);

create table if not exists address
(
    id                    bigserial    not null primary key,
    id_customer           bigint       not null,
    street                varchar(255) not null,
    number                varchar(50)  not null,
    complement            varchar(255) null,
    neighborhood          varchar(100) not null,
    city                  varchar(100) not null,
    state                 varchar(50)  not null,
    reference             text null,
    delivery_instructions text null,
    foreign key (id_customer) references customer (id)
);