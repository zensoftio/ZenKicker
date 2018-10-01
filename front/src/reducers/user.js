import {
  GET_CURRENT_USER_SUCCESS
} from '../actions/const'

const initState = {
  current: {}
};

export const user = (state = initState, action) => {
  switch (action.type) {
    case GET_CURRENT_USER_SUCCESS:
      return {current: action.payload};
    default:
      return state;
  }
};
