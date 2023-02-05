insert into kitchen (id, name) values(1, 'Tailandasa');
insert into kitchen (id, name) values(2, 'Indiana');

insert into restaurant (name, freight_rate, kitchen_id) values('Thai Gourmet', 10, 1);
insert into restaurant (name, freight_rate, kitchen_id) values('Thai Delivery', 9.50, 1);
insert into restaurant (name, freight_rate, kitchen_id) values('Tuk Tuk Comida', 15, 2);

insert into payment_method (description) values('Cartão de crédito');
insert into payment_method (description) values('Cartão de débito');
insert into payment_method (description) values('Pix');

insert into permission (name, description) values ('Cadastrar produtos', 'Permite cadastrar produtos');
insert into permission (name, description) values ('Consultar produtos', 'Permite consultar produtos');


insert into state (id, name) values (1, 'Paraná');
insert into state (id, name) values (2, 'São Paulo');

insert into city (name, state_id) values ('Maringá', 1);
insert into city (name, state_id) values ('Curitiba', 1);
insert into city (name, state_id) values ('Garulhos', 2);
insert into city (name, state_id) values ('Campinas', 2);
