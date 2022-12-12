CREATE TABLE IF NOT EXISTS accounts
(
    id serial NOT NULL,
    first_name character varying(100),
    last_name character varying(100),
    email character varying(255),
    password TEXT,
    role character varying(255),
    phone character varying(50),
    PRIMARY KEY (id, email)
);
