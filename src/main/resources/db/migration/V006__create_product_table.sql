create table product
(
    id            bigint         not null auto_increment,
    name          varchar(80)    not null,
    description   varchar(80)    not null,
    price         decimal(19, 2) not null,
    active        bit            not null,
    restaurant_id bigint         not null,

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

alter table product
    add constraint fk_product_restaurant
        foreign key (restaurant_id) references restaurant (id);