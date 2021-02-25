-- CREATE TABLE account
-- (
--     ID      SERIAL PRIMARY KEY,
--     code    INT     NOT NULL,
--     balance DECIMAL NOT NULL
-- );
CREATE TABLE account
(
    ID      NUMBER(19,0) PRIMARY KEY,
    code    NUMBER(19,0)     NOT NULL,
    balance DECIMAL NOT NULL
);
CREATE SEQUENCE account_id_seq START WITH 1;