import Paths from "../dicts/paths";
import {api} from "../config/api";
import {ActionType} from './const';
import {authenticated, unauthenticated} from "./authentication";

export const getCurrent = () => {
	return async (dispatch) => {

		dispatch({type: ActionType.User.GET_CURRENT_USER_REQUEST});

		try {
			const result = await api.get(Paths.User.GetCurrent);
			dispatch({type: ActionType.User.GET_CURRENT_USER_SUCCESS, payload: result.data});
      authenticated(dispatch);
		} catch (err) {
			dispatch({type: ActionType.User.GET_CURRENT_USER_FAILURE});
      if (err.response.status === 401) {
        unauthenticated(dispatch);
      }
		}
	}
}

export const updateUsername = async (data) => {
  return await api.put(Paths.User.UpdateUsername, data);
}
export const updatePassword = async (data) => {
  return await api.put(Paths.User.UpdatePassword, data);
}
export const updatePhoto = async (data) => {
  return await api.put(Paths.User.UpdatePhoto, data);
}