import Paths from "../dicts/paths";
import {api} from "../config/api";
import {ActionType} from './const';

export const registerGame = async (data) => {
  return await api.post(Paths.Game.RegisterGame, data);
}

export const getLastGame = () => async (dispatch) => {
  dispatch({type: ActionType.Game.GET_LAST_GAME_REQUEST});

  try {
    const result = await api.get(Paths.Game.GetAll, {params: {limit: 1, sortBy: 'date', sortDirection: 'DESC'}});
    dispatch({type: ActionType.Game.GET_LAST_GAME_SUCCESS, payload: result.data});
  } catch (err) {
    dispatch({type: ActionType.Game.GET_LAST_GAME_FAILURE});
  }
}

export const getLatestGames = () => async (dispatch) => {
  dispatch({type: ActionType.Game.GET_LATEST_GAMES_REQUEST});

  try {
    const result = await api.get(Paths.Game.GetAll, {params: {limit: 5, sortBy: 'date', sortDirection: 'DESC'}});
    dispatch({type: ActionType.Game.GET_LATEST_GAMES_SUCCESS, payload: result.data});
  } catch (err) {
    dispatch({type: ActionType.Game.GET_LATEST_GAMES_FAILURE});
  }
}

export const getPlayerGames = (id) => async (dispatch) => {
  dispatch({type: ActionType.Game.GET_PLAYER_GAMES_REQUEST});

  try {
    const result = await api.get(Paths.Player.PlayerGames(id), {params: {limit: 15, sortDirection: 'DESC'}});
    dispatch({type: ActionType.Game.GET_PLAYER_GAMES_SUCCESS, payload: result.data});
  } catch (err) {
    dispatch({type: ActionType.Game.GET_PLAYER_GAMES_FAILURE});
  }
}

export const appendToPlayerGames = (gamesLength, id) => async (dispatch) => {
  dispatch({type: ActionType.Game.APPEND_TO_PLAYER_GAMES_REQUEST});

  try {
    const page = {offset: gamesLength, limit: 5, sortDirection: 'DESC'};
    const result = await api.get(Paths.Player.PlayerGames(id), {params: page});
    dispatch({type: ActionType.Game.APPEND_TO_PLAYER_GAMES_SUCCESS, payload: result.data});
  } catch (err) {
    dispatch({type: ActionType.Game.APPEND_TO_PLAYER_GAMES_FAILURE});
  }
}

export const getAllGames = () => async (dispatch) => {
  dispatch({type: ActionType.Game.GET_ALL_GAMES_REQUEST});

  try {
    const result = await api.get(Paths.Game.GetAll, {params: {limit: 15, sortBy: 'date', sortDirection: 'DESC'}});
    dispatch({type: ActionType.Game.GET_ALL_GAMES_SUCCESS, payload: result.data});
  } catch (err) {
    dispatch({type: ActionType.Game.GET_ALL_GAMES_FAILURE});
  }
}

export const appendToGames = (gamesLength) => async (dispatch) => {
  dispatch({type: ActionType.Game.APPEND_TO_GAMES_REQUEST});

  try {

    const page = {offset: gamesLength, limit: 5, sortBy: 'date', sortDirection: 'DESC'};
    const result = await api.get(Paths.Game.GetAll, {params: page});
    dispatch({type: ActionType.Game.APPEND_TO_GAMES_SUCCESS, payload: result.data});
  } catch (err) {
    dispatch({type: ActionType.Game.APPEND_TO_GAMES_FAILURE});
  }
}

export const getGamesCountPerWeek = () => async (dispatch) => {
  dispatch({type: ActionType.Game.GET_GAMES_COUNT_PER_WEEK_REQUEST});

  try {
    const result = await api.get(Paths.Game.GetGamesCount);
    dispatch({type: ActionType.Game.GET_GAMES_COUNT_PER_WEEK_SUCCESS, payload: result.data});
  } catch (err) {
    dispatch({type: ActionType.Game.GET_GAMES_COUNT_PER_WEEK_FAILURE});
  }
}
