import PropTypes from "prop-types";

export const PlayerModel = PropTypes.shape({
  iconPath: PropTypes.string,
  id: PropTypes.number.isRequired,
  username: PropTypes.string.isRequired
})

export const GameModel = PropTypes.shape({
  date: PropTypes.number.isRequired,
  id: PropTypes.number.isRequired,
  loser1: PlayerModel.isRequired,
  loser2: PlayerModel.isRequired,
  loserGoals: PropTypes.number,
  reportedBy: PlayerModel.isRequired,
  winner1: PlayerModel.isRequired,
  winner2: PlayerModel.isRequired,
})

export const PlayerStatsModel = PropTypes.shape({
  active: PropTypes.bool.isRequired,
  countGames: PropTypes.number.isRequired,
  countLosses: PropTypes.number.isRequired,
  countWins: PropTypes.number.isRequired,
  currentLossesStreak: PropTypes.number.isRequired,
  currentWinningStreak: PropTypes.number.isRequired,
  goalsAgainst: PropTypes.number.isRequired,
  goalsFor: PropTypes.number.isRequired,
  longestLossesStreak: PropTypes.number.isRequired,
  longestWinningStreak: PropTypes.number.isRequired,
  player: PlayerModel,
  rated: PropTypes.number.isRequired,
  rating: PropTypes.number.isRequired,
  winningPercentage: PropTypes.number.isRequired,
})

export const PlayerDashboardModel = PropTypes.shape({
  firstPlace: PlayerModel.isRequired,
  loser: PlayerModel.isRequired,
  secondPlace: PlayerModel.isRequired,
  thirdPlace: PlayerModel.isRequired,
})

export const RelationDashboardModel = PropTypes.shape({
  countGames: PropTypes.number.isRequired,
  countWins: PropTypes.number.isRequired,
  winningPercentage: PropTypes.number.isRequired,
  partner: PlayerModel.isRequired,
  player: PlayerModel.isRequired,
})