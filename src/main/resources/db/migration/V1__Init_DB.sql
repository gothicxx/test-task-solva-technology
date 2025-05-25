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
    expense_category varchar(50),
    amount decimal(19,2),
    limit_date date,
    constraint spending_limits_pk primary key (id)
);

create table transactions(
    id bigint not null auto_increment,
    account_from bigint,
    account_to bigint,
    currency_shortname varchar(10),
    tx_sum decimal(19,2),
    expense_category varchar(50),
    date_time date,
    limit_exceeded boolean,
    constraint transactions_pk primary key (id)
);

--create index idx_transactions_category_date
--    on transactions (expense_category, date_time);
--
--create index idx_spending_limits_category_date
--    on spending_limits (expense_category, limit_date);