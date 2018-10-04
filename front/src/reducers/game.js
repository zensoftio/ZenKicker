import {
  GET_ALL_GAMES_SUCCESS,
} from '../actions/const'

const initState = {
  games: null,
};

export const game = (state = initState, action) => {
  switch (action.type) {
    case GET_ALL_GAMES_SUCCESS:
      return {...state, games: action.payload};

    default:
      return state;
  }
};
