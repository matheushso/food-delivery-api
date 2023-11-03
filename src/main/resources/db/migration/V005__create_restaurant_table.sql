create table restaurant
(
    id                   bigint         not null auto_increment,
    name                 varchar(80)    not null,
    freight_rate         decimal(19, 2) not null,
    kitchen_id           bigint         not null,
    address_zip_code     varchar(8)     not null,
    address_street       varchar(80)    not null,
    address_number       varchar(10)    not null,
    address_complement   varchar(100),
    address_neighborhood varchar(80)    not null,
    address_city_id      bigint         not null,
    creation_date        datetime(6) not null,
    update_date          datetime(6),

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

alter table restaurant
    add constraint fk_restaurant_kitchen
        foreign key (kitchen_id) references kitchen (id);

alter table restaurant
    add constraint fk_restaurant_city
        foreign key (address_city_id) references city (id);