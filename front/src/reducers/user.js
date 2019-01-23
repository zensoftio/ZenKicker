import {ActionType} from '../actions/const';

const initState = {
  current: null,
};

export const user = (state = initState, action) => {
  switch (action.type) {
    case ActionType.User.GET_CURRENT_USER_SUCCESS:
      return {...state, current: action.payload};

    case ActionType.User.GET_CURRENT_USER_FAILURE:
      return {...state, current: null};

    default:
      return state;
  }
};
