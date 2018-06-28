import {configuration} from '../config/api';

const base = (rest) => `${configuration.apiPrefix}${rest}`;

const Paths = {
	User: {
		GetCurrent: base('/players/current')
	}
};

export default Paths;
