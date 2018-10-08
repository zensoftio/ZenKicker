import {
  APPEND_TO_GAMES_SUCCESS,
  GET_ALL_GAMES_SUCCESS,
  GET_LATEST_GAMES_SUCCESS,
} from '../actions/const'

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
    case GET_ALL_GAMES_SUCCESS:
      return {...state, games: action.payload};

    case APPEND_TO_GAMES_SUCCESS:
      return {...state, games: {totalCount: action.payload.totalCount, list: [...state.games.list, ...action.payload.list]}};

    case GET_LATEST_GAMES_SUCCESS:
      return {...state, latestGames: action.payload};

    default:
      return state;
  }
};
