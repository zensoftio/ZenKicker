-- Players
CREATE TABLE players (
  id                     BIGSERIAL PRIMARY KEY,
  username               VARCHAR          NOT NULL UNIQUE,
  password               VARCHAR          NOT NULL,
  rating                 DOUBLE PRECISION NOT NULL,
  active                 BOOLEAN          NOT NULL DEFAULT FALSE,
  icon_name              VARCHAR,
  current_winning_streak INT              NOT NULL DEFAULT 0,
  current_losses_streak  INT              NOT NULL DEFAULT 0,
  longest_winning_streak INT              NOT NULL DEFAULT 0,
  longest_losses_streak  INT              NOT NULL DEFAULT 0
);