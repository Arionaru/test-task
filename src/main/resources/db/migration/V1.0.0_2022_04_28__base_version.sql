CREATE TABLE IF NOT EXISTS clan
(
    id   BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR   NOT NULL,
    gold INT       NOT NULL DEFAULT 0
);