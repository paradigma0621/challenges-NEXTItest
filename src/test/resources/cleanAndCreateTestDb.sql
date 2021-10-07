DROP DATABASE testNEXTIdb;
CREATE DATABASE IF NOT EXISTS testNEXTIdb;
USE testNEXTIdb;
create table cliente (id integer not null auto_increment, cpf varchar(255), data_nascimento datetime(6), nome varchar(255), primary key (id));
create table pedido (id integer not null auto_increment, data_compra datetime(6), total_da_compra double precision not null, cliente_id integer, primary key (id));
create table produto (id integer not null auto_increment, descricao varchar(255), nome varchar(255), preco double precision, quantidade integer, pedido_id integer, primary key (id));

