create table currencies(
    id bigint not null auto_increment,
    reference int,
    name varchar(50),
    rate decimal(12, 4),
    currency_code varchar(25),
    exchange_date date,
    constraint currencies_pk primary key (id)
);

create table spending_limits(
    id bigint not null auto_increment,
    constraint spending_limits_pk primary key (id)
);

create table transactions(
    id bigint not null auto_increment,
    constraint transactions_pk primary key (id)
);