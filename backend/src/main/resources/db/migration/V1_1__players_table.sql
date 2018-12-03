-- Players
CREATE TABLE players (
  id        BIGSERIAL PRIMARY KEY,
  username  VARCHAR NOT NULL UNIQUE,
  password  VARCHAR NOT NULL,
  icon_path VARCHAR
);