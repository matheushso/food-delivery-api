create table payment_method
(
    id          bigint      not null auto_increment,
    description varchar(40) not null,

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurant_payment_method
(
    restaurant_id     bigint not null,
    payment_method_id bigint not null,

    primary key (restaurant_id, payment_method_id)
) engine=InnoDB default charset=utf8mb4;

alter table restaurant_payment_method
    add constraint fk_rest_pay_meth_restaurant
        foreign key (restaurant_id) references restaurant (id);

alter table restaurant_payment_method
    add constraint fk_rest_pay_meth_payment_method
        foreign key (payment_method_id) references payment_method (id);