import {
  GET_ACTIVE_PLAYERS_SUCCESS,
  GET_ALL_PLAYERS_SUCCESS,
  GET_CURRENT_USER_SUCCESS
} from '../actions/const'

const initState = {
  current: {},
  activePlayers: {},
  players: {}
};

export const user = (state = initState, action) => {
  switch (action.type) {
    case GET_CURRENT_USER_SUCCESS:
      return {...state, current: action.payload};

    case GET_ACTIVE_PLAYERS_SUCCESS:
      return {...state, activePlayers: action.payload};

    case GET_ALL_PLAYERS_SUCCESS:
      return {...state, players: action.payload};

    default:
      return state;
  }
};
