import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {
  getPlayer,
  getPlayerGames,
  appendToPlayerGames,
  getPlayerDeltaStatistic,
  getPlayerGamesCountStatistic,
  getRelations
} from '../../actions';
import {withRouter} from 'react-router-dom';
import ProfileMainInfo from '../../components/profile-main-info';
import PlayerGames from '../../components/player-games';
import ChartStatistics from '../../components/chart-statistics';
import PlayerRelations from "../../components/player-relations";
import {getPlayerInfo} from "../../helpers/get-player-info";

class ProfileScene extends Component {

  componentDidMount() {
    const {match, actions} = this.props;
    const playerId = match.params.id;
    actions.getPlayer(playerId);
    actions.getPlayerDeltaStatistic(playerId);
    actions.getPlayerGames(playerId);
    actions.getPlayerGamesCountStatistic(playerId);
    actions.getRelations(playerId);
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
    }
  }

  render() {
    const {player, match, currentUser, players, playerGames, actions, ratingStatistic, gamesCountStatistic, relations} = this.props;

    const playerId = match.params.id;

    if (!player) return null;
    if (player.id !== +playerId) return null;

    const mappedGames = playerGames.list.map(game => (
      {
        ...game.gameDto,
        delta: game.delta,
        won: game.won,
        ...getPlayerInfo(players, game.gameDto),
        reportedBy: players.list.length ? players.list.find(i => i.player.id === game.gameDto.reportedById).player.username : null,
      }
    ))

    const isCurrent = +playerId === currentUser.id;

    const mappedRelations = relations.list.map(relation => (
      {
        ...relation,
        partnerIcon: players.list.find(i => i.player.id === relation.partnerId).player.iconName || null,
        partnerName: players.list.find(i => i.player.id === relation.partnerId).player.username || null,
      }
    ))

    return (
      <Content>
        <ProfileMainInfo countGames={player.countGames} rated={player.rated} rating={player.rating}
                         id={this.props.match.params.id}
                         username={player.username} isCurrent={isCurrent} countLosses={player.countLosses}
                         countWins={player.countWins} winningPercentage={player.winningPercentage}
                         goalsAgainst={player.goalsAgainst} goalsFor={player.goalsFor}
                         currentLossStreak={player.currentLossesStreak} currentWinStreak={player.currentWinningStreak}
                         longestLossStreak={player.longestLossesStreak} longestWinStreak={player.longestWinningStreak}/>
        <PlayerRelations relations={mappedRelations}/>
        <ChartStatistics ratingStatistic={ratingStatistic} gamesCountStatistic={gamesCountStatistic}/>
        <PlayerGames games={mappedGames ? mappedGames : []} appendToGames={actions.appendToPlayerGames}
                     totalCount={playerGames.totalCount}
                     playerId={playerId}/>
      </Content>
    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    player: state.player.player,
    players: state.player.players,
    playerGames: state.game.playerGames,
    currentUser: state.user.current,
    ratingStatistic: state.player.deltaStatistic,
    gamesCountStatistic: state.player.gamesCountStatistic,
    relations: state.player.relations,
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getPlayer,
    getPlayerGames,
    appendToPlayerGames,
    getPlayerGamesCountStatistic,
    getPlayerDeltaStatistic,
    getRelations
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(ProfileScene));

const Content = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;
