create table if not exists api_key
(
    id          bigserial    not null primary key,
    supplier_id bigint       not null,
    name        varchar(150) not null,
    hash        varchar(255) not null,
    access_type varchar(50)  not null,
    created_at  timestamp    not null default CURRENT_TIMESTAMP,
    active      boolean      not null default TRUE
);

create table if not exists warehouse
(
    id           bigserial    not null primary key,
    supplier_id  bigint       not null,
    name         varchar(150) not null,
    street       varchar(255) not null,
    number       varchar(50)  not null,
    complement   varchar(255) null,
    neighborhood varchar(100) not null,
    city         varchar(100) not null,
    state        varchar(50)  not null,
    zip_code     varchar(50)  not null,
    open_hours   varchar(255) null,
    created_at   timestamp    not null default CURRENT_TIMESTAMP,
    active       boolean      not null default TRUE
);