ALTER TABLE players ADD COLUMN password VARCHAR NOT NULL DEFAULT '';

ALTER TABLE players ALTER COLUMN password DROP DEFAULT;