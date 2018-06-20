ALTER TABLE players
  ADD COLUMN current_rating DOUBLE PRECISION NOT NULL DEFAULT 10000;

-- dashboard_rating
CREATE TABLE dashboard_rating (
  id        BIGSERIAL PRIMARY KEY,
  player_id BIGINT REFERENCES players    NOT NULL,
  delta     DOUBLE PRECISION             NOT NULL,
  week_ago  INT                          NOT NULL DEFAULT 0
);