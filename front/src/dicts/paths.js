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
		GetAll: base('/games')
	},
  Player: {
    GetActive: base('/players/active'),
    GetAll: base('/players'),
    GetPlayer: (id) => base(`/players/${id}`),
  }
};

export default Paths;
