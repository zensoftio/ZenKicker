import Paths from "../dicts/paths";
import {api} from "../config/api";
import {ActionType} from './const';

export const getActivePlayers = () => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_ACTIVE_PLAYERS_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetActive, {params: {limit: 15}});
      dispatch({type: ActionType.Player.GET_ACTIVE_PLAYERS_SUCCESS, payload: result.data});
    } catch (err) {
      console.warn(err);
      dispatch({type: ActionType.Player.GET_ACTIVE_PLAYERS_FAILURE});
    }
  }
}

export const getAllPlayers = () => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_ALL_PLAYERS_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetAll, {params: {limit: 15}});
      dispatch({type: ActionType.Player.GET_ALL_PLAYERS_SUCCESS, payload: result.data});
    } catch (err) {
      console.warn(err);
      dispatch({type: ActionType.Player.GET_ALL_PLAYERS_FAILURE});
    }
  }
}

export const getTopPlayers = () => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_TOP_PLAYERS_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetActive, {params: {limit: 7}});
      dispatch({type: ActionType.Player.GET_TOP_PLAYERS_SUCCESS, payload: result.data});
    } catch (err) {
      console.warn(err);
      dispatch({type: ActionType.Player.GET_TOP_PLAYERS_FAILURE});
    }
  }
}

export const getPlayer = (id) => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_PLAYER_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetPlayer(id));
      dispatch({type: ActionType.Player.GET_PLAYER_SUCCESS, payload: result.data});
    } catch (err) {
      console.warn(err);
      dispatch({type: ActionType.Player.GET_PLAYER_FAILURE});
    }
  }
}

export const appendToPlayers = (playersLength) => async (dispatch) => {
  dispatch({type: ActionType.Player.APPEND_TO_PLAYERS_REQUEST});

  try {
    const page = {offset: playersLength, limit: 5};
    const result = await api.get(Paths.Player.GetAll, {params: page});
    dispatch({type: ActionType.Player.APPEND_TO_PLAYERS_SUCCESS, payload: result.data});
  } catch (err) {
    console.warn(err);
    dispatch({type: ActionType.Player.APPEND_TO_PLAYERS_FAILURE});
  }
}

export const appendToActivePlayers = (playersLength) => async (dispatch) => {
  dispatch({type: ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_REQUEST});

  try {
    const page = {offset: playersLength, limit: 5};
    const result = await api.get(Paths.Player.GetAll, {params: page});
    dispatch({type: ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_SUCCESS, payload: result.data});
  } catch (err) {
    console.warn(err);
    dispatch({type: ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_FAILURE});
  }
}