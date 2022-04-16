-- CUSTOMER
insert into customer (id, name, email, active)
values (1, 'Jeremias Silva', 'jeremias@email-provider.com', TRUE);

insert into customer (id, name, email, active)
values (2, 'Joaquim Pereira', 'joaquim@email-provider.com', TRUE);

-- ADDRESSES
insert into address (id, customer_id, street, number, complement, neighborhood, city, state, zip_code, reference, delivery_instructions)
values (1, 1, 'Rua Abc', '12345', 'Ap 2', 'Vila Def', 'São Paulo', 'SP', '01234-567', 'Perto do petshop', 'deixar com o porteiro');

insert into address (id, customer_id, street, number, complement, neighborhood, city, state, zip_code, reference, delivery_instructions)
values (2, 2, 'Rua Abc', '123', 'Ap 2', 'Vila Def', 'São Paulo', 'SP', '01234-567', 'Perto do petshop', 'deixar com o porteiro');
