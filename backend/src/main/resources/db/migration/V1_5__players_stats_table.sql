-- Players stats
CREATE TABLE players_stats (
  id                     BIGSERIAL PRIMARY KEY,
  player_id              BIGINT REFERENCES players    NOT NULL,
  rating                 DOUBLE PRECISION             NOT NULL DEFAULT 10000.0,
  current_winning_streak INT                          NOT NULL DEFAULT 0,
  current_losses_streak  INT                          NOT NULL DEFAULT 0,
  longest_winning_streak INT                          NOT NULL DEFAULT 0,
  longest_losses_streak  INT                          NOT NULL DEFAULT 0,
  count_wins             INT                          NOT NULL DEFAULT 0,
  count_games            INT                          NOT NULL DEFAULT 0,
  rated                  INT                          NOT NULL DEFAULT 0,
  winning_percentage     DOUBLE PRECISION             NOT NULL DEFAULT 0.0,
  goals_against          INT                          NOT NULL DEFAULT 0,
  goals_for              INT                          NOT NULL DEFAULT 0,
  active                 BOOLEAN                      NOT NULL DEFAULT FALSE
);