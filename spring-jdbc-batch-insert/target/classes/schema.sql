CREATE TABLE IF NOT EXISTS product (
    id              SERIAL PRIMARY KEY,
    title           VARCHAR(40),
    created_ts      timestamp without time zone,
    price           numeric
);
TRUNCATE TABLE product;