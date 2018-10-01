-- Games
CREATE TABLE games (
  id           BIGSERIAL PRIMARY KEY,
  losers_goals INT                       NOT NULL,
  winner1      BIGINT REFERENCES players NOT NULL,
  winner2      BIGINT REFERENCES players NOT NULL,
  loser1       BIGINT REFERENCES players NOT NULL,
  loser2       BIGINT REFERENCES players NOT NULL,
  reported_by  BIGINT REFERENCES players NOT NULL,
  date         TIMESTAMP                 NOT NULL
);

CREATE INDEX index_winner1 ON games (winner1);
CREATE INDEX index_winner2 ON games (winner2);
CREATE INDEX index_loser1 ON games (loser1);
CREATE INDEX index_loser2 ON games (loser2);
CREATE INDEX index_date ON games (date);