CREATE SEQUENCE entregas_id_seq;

CREATE TABLE entregas (
                          id INTEGER NOT NULL DEFAULT nextval('entregas_id_seq'),
                          destinatario_nome VARCHAR(100) NOT NULL,
                          destinatario_logradouro VARCHAR(100) NOT NULL,
                          destinatario_numero VARCHAR(10),
                          destinatario_complemento VARCHAR(100),
                          destinatario_bairro VARCHAR(60) NOT NULL,
                          destinatario_cep VARCHAR(20) NOT NULL,
                          status_entrega VARCHAR(20) NOT NULL,
                          taxa DECIMAL(8, 2) NOT NULL,
                          data_pedido TIMESTAMP NOT NULL,
                          data_finalizacao TIMESTAMP,
                          cliente_id INTEGER NOT NULL
);

ALTER TABLE entregas ADD CONSTRAINT entregas_pk PRIMARY KEY (id);
ALTER TABLE entregas ADD CONSTRAINT cliente_fk FOREIGN KEY (cliente_id) REFERENCES clientes(id);