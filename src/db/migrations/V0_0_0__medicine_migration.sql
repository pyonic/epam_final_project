CREATE TABLE IF NOT EXISTS medicines
(
    id serial NOT NULL,
    title character varying(150),
    description character varying(500),
    price real,
    slug character varying(200),
    need_receipt BOOLEAN DEFAULT FALSE,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id, slug)
);