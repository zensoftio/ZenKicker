import {configuration} from '../config/api';

const base = (rest) => `${configuration.apiPrefix}${rest}`;

const Paths = {
	User: {
		GetCurrent: base('/players/current'),
		GetActive: base('/players/active'),
		GetAll: base('/players'),
		GetPlayer: (id) => base(`/players/${id}`),
    UpdateUsername: base(`/players/username`),
    UpdatePassword: base(`/players/password`)
	}
};

export default Paths;
