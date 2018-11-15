-- Players relations
CREATE TABLE players_relations (
  id                 BIGSERIAL PRIMARY KEY,
  player_id          BIGINT REFERENCES players NOT NULL,
  partner_id         BIGINT REFERENCES players NOT NULL,
  count_wins         INT                       NOT NULL DEFAULT 0,
  count_games        INT                       NOT NULL DEFAULT 0,
  winning_percentage DOUBLE PRECISION          NOT NULL DEFAULT 0.0,
  UNIQUE (player_id, partner_id)
);