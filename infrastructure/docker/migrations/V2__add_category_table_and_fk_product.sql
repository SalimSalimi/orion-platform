DROP TABLE IF EXISTS category CASCADE;

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);


ALTER TABLE product
ADD COLUMN id_category INT references category(id) ON DELETE CASE;
