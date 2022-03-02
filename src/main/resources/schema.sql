CREATE SCHEMA IF NOT EXISTS test;
CREATE TABLE IF NOT EXISTS test.products(
    id BIGSERIAL NOT NULL,
    name text,
    short_name text,
    specification text,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS test.product_transactions(
    id bigserial NOT NULL,
    operation_type text,
    transaction_date DATE,
    quantity bigint,
    product_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT fk_product
        FOREIGN KEY (product_id) REFERENCES test.products(id)
)