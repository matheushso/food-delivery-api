create table purchase_order
(
    id                   bigint         not null auto_increment,
    sub_total            decimal(19, 2) not null,
    freight_rate         decimal(19, 2) not null,
    amount               decimal(19, 2) not null,
    address_zip_code     varchar(8)     not null,
    address_street       varchar(80)    not null,
    address_number       varchar(10)    not null,
    address_complement   varchar(100),
    address_neighborhood varchar(80)    not null,
    address_city_id      bigint         not null,
    order_status         varchar(10)    not null,
    payment_method_id    bigint         not null,
    restaurant_id        bigint         not null,
    user_id              bigint         not null,
    creation_date        datetime(6) not null,
    confirmation_date    datetime(6),
    cancellation_date    datetime(6),
    delivery_date        datetime(6),

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

alter table purchase_order
    add constraint fk_purchase_order_city
        foreign key (address_city_id) references city (id);

alter table purchase_order
    add constraint fk_purchase_order_payment_method
        foreign key (payment_method_id) references payment_method (id);

alter table purchase_order
    add constraint fk_purchase_order_restaurant
        foreign key (restaurant_id) references restaurant (id);

alter table purchase_order
    add constraint fk_purchase_order_user
        foreign key (user_id) references user (id);

create table purchase_order_item
(
    id                bigint         not null auto_increment,
    unit_price        decimal(19, 2) not null,
    total_price       decimal(19, 2) not null,
    quantity          integer        not null,
    note              varchar(255),
    purchase_order_id bigint         not null,
    product_id        bigint         not null,

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

alter table purchase_order_item add constraint fk_purchase_order_item_order
    foreign key (purchase_order_id) references purchase_order (id);

alter table purchase_order_item add constraint fk_purchase_order_item_product
    foreign key (product_id) references product (id);