CREATE TABLE account
(
    ID      SERIAL PRIMARY KEY,
    code    INT     NOT NULL,
    balance DECIMAL NOT NULL
);