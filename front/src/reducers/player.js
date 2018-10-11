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
  player: null
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
      return {...state, player: action.payload};

    case ActionType.Player.APPEND_TO_PLAYERS_SUCCESS:
      return {...state, players: {totalCount: action.payload.totalCount, list: [...state.players.list, ...action.payload.list]}};

    case ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_SUCCESS:
      return {...state, activePlayers: {totalCount: action.payload.totalCount, list: [...state.activePlayers.list, ...action.payload.list]}};

    default:
      return state;
  }
};
