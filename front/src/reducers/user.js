import {
  GET_ACTIVE_PLAYERS_SUCCESS,
  GET_ALL_PLAYERS_SUCCESS,
  GET_CURRENT_USER_SUCCESS,
  GET_PLAYER_SUCCESS
} from '../actions/const'

const initState = {
  current: null,
  activePlayers: {
    list: [],
    totalCount: 0
  },
  players: {
    list: [],
    totalCount: 0
  },
  player: null
};

export const user = (state = initState, action) => {
  switch (action.type) {
    case GET_CURRENT_USER_SUCCESS:
      return {...state, current: action.payload};

    case GET_ACTIVE_PLAYERS_SUCCESS:
      return {...state, activePlayers: action.payload};

    case GET_ALL_PLAYERS_SUCCESS:
      return {...state, players: action.payload};

    case GET_PLAYER_SUCCESS:
      return {...state, player: action.payload};

    default:
      return state;
  }
};
