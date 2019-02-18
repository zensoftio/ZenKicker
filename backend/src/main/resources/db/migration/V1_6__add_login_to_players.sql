DROP INDEX index_winner1_id;
DROP INDEX index_winner2_id;
DROP INDEX index_loser1_id;
DROP INDEX index_loser2_id;

ALTER TABLE players
  DROP CONSTRAINT players_username_key;

ALTER TABLE players
  ADD COLUMN login VARCHAR UNIQUE;

UPDATE players SET login=username;

ALTER TABLE players
  ALTER COLUMN login SET NOT NULL;

ALTER TABLE players
  RENAME COLUMN username TO full_name;