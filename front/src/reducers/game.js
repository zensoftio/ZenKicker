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
};

export const game = (state = initState, action) => {
  switch (action.type) {
    case ActionType.Game.GET_ALL_GAMES_SUCCESS:
      return {...state, games: action.payload};

    case ActionType.Game.APPEND_TO_GAMES_SUCCESS:
      return {...state, games: {totalCount: action.payload.totalCount, list: [...state.games.list, ...action.payload.list]}};

    case ActionType.Game.GET_LATEST_GAMES_SUCCESS:
      return {...state, latestGames: action.payload};

    default:
      return state;
  }
};
