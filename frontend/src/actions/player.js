import Paths from "../dicts/paths";
import {api} from "../config/api";
import {ActionType} from './const';

export const initSort = (sort, isAllPlayers) => async (dispatch) => {
  dispatch({
    type: ActionType.Player.INIT_SORT,
    payload: sort
  });
  if (isAllPlayers) {
    dispatch(getAllPlayers());
  } else {
    dispatch(getActivePlayers());
  }

};

export const getActivePlayers = () => {
  return async (dispatch, getState) => {
    dispatch({type: ActionType.Player.GET_ACTIVE_PLAYERS_REQUEST});

    try {
      const state = getState();
      const page = {
        limit: 15,
        sortBy: state.player.sort.sortBy,
        sortDirection: state.player.sort.sortDirection
      };
      const result = await api.get(Paths.Player.GetActive, {params: page});
      dispatch({
        type: ActionType.Player.GET_ACTIVE_PLAYERS_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_ACTIVE_PLAYERS_FAILURE});
    }
  };
};

export const getAllPlayers = () => {
  return async (dispatch, getState) => {
    dispatch({type: ActionType.Player.GET_ALL_PLAYERS_REQUEST});

    try {
      const state = getState();
      const page = {
        limit: 15,
        sortBy: state.player.sort.sortBy,
        sortDirection: state.player.sort.sortDirection
      };
      const result = await api.get(Paths.Player.GetAll, {params: page});
      dispatch({
        type: ActionType.Player.GET_ALL_PLAYERS_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_ALL_PLAYERS_FAILURE});
    }
  };
};

export const getPlayersDashboard = () => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_PLAYERS_DASHBOARD_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetPlayersDashboard);
      dispatch({
        type: ActionType.Player.GET_PLAYERS_DASHBOARD_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_PLAYERS_DASHBOARD_FAILURE});
    }
  };
};

export const getPlayer = (id) => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_PLAYER_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetPlayer(id));
      dispatch({
        type: ActionType.Player.GET_PLAYER_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_PLAYER_FAILURE});
    }
  };
};

export const appendToPlayers = () => async (dispatch, getState) => {
  dispatch({type: ActionType.Player.APPEND_TO_PLAYERS_REQUEST});

  try {
    const state = getState();
    const page = {
      offset: state.player.players.list.length,
      limit: 5,
      sortBy: state.player.sort.sortBy,
      sortDirection: state.player.sort.sortDirection
    };
    const result = await api.get(Paths.Player.GetAll, {params: page});
    dispatch({
      type: ActionType.Player.APPEND_TO_PLAYERS_SUCCESS,
      payload: result.data
    });
  } catch (err) {
    dispatch({type: ActionType.Player.APPEND_TO_PLAYERS_FAILURE});
  }
};

export const appendToActivePlayers = () => async (dispatch, getState) => {
  dispatch({type: ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_REQUEST});

  try {
    const state = getState();
    const page = {
      offset: state.player.activePlayers.list.length,
      limit: 5,
      sortBy: state.player.sort.sortBy,
      sortDirection: state.player.sort.sortDirection
    };
    const result = await api.get(Paths.Player.GetActive, {params: page});
    dispatch({
      type: ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_SUCCESS,
      payload: result.data
    });
  } catch (err) {
    dispatch({type: ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_FAILURE});
  }
};

export const getPlayerDeltaStatistic = (id) => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_PLAYER_DELTA_STATISTIC_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetDeltaStatistic(id));
      dispatch({
        type: ActionType.Player.GET_PLAYER_DELTA_STATISTIC_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_PLAYER_DELTA_STATISTIC_FAILURE});
    }
  };
};

export const getPlayerGamesCountStatistic = (id) => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_PLAYER_GAMES_COUNT_STATISTIC_REQUEST});

    try {
      const result = await api.get(Paths.Game.GetGamesCountStatistic(id));
      dispatch({
        type: ActionType.Player.GET_PLAYER_GAMES_COUNT_STATISTIC_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_PLAYER_GAMES_COUNT_STATISTIC_FAILURE});
    }
  };
};

export const getRelations = (id) => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_RELATIONS_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetRelations(id));
      dispatch({
        type: ActionType.Player.GET_RELATIONS_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_RELATIONS_FAILURE});
    }
  };
};

export const getRelationsDashboard = (id) => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_RELATIONS_DASHBOARD_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetRelationsDashboard(id));
      dispatch({
        type: ActionType.Player.GET_RELATIONS_DASHBOARD_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_RELATIONS_DASHBOARD_FAILURE});
    }
  };
};

export const searchPlayers = (keyword) => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_SEARCH_RESULT_REQUEST});

    try {
      const result = await api.get(Paths.Player.SearchPlayer(keyword));
      dispatch({
        type: ActionType.Player.GET_SEARCH_RESULT_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_SEARCH_RESULT_FAILURE});
    }
  };
};

export const getPlayerAchievements = (id) => {
  return async (dispatch) => {

    dispatch({type: ActionType.Player.GET_PLAYER_ACHIEVEMENTS_REQUEST});

    try {
      const result = await api.get(Paths.Player.GetAcheivements(id));
      dispatch({
        type: ActionType.Player.GET_PLAYER_ACHIEVEMENTS_SUCCESS,
        payload: result.data
      });
    } catch (err) {
      dispatch({type: ActionType.Player.GET_PLAYER_ACHIEVEMENTS_FAILURE});
    }
  };
};
