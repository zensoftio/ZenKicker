-- Games
CREATE TABLE games (
  id           BIGSERIAL PRIMARY KEY,
  losers_goals INT                       NOT NULL,
  winner1_id   BIGINT REFERENCES players NOT NULL,
  winner2_id   BIGINT REFERENCES players NOT NULL,
  loser1_id    BIGINT REFERENCES players NOT NULL,
  loser2_id    BIGINT REFERENCES players NOT NULL,
  reported_by  BIGINT REFERENCES players NOT NULL,
  date         TIMESTAMP                 NOT NULL
);

CREATE INDEX index_winner1_id
  ON games (winner1_id);
CREATE INDEX index_winner2_id
  ON games (winner2_id);
CREATE INDEX index_loser1_id
  ON games (loser1_id);
CREATE INDEX index_loser2_id
  ON games (loser2_id);
CREATE INDEX index_date
  ON games (date);