import Paths from "../dicts/paths";
import {api} from "../config/api";
import {ActionType} from './const';

export const getCurrent = () => {
  return async (dispatch) => {

    dispatch({type: ActionType.User.GET_CURRENT_USER_REQUEST});

    try {
      const result = await api.get(Paths.User.GetCurrent);
      dispatch({
        type: ActionType.User.GET_CURRENT_USER_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.User.GET_CURRENT_USER_FAILURE});
    }
  };
};

export const updateFullName = async (data) => {
  return await api.put(Paths.User.UpdateFullName, data);
};
export const updatePassword = async (data) => {
  return await api.put(Paths.User.UpdatePassword, data);
};
export const updatePhoto = async (data) => {
  return await api.put(Paths.User.UpdatePhoto, data);
};
export const updateEmail = async (data) => {
  return await api.put(Paths.User.UpdateEmail, data);
};