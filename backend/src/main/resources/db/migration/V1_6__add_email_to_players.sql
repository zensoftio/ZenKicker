DROP INDEX index_winner1_id;
DROP INDEX index_winner2_id;
DROP INDEX index_loser1_id;
DROP INDEX index_loser2_id;

ALTER TABLE players
  DROP CONSTRAINT players_username_key;

ALTER TABLE players
  ADD COLUMN email VARCHAR UNIQUE;

UPDATE players SET email=username;

ALTER TABLE players
  ALTER COLUMN email SET NOT NULL;

ALTER TABLE players
  RENAME COLUMN username TO full_name;