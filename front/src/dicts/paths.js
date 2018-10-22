import {configuration} from '../config/api';

const base = (rest) => `${configuration.apiPrefix}${rest}`;

const Paths = {
	User: {
		GetCurrent: base('/players/current'),
    UpdateUsername: base(`/players/username`),
    UpdatePassword: base(`/players/password`),
    UpdatePhoto: base(`/players/icon`)
	},
	Game: {
		RegisterGame: base('/games/registration'),
		GetAll: base('/games'),
    PlayerGames: (id) => base(`/games/player/${id}`),
    GetGamesCount: (id, count) => base(`/games/count/player/${id}/weeksAgo/${count}`)
	},
  Player: {
    GetActive: base('/players/active'),
    GetAll: base('/players'),
    GetPlayer: (id) => base(`/players/${id}`),
  }
};

export default Paths;
