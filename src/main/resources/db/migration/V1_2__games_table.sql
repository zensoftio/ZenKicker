-- Games
CREATE TABLE games (
  id           BIGSERIAL PRIMARY KEY,
  losers_goals INT                       NOT NULL,
  winner1      BIGINT REFERENCES players NOT NULL,
  winner2      BIGINT REFERENCES players NOT NULL,
  loser1       BIGINT REFERENCES players NOT NULL,
  loser2       BIGINT REFERENCES players NOT NULL,
  reported_by  BIGINT REFERENCES players NOT NULL,
  date         DATE                      NOT NULL
);