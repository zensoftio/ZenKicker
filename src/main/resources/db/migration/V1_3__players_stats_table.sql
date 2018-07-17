-- Players stats
CREATE TABLE players_stats (
  id        BIGSERIAL PRIMARY KEY,
  player_id BIGINT REFERENCES players    NOT NULL,
  game_id   BIGINT REFERENCES games      NOT NULL,
  won       BOOLEAN                      NOT NULL,
  delta     DOUBLE PRECISION             NOT NULL
);