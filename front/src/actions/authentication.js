import {ActionType} from "./const";

export const authenticated = (dispatch) => dispatch({type: ActionType.Authentication.LOGIN, payload: true});
export const unauthenticated = (dispatch) => dispatch({type: ActionType.Authentication.LOGIN, payload: false});