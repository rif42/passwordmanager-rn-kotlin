-- CREATE TABLE IF NOT EXISTS messages (
-- id       VARCHAR(60)  PRIMARY KEY,
-- text     VARCHAR      NOT NULL
-- );

CREATE TABLE IF NOT EXISTS books (
id       VARCHAR(60)  PRIMARY KEY,
title     VARCHAR(60)   NOT NULL,
author    VARCHAR(60)   NOT NULL,
quantity   INT          NOT NULL 
);