create table state
(
    id   bigint      not null auto_increment,
    name varchar(80) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table city
(
    id       bigint      not null auto_increment,
    name     varchar(80) not null,
    state_id bigint      not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

alter table city
    add constraint fk_city_state
        foreign key (state_id) references state (id);