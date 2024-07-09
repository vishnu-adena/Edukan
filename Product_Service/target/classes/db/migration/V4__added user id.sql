ALTER TABLE productservice.product
    ADD user_id BIGINT;

ALTER TABLE productservice.product
    ALTER COLUMN user_id SET default 1;