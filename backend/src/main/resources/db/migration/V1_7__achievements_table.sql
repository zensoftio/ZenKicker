CREATE TABLE achievement_types (
  id  INT PRIMARY KEY,
  key VARCHAR NOT NULL UNIQUE
);

INSERT INTO achievement_types (id, key)
VALUES (1, 'WEEK_END_OVERALL_RATING');

CREATE TABLE achievement_levels (
  id  INT PRIMARY KEY,
  key VARCHAR NOT NULL UNIQUE
);

INSERT INTO achievement_levels (id, key)
VALUES (1, 'GOLD'),
       (2, 'SILVER'),
       (3, 'BRONZE');

CREATE TABLE achievements (
  id                   BIGSERIAL PRIMARY KEY,
  player_id            BIGINT    NOT NULL REFERENCES players,
  achievement_type_id  INT       NOT NULL REFERENCES achievement_types,
  achievement_level_id INT       NOT NULL REFERENCES achievement_levels,
  date                 TIMESTAMP NOT NULL
);