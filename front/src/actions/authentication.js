import {ActionType} from "./const";

export const authenticated = (dispatch) => dispatch({type: ActionType.Authentication.IS_LOGIN, payload: true});
export const unauthenticated = (dispatch) => dispatch({type: ActionType.Authentication.IS_LOGIN, payload: false});