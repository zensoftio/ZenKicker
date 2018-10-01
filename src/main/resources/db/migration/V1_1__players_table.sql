-- Players
CREATE TABLE players (
  id       BIGSERIAL PRIMARY KEY,
  username VARCHAR          NOT NULL UNIQUE,
  password VARCHAR          NOT NULL,
  rating   DOUBLE PRECISION NOT NULL,
  active   BOOLEAN          NOT NULL DEFAULT FALSE
);