import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getActivePlayers, getAllPlayers, getLatestGames, getAllGames, appendToGames} from '../../actions';
import {withRouter} from 'react-router-dom';
import PlayersTabs from '../../components/players-tabs';
import GamesTabs from '../../components/games-tabs';

const Content = styled.div`
  display: flex;
  justify-content: space-between;
`;

const Title = styled.div`
  font-size: 1.5em;
  margin: 20px 0;
  width: 100%;
`;

const PlayersContent = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

const LatestGamesContent = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

class DashboardScene extends Component {

  componentDidMount() {
    this.props.actions.getActivePlayers();
    this.props.actions.getAllPlayers();
    this.props.actions.getLatestGames();
    this.props.actions.getAllGames();
  }

  render() {
    const {players, activePlayers, latestGames, games, actions} = this.props;

    const mappedGames = players.list && games.list.map(game => (
      {
        ...game,
        winner1Icon: players.list.length ? players.list.find(player => player.id === game.winner1Id).iconName : null,
        winner2Icon: players.list.length ? players.list.find(player => player.id === game.winner2Id).iconName : null,
        loser1Icon: players.list.length ? players.list.find(player => player.id === game.loser1Id).iconName : null,
        loser2Icon: players.list.length ? players.list.find(player => player.id === game.loser2Id).iconName : null
      }
    ))

    const mappedLatestGames = players.list && latestGames.list.map(game => (
      {
        ...game,
        winner1Icon: players.list.length ? players.list.find(player => player.id === game.winner1Id).iconName : null,
        winner2Icon: players.list.length ? players.list.find(player => player.id === game.winner2Id).iconName : null,
        loser1Icon: players.list.length ? players.list.find(player => player.id === game.loser1Id).iconName : null,
        loser2Icon: players.list.length ? players.list.find(player => player.id === game.loser2Id).iconName : null
      }
    ))

    return (
      <Content>
        <PlayersContent>
          <Title>Players</Title>
          <PlayersTabs players={players ? players.list : []} activePlayers={activePlayers ? activePlayers.list : []}/>
        </PlayersContent>
        <LatestGamesContent>
          <Title>Games</Title>
          <GamesTabs latestGames={mappedLatestGames ? mappedLatestGames : []} games={mappedGames ? mappedGames : []}
                     appendToGamesAction={actions.appendToGames} totalCount={games.totalCount}/>
        </LatestGamesContent>
      </Content>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    activePlayers: state.user.activePlayers,
    players: state.user.players,
    games: state.game.games,
    latestGames: state.game.latestGames
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getActivePlayers,
    getLatestGames,
    getAllPlayers,
    getAllGames,
    appendToGames,
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(DashboardScene));