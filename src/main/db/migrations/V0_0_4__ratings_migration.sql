CREATE TABLE IF NOT EXISTS ratings
(
    medicine_id numeric(11) NOT NULL,
    account_id numeric(11) NOT NULL,
    rating numeric(5),
    body text,
    PRIMARY KEY (medicine_id, account_id)
);