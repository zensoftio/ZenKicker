import {ActionType} from '../actions/const';

const initState = {
  isLogin: null
};

export const authentication = (state = initState, action) => {
  switch (action.type) {
    case ActionType.Authentication.LOGIN:
      return {...state, isLogin: action.payload};

    default:
      return state;
  }
};
