ALTER TABLE players_relations DROP CONSTRAINT IF EXISTS players_relations_player_id_partner_id_key;

ALTER TABLE players_relations
  ADD COLUMN game_id BIGINT NOT NULL REFERENCES games (id) DEFAULT 1;

UPDATE players_relations
SET game_id = (SELECT id FROM games ORDER BY id DESC LIMIT 1);

ALTER TABLE players_stats
  ADD COLUMN game_id BIGINT REFERENCES games (id);

UPDATE players_stats
SET game_id = (SELECT id FROM games ORDER BY id DESC LIMIT 1);
