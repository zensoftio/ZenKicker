import {ActionType} from '../actions/const';

const initState = {
  activePlayers: {
    list: [],
    totalCount: 0
  },
  players: {
    list: [],
    totalCount: 0
  },
  topPlayers: {
    list: [],
    totalCount: 0
  },
  player: null,
  deltaStatistic: null,
  gamesCountStatistic: null
};

export const player = (state = initState, action) => {
  switch (action.type) {
    case ActionType.Player.GET_ACTIVE_PLAYERS_SUCCESS:
      return {...state, activePlayers: action.payload};

    case ActionType.Player.GET_ALL_PLAYERS_SUCCESS:
      return {...state, players: action.payload};

    case ActionType.Player.GET_TOP_PLAYERS_SUCCESS:
      return {...state, topPlayers: action.payload};

    case ActionType.Player.GET_PLAYER_SUCCESS:
      const player = { ...action.payload.player, ...action.payload};
      delete player['player'];
      return {...state, player: player};

    case ActionType.Player.GET_PLAYER_DELTA_STATISTIC_SUCCESS:
      return {...state, deltaStatistic: action.payload};

    case ActionType.Player.GET_PLAYER_GAMES_COUNT_STATISTIC_SUCCESS:
      return {...state, gamesCountStatistic: action.payload};

    case ActionType.Player.APPEND_TO_PLAYERS_SUCCESS:
      return {...state, players: {totalCount: action.payload.totalCount, list: [...state.players.list, ...action.payload.list]}};

    case ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_SUCCESS:
      return {...state, activePlayers: {totalCount: action.payload.totalCount, list: [...state.activePlayers.list, ...action.payload.list]}};

    default:
      return state;
  }
};
