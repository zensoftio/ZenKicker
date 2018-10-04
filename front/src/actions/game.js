import Paths from "../dicts/paths";
import {api} from "../config/api";
import {
  GET_ALL_GAMES_REQUEST,
  GET_ALL_GAMES_SUCCESS,
  GET_ALL_GAMES_FAILURE
} from './const';

export const registerGame = async (data) => {
  return await api.post(Paths.Game.RegisterGame, data);
}

export const getAllGames = () => {
  return async (dispatch) => {

    dispatch({type: GET_ALL_GAMES_REQUEST});

    try {
      const result = await api.get(Paths.Game.GetAll);
      dispatch({type: GET_ALL_GAMES_SUCCESS, payload: result.data});
    } catch (err) {
      console.warn(err);
      dispatch({type: GET_ALL_GAMES_FAILURE});
    }
  }
}