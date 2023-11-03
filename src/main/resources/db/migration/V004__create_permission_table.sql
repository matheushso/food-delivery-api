create table permission
(
    id          bigint      not null auto_increment,
    name        varchar(80) not null,
    description varchar(80) not null,

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table group_access_permission
(
    group_access_id bigint not null,
    permission_id   bigint not null,

    primary key (group_access_id, permission_id)
) engine=InnoDB default charset=utf8mb4;

alter table group_access_permission
    add constraint fk_grp_access_per_group_access
        foreign key (group_access_id) references group_access (id);

alter table group_access_permission
    add constraint fk_grp_access_per_permission
        foreign key (permission_id) references permission (id);