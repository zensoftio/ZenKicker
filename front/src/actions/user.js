import {
  GET_CURRENT_USER_REQUEST, GET_CURRENT_USER_SUCCESS, GET_CURRENT_USER_FAILURE
} from "./const";
import Paths from "../dicts/paths";
import {api} from "../config/api";


export const getCurrent = () => {
	return async (dispatch) => {

		dispatch({type: GET_CURRENT_USER_REQUEST});

		try {
			const result = await api.get(Paths.User.GetCurrent);
			dispatch({type: GET_CURRENT_USER_SUCCESS, payload: result});
		} catch (err) {
			console.warn(err);
			dispatch({type: GET_CURRENT_USER_FAILURE});
		}
	}
}
