CREATE SEQUENCE movimentos_id_seq;

CREATE TABLE movimentos (
    id INTEGER NOT NULL DEFAULT nextval('movimentos_id_seq'),
    descricao TEXT,
    data_registro TIMESTAMP NOT NULL,
    entrega_id INTEGER NOT NULL
);

ALTER TABLE movimentos ADD CONSTRAINT movimentos_pk PRIMARY KEY (id);
ALTER TABLE movimentos ADD CONSTRAINT entrega_fk FOREIGN KEY (entrega_id) REFERENCES entregas(id);