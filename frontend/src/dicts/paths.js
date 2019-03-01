import {configuration} from '../config/api';

const base = (rest) => `${configuration.apiPrefix}${rest}`;

const Paths = {
  User: {
    GetCurrent: base('/players/current'),
    UpdateFullName: base(`/players/fullName`),
    UpdateEmail: base(`/players/email`),
    UpdatePassword: base(`/players/password`),
    UpdatePhoto: base(`/players/icon`)
  },
  Game: {
    RegisterGame: base('/games/registration'),
    GetAll: base('/games'),
    GetGamesCount: base('/games/count/lastWeek'),
    GetGamesCountStatistic: (id) => base(`/games/count/player/${id}/dashboard`),
  },
  Player: {
    GetActive: base('/players/active/stats'),
    GetRelations: (id) => base(`/players/${id}/relations`),
    GetRelationsDashboard: (id) => base(`/players/${id}/relations/dashboard`),
    GetAll: base('/players/stats'),
    GetPlayersDashboard: base('/players/dashboard'),
    GetPlayer: (id) => base(`/players/${id}/stats`),
    GetDeltaStatistic: (id) => base(`/players/${id}/delta/dashboard`),
    PlayerGames: (id) => base(`/players/${id}/games`),
    SearchPlayer: (keyword) => base(`/players/search/${keyword}`),
    GetAcheivements: (id) => base(`/players/${id}/achievements`)
  }
};

export default Paths;
