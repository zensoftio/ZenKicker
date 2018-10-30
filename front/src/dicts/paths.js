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
    PlayerGames: (id) => base(`/stats/games/player/${id}`)
	},
  Player: {
    GetActive: base('/players/active'),
    GetAll: base('/players'),
    GetPlayer: (id) => base(`/stats/player/${id}`),
    GetDeltaStatistic: (id, countOfWeeks) => base(`/stats/delta/player/${id}/dashboard/countWeeks/${countOfWeeks}`),
    GetGamesCountStatistic: (id, count) => base(`/games/count/player/${id}/dashboard/countWeeks/${count}`)
  }
};

export default Paths;
