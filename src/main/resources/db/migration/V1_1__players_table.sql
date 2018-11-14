-- Players
CREATE TABLE players (
  id                  BIGSERIAL PRIMARY KEY,
  username            VARCHAR          NOT NULL UNIQUE,
  password            VARCHAR          NOT NULL,
  rating              DOUBLE PRECISION NOT NULL,
  active              BOOLEAN          NOT NULL DEFAULT FALSE,
  icon_name           VARCHAR,
  current_win_streak  INT              NOT NULL DEFAULT 0,
  current_loss_streak INT              NOT NULL DEFAULT 0,
  longest_win_streak  INT              NOT NULL DEFAULT 0,
  longest_loss_streak INT              NOT NULL DEFAULT 0
);