create table schedule
(
    id       bigint auto_increment
        primary key,
    work     varchar(500) not null,
    name     varchar(255) not null,
    password varchar(500) not null,
    date1    varchar(500) not null,
    date2    varchar(500) not null
);