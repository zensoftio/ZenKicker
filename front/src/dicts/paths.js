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
    PlayerGames: (id) => base(`/stats/games/player/${id}`),
    GetGamesCount: base('/games/count/lastWeek')
	},
  Player: {
    GetActive: base('/players/active'),
    GetAll: base('/players'),
    GetPlayer: (id) => base(`/stats/player/${id}`),
    GetDeltaStatistic: (id) => base(`/stats/delta/player/${id}/dashboard`),
    GetGamesCountStatistic: (id) => base(`/games/count/player/${id}/dashboard`),
    GetDeltaPlayers: base('/stats/players/delta')
  }
};

export default Paths;
