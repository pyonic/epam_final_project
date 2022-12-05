CREATE TABLE IF NOT EXISTS orders
(
    id serial NOT NULL,
    medicine_id numeric(5) NOT NULL,
    customer_id numeric(5) NOT NULL,
    amount real,
    dosage real,
    price real NOT NULL,
    delivery_address character varying(255),
    status character varying(15) DEFAULT 'NEW',
    PRIMARY KEY (id)
);