CREATE TABLE receipt_requests
(
    id serial NOT NULL,
    customer_id numeric(10),
    description text,
    status character varying(150),
    PRIMARY KEY (id)
);