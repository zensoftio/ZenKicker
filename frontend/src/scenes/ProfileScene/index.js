import React, {Component} from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {
  getPlayer, getPlayerGames, appendToPlayerGames, getPlayerDeltaStatistic, getPlayerGamesCountStatistic, getRelations,
  getRelationsDashboard, getCurrent, getPlayerAchievements
} from '../../actions';
import {withRouter} from 'react-router-dom';
import ProfileMainInfo from '../../components/profile-main-info';
import PlayerGames from '../../components/player-games';
import ChartStatistics from '../../components/chart-statistics';
import PlayerRelations from "../../components/player-relations";
import {MediaViews} from "../../helpers/style-variables";
import {
  PlayerGameModel, PlayerModel, PlayerStatsModel, RelationDashboardModel, RelationModel
} from "../../common/global-prop-types";

class ProfileScene extends Component {

  componentDidMount() {
    const {match, actions} = this.props;
    const playerId = match.params.id;

    actions.getPlayer(playerId);
    actions.getPlayerDeltaStatistic(playerId);
    actions.getPlayerGames(playerId);
    actions.getPlayerGamesCountStatistic(playerId);
    actions.getRelations(playerId);
    actions.getRelationsDashboard(playerId);
    actions.getPlayerAchievements(playerId);
  }

  componentDidUpdate() {
    const {player, actions, match} = this.props;
    const playerId = match.params.id;

    if (player && player.id !== +playerId) {
      actions.getPlayer(playerId);
      actions.getPlayerDeltaStatistic(playerId);
      actions.getPlayerGames(playerId);
      actions.getPlayerGamesCountStatistic(playerId);
      actions.getRelations(playerId);
      actions.getRelationsDashboard(playerId);
      actions.getPlayerAchievements(playerId);
    }
  }

  render() {
    const {
      player, match, currentUser, playerGames, actions, ratingStatistic, gamesCountStatistic, relations, relationsDashboard, achievements
    } = this.props;

    const playerId = match.params.id;

    if (!player) return null;
    if (player.id !== +playerId) return null;

    const mappedGames = playerGames.list.map(game => ({
      ...game.gameDto,
      delta: game.delta,
      won: game.won,
    }));

    const isCurrent = !!currentUser && (+playerId === currentUser.id);
    const isMobile = window.outerWidth <= MediaViews.MOBILE;
    return (

      <Content>
        <ProfileMainInfo countGames={player.countGames} rated={player.rated} rating={player.rating}
                         id={playerId} goalsAgainst={player.goalsAgainst} goalsFor={player.goalsFor}
                         bestPartner={relationsDashboard && relationsDashboard.bestPartner} iconPath={player.iconPath}
                         worstPartner={relationsDashboard && relationsDashboard.worstPartner}
                         favoritePartner={relationsDashboard && relationsDashboard.favoritePartner}
                         fullName={player.fullName} isCurrent={isCurrent} countLosses={player.countLosses}
                         countWins={player.countWins} winningPercentage={player.winningPercentage}
                         currentLossStreak={player.currentLossesStreak} currentWinStreak={player.currentWinningStreak}
                         longestLossStreak={player.longestLossesStreak} longestWinStreak={player.longestWinningStreak}
                         getPlayer={actions.getPlayer} getCurrent={actions.getCurrent} email={player.email}
                         achievements={achievements}/>
        <PlayerRelations relations={relations.list} isMobile={isMobile} gamesPlayed={player.countGames}/>
        <ChartStatistics ratingStatistic={ratingStatistic} gamesCountStatistic={gamesCountStatistic}
                         isMobile={isMobile}/>
        <PlayerGames games={mappedGames ? mappedGames : []} appendToGames={actions.appendToPlayerGames}
                     totalCount={playerGames.totalCount} isMobile={isMobile} playerId={playerId}/>
      </Content>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    player: state.player.player,
    playerGames: state.game.playerGames,
    currentUser: state.user.current,
    ratingStatistic: state.player.deltaStatistic,
    gamesCountStatistic: state.player.gamesCountStatistic,
    relations: state.player.relations,
    relationsDashboard: state.player.relationsDashboard,
    achievements: state.player.achievements,
  };
  return props;
};
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getPlayer,
    getPlayerGames,
    appendToPlayerGames,
    getPlayerGamesCountStatistic,
    getPlayerDeltaStatistic,
    getRelations,
    getRelationsDashboard,
    getCurrent,
    getPlayerAchievements
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(ProfileScene));

ProfileScene.propTypes = {
  player: PlayerStatsModel,
  playerGames: PropTypes.shape({
    list: PropTypes.arrayOf(PlayerGameModel),
    totalCount: PropTypes.number.isRequired
  }).isRequired,
  currentUser: PlayerModel,
  ratingStatistic: PropTypes.arrayOf(PropTypes.number.isRequired).isRequired,
  gamesCountStatistic: PropTypes.arrayOf(PropTypes.number.isRequired).isRequired,
  relations: PropTypes.shape({
    list: PropTypes.arrayOf(RelationModel),
    totalCount: PropTypes.number.isRequired
  }).isRequired,
  relationsDashboard: RelationDashboardModel,
};

const Content = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
`;
