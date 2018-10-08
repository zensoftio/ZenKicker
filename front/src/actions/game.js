import Paths from "../dicts/paths";
import {api} from "../config/api";
import {
  GET_ALL_GAMES_REQUEST, GET_ALL_GAMES_SUCCESS, GET_ALL_GAMES_FAILURE,
  GET_LATEST_GAMES_REQUEST, GET_LATEST_GAMES_SUCCESS, GET_LATEST_GAMES_FAILURE,
  APPEND_TO_GAMES_REQUEST, APPEND_TO_GAMES_SUCCESS, APPEND_TO_GAMES_FAILURE
} from './const';

export const registerGame = async (data) => {
  return await api.post(Paths.Game.RegisterGame, data);
}

export const getLatestGames = () => async (dispatch) => {
  dispatch({type: GET_LATEST_GAMES_REQUEST});

  try {
    const result = await api.get(Paths.Game.GetAll, {params: {limit: 7}});
    dispatch({type: GET_LATEST_GAMES_SUCCESS, payload: result.data});
  } catch (err) {
    console.warn(err);
    dispatch({type: GET_LATEST_GAMES_FAILURE});
  }
}

export const getAllGames = () => async (dispatch) => {
  dispatch({type: GET_ALL_GAMES_REQUEST});

  try {
    const result = await api.get(Paths.Game.GetAll);
    dispatch({type: GET_ALL_GAMES_SUCCESS, payload: result.data});
  } catch (err) {
    console.warn(err);
    dispatch({type: GET_ALL_GAMES_FAILURE});
  }
}

export const appendToGames = (gamesLength) => async (dispatch) => {
  dispatch({type: APPEND_TO_GAMES_REQUEST});

  try {
    const page = {offset: gamesLength, limit: 7};
    const result = await api.get(Paths.Game.GetAll, {params: page});
    dispatch({type: APPEND_TO_GAMES_SUCCESS, payload: result.data});
  } catch (err) {
    console.warn(err);
    dispatch({type: APPEND_TO_GAMES_FAILURE});
  }
}
