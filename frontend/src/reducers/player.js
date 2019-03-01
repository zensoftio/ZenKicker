import {ActionType} from '../actions/const';

const initState = {
  activePlayers: {
    list: [],
    totalCount: 0
  },
  players: {
    list: [],
    totalCount: 0
  },
  playersDashboard: null,
  player: null,
  deltaStatistic: [],
  gamesCountStatistic: [],
  relations: {
    list: [],
    totalCount: 0
  },
  relationsDashboard: null,
  searchResult: {
    list: [],
    totalCount: 0
  },
  sort: {
    sortBy: 'rating',
    sortDirection: 'DESC'
  },
  achievements: []
};

export const player = (state = initState, action) => {
  switch (action.type) {
    case ActionType.Player.GET_ACTIVE_PLAYERS_SUCCESS:
      return {
        ...state,
        activePlayers: action.payload
      };

    case ActionType.Player.GET_ALL_PLAYERS_SUCCESS:
      return {
        ...state,
        players: action.payload
      };

    case ActionType.Player.GET_RELATIONS_SUCCESS:
      return {
        ...state,
        relations: action.payload
      };

    case ActionType.Player.GET_RELATIONS_DASHBOARD_SUCCESS:
      return {
        ...state,
        relationsDashboard: action.payload
      };

    case ActionType.Player.GET_PLAYERS_DASHBOARD_SUCCESS:
      return {
        ...state,
        playersDashboard: action.payload
      };

    case ActionType.Player.GET_PLAYER_SUCCESS:
      const player = {...action.payload.player, ...action.payload};
      delete player['player'];
      return {
        ...state,
        player: player
      };

    case ActionType.Player.GET_PLAYER_DELTA_STATISTIC_SUCCESS:
      return {
        ...state,
        deltaStatistic: action.payload
      };

    case ActionType.Player.GET_PLAYER_GAMES_COUNT_STATISTIC_SUCCESS:
      return {
        ...state,
        gamesCountStatistic: action.payload
      };

    case ActionType.Player.APPEND_TO_PLAYERS_SUCCESS:
      return {
        ...state,
        players: {
          totalCount: action.payload.totalCount,
          list: [...state.players.list, ...action.payload.list]
        }
      };

    case ActionType.Player.APPEND_TO_ACTIVE_PLAYERS_SUCCESS:
      return {
        ...state,
        activePlayers: {
          totalCount: action.payload.totalCount,
          list: [...state.activePlayers.list, ...action.payload.list]
        }
      };

    case ActionType.Player.GET_SEARCH_RESULT_SUCCESS:
      return {
        ...state,
        searchResult: action.payload
      };

    case ActionType.Player.INIT_SORT:
      return {
        ...state,
        sort: action.payload
      };

    case ActionType.Player.GET_PLAYER_ACHIEVEMENTS_SUCCESS:
      return {
        ...state,
        achievements: action.payload
      };

    default:
      return state;
  }
};
