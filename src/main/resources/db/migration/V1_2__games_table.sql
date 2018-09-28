-- Games
CREATE TABLE games (
  id          BIGSERIAL PRIMARY KEY,
  date        DATE                      NOT NULL,
  score       VARCHAR                   NOT NULL,
  reported_by BIGINT REFERENCES players NOT NULL
);