-- schema.sql (pure structure)

--DROP TABLE IF EXISTS offer CASCADE;
DROP TABLE IF EXISTS product CASCADE;
--DROP TABLE IF EXISTS category CASCADE;

--CREATE TABLE category (
--    id SERIAL PRIMARY KEY,
--    name VARCHAR(255) NOT NULL UNIQUE,
--    description TEXT,
--    created_at TIMESTAMPTZ NOT NULL,
--    updated_at TIMESTAMPTZ NOT NULL
--);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    stock INTEGER NOT NULL,
    -- category_id INT REFERENCES category(id) ON DELETE SET NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

--CREATE TABLE offer (
--    id SERIAL PRIMARY KEY,
--    title VARCHAR(255) NOT NULL,
--    description TEXT,
--    discount_percent NUMERIC(5, 2),
--    start_date DATE NOT NULL,
--    end_date DATE NOT NULL,
--    product_id INT NOT NULL REFERENCES product(id) ON DELETE CASCADE,
--    created_at TIMESTAMPTZ NOT NULL,
--    updated_at TIMESTAMPTZ NOT NULL
--);

