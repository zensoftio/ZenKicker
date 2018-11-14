-- Award Degrees
CREATE TABLE award_degrees (
  id  INT PRIMARY KEY,
  key VARCHAR NOT NULL UNIQUE
);

INSERT INTO award_degrees (id, key) VALUES (1, 'GOLD');
INSERT INTO award_degrees (id, key) VALUES (2, 'SILVER');
INSERT INTO award_degrees (id, key) VALUES (3, 'BRONZE');

-- Award Types
CREATE TABLE award_types (
  id  INT PRIMARY KEY,
  key VARCHAR NOT NULL UNIQUE
);

INSERT INTO award_types (id, key) VALUES (1, 'WEEK_END_OVERALL_RATING');
INSERT INTO award_types (id, key) VALUES (2, 'WEEK_MAX_RATING_DELTA');
INSERT INTO award_types (id, key) VALUES (3, 'TOURNAMENT');

-- Awards
CREATE TABLE awards (
  id              BIGSERIAL PRIMARY KEY,
  player_id       BIGINT REFERENCES players    NOT NULL,
  award_type_id   INT REFERENCES award_types   NOT NULL,
  award_degree_id INT REFERENCES award_degrees NOT NULL,
  description     VARCHAR                      NOT NULL
);