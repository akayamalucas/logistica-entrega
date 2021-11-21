CREATE SCHEMA IF NOT EXISTS logistica;

CREATE SEQUENCE clientes_id_seq;

CREATE TABLE clientes (
                          id INTEGER NOT NULL DEFAULT nextval('clientes_id_seq'),
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR (60),
                          telefone VARCHAR (15),
                          CONSTRAINT clientes_pk PRIMARY KEY (id)
);