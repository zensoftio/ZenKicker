ALTER TABLE games ADD COLUMN reported BIGINT;

DO $$
DECLARE
  game    RECORD;
BEGIN
  FOR game IN SELECT * FROM games
  LOOP
    UPDATE games SET reported = (SELECT id FROM players WHERE username = game.reported_by);
  END LOOP;
END$$;

ALTER TABLE games DROP COLUMN reported_by;

ALTER TABLE games RENAME COLUMN reported TO reported_by;

ALTER TABLE games ADD CONSTRAINT games_reported_by_id_fkey FOREIGN KEY (reported_by) REFERENCES players (id);