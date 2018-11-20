import {getCurrent, updateUsername} from './user';
import {
  getActivePlayers, getAllPlayers, getPlayer, getTopPlayers, appendToPlayers, appendToActivePlayers,
  getPlayerDeltaStatistic, getPlayerGamesCountStatistic, getLoser
} from './player';
import {
  registerGame,
  getAllGames,
  getLatestGames,
  appendToGames,
  getPlayerGames,
  appendToPlayerGames,
  getGamesCountPerWeek,
  getLastGame
} from './game';

export {
  getCurrent,
  getActivePlayers,
  getAllPlayers,
  getPlayer,
  updateUsername,
  registerGame,
  getAllGames,
  getLatestGames,
  appendToGames,
  getTopPlayers,
  appendToPlayers,
  appendToActivePlayers,
  getPlayerGames,
  appendToPlayerGames,
  getGamesCountPerWeek,
  getPlayerDeltaStatistic,
  getPlayerGamesCountStatistic,
  getLastGame,
  getLoser
};
