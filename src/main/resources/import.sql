insert into kitchen (id, nome) values(1, 'Tailandasa');
insert into kitchen (id, nome) values(2, 'Indiana');

insert into restaurant (nome, taxa_frete, cozinha_id) values('Thai Gourmet', 10, 1);
insert into restaurant (nome, taxa_frete, cozinha_id) values('Thai Delivery', 9.50, 1);
insert into restaurant (nome, taxa_frete, cozinha_id) values('Tuk Tuk Comida', 15, 2);

insert into payment_method (descricao) values('Cartão de crédito');
insert into payment_method (descricao) values('Cartão de débito');
insert into payment_method (descricao) values('Pix');

insert into permission (nome, descricao) values ('Cadastrar produtos', 'Permite cadastrar produtos');
insert into permission (nome, descricao) values ('Consultar produtos', 'Permite consultar produtos');


insert into state (id, nome) values (1, 'Paraná');
insert into state (id, nome) values (2, 'São Paulo');

insert into city (nome, estado_id) values ('Maringá', 1);
insert into city (nome, estado_id) values ('Curitiba', 1);
insert into city (nome, estado_id) values ('Garulhos', 2);
insert into city (nome, estado_id) values ('Campinas', 2);
