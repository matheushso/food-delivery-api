insert into kitchen (id, name) values(1, 'Tailandesa');
insert into kitchen (id, name) values(2, 'Indiana');

insert into state (id, name) values (1, 'Paraná');
insert into state (id, name) values (2, 'São Paulo');

insert into city (name, state_id) values ('Maringá', 1);
insert into city (name, state_id) values ('Curitiba', 1);
insert into city (name, state_id) values ('Garulhos', 2);
insert into city (name, state_id) values ('Campinas', 2);

insert into restaurant (name, freight_rate, kitchen_id, creation_date, update_date, address_city_id, address_zip_code, address_street, address_number, address_neighborhood) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (name, freight_rate, kitchen_id, creation_date, update_date) values('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (name, freight_rate, kitchen_id, creation_date, update_date) values('Tuk Tuk Comida', 15, 2, utc_timestamp, utc_timestamp);

insert into payment_method (description) values('Cartão de crédito');
insert into payment_method (description) values('Cartão de débito');
insert into payment_method (description) values('Pix');

insert into permission (name, description) values ('Cadastrar produtos', 'Permite cadastrar produtos');
insert into permission (name, description) values ('Consultar produtos', 'Permite consultar produtos');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into product (name, description, price, active, restaurant_id) values ('Massa Carbonara', 'Uma massa clássica italiana com bacon, ovos e queijo.', 15.99, true, 1);
insert into product (name, description, price, active, restaurant_id) values ('Sushi Combo', 'Uma seleção de sushis variados com acompanhamentos.', 22.99, true, 2);
insert into product (name, description, price, active, restaurant_id) values ('Pizza Margherita', 'Uma pizza tradicional com molho de tomate, muçarela e manjericão.', 14.99, true, 2);
insert into product (name, description, price, active, restaurant_id) values ('Hambúrguer Gourmet', 'Um hambúrguer suculento com ingredientes gourmet.', 12.99, true, 3);
insert into product (name, description, price, active, restaurant_id) values ('Salada Caesar', 'Uma salada refrescante com alface, croutons e molho Caesar.', 8.99, true, 3);