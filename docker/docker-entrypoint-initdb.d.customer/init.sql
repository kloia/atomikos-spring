CREATE TABLE customer
(
    ID         SERIAL PRIMARY KEY,
    name       TEXT NOT NULL,
    age        INT  NOT NULL,
    account_id BIGINT
);