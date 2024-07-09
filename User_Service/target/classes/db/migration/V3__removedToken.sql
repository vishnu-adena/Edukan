ALTER TABLE token
    DROP CONSTRAINT fk_token_on_user;

DROP TABLE token CASCADE;