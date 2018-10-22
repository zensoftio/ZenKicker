import {ActionType} from '../actions/const';

const initState = {
  games: {
    list: [],
    totalCount: 0
  },
  latestGames: {
    list: [],
    totalCount: 0
  },
  playerGames: {
    list: [],
    totalCount: 0
  },
  gamesCount: null
};

export const game = (state = initState, action) => {
  switch (action.type) {
    case ActionType.Game.GET_ALL_GAMES_SUCCESS:
      return {...state, games: action.payload};

    case ActionType.Game.APPEND_TO_GAMES_SUCCESS:
      return {...state, games: {totalCount: action.payload.totalCount, list: [...state.games.list, ...action.payload.list]}};

    case ActionType.Game.GET_LATEST_GAMES_SUCCESS:
      return {...state, latestGames: action.payload};

    case ActionType.Game.GET_PLAYER_GAMES_SUCCESS:
      return {...state, playerGames: action.payload};

    case ActionType.Game.APPEND_TO_PLAYER_GAMES_SUCCESS:
      return {...state, playerGames: {totalCount: action.payload.totalCount, list: [...state.playerGames.list, ...action.payload.list]}};

    case ActionType.Game.GET_GAMES_COUNT_PER_WEEKS_SUCCESS:
      return {...state, gamesCount: action.payload};

    default:
      return state;
  }
};
