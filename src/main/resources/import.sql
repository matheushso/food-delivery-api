insert into cozinha (id, nome) values(1, 'Tailandasa');
insert into cozinha (id, nome) values(2, 'Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values('Tuk Tuk Comida', 15, 2);

insert into forma_pagamento (descricao) values('Cartão de crédito');
insert into forma_pagamento (descricao) values('Cartão de débito');
insert into forma_pagamento (descricao) values('Pix');

insert into permissao (nome, descricao) values ('Cadastrar produtos', 'Permite cadastrar produtos');
insert into permissao (nome, descricao) values ('Consultar produtos', 'Permite consultar produtos');


insert into estado (id, nome) values (1, 'Paraná');
insert into estado (id, nome) values (2, 'São Paulo');

insert into cidade (nome, estado_id) values ('Maringá', 1);
insert into cidade (nome, estado_id) values ('Curitiba', 1);
insert into cidade (nome, estado_id) values ('Garulhos', 2);
insert into cidade (nome, estado_id) values ('Campinas', 2);
