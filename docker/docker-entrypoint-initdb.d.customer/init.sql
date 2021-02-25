-- CREATE TABLE customer
-- (
--     ID         SERIAL PRIMARY KEY,
--     name       TEXT NOT NULL,
--     age        INT  NOT NULL,
--     account_id BIGINT
-- );
CREATE TABLE customer
(
    ID         NUMBER(19,0) PRIMARY KEY,
    NAME       VARCHAR(256) NOT NULL,
    AGE        NUMBER(19,0)  NOT NULL,
    ACCOUNT_ID NUMBER(19,0)
);
CREATE SEQUENCE customer_id_seq START WITH 1;