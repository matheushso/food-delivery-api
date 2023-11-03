create table user
(
    id            bigint       not null auto_increment,
    name          varchar(80)  not null,
    email         varchar(80)  not null,
    password      varchar(200) not null,
    creation_date datetime(6) not null,

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table group_access
(
    id   bigint      not null auto_increment,
    nome varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table user_group_access
(
    user_id         bigint not null,
    group_access_id bigint not null,

    primary key (user_id, group_access_id)
) engine=InnoDB default charset=utf8mb4;

alter table user_group_access
    add constraint fk_usr_grp_access_user
        foreign key (user_id) references user (id);

alter table user_group_access
    add constraint fk_usr_grp_access_group_access
        foreign key (group_access_id) references group_access (id);