import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getTopPlayers, getLatestGames, getAllPlayers} from '../../actions';
import {withRouter} from 'react-router-dom';
import LatestGames from '../../components/latest-games';
import TopPlayers from '../../components/top-players';

class DashboardScene extends Component {

  componentDidMount() {
    const {getTopPlayers, getAllPlayers, getLatestGames} = this.props.actions;
    getTopPlayers();
    getAllPlayers();
    getLatestGames();
  }

  render() {
    const {players, topPlayers, latestGames} = this.props;

    const mappedLatestGames = players.list && latestGames.list.map(game => (
      {
        ...game,
        winner1Icon: players.list.length ? players.list.find(player => player.id === game.winner1Id).iconName : null,
        winner2Icon: players.list.length ? players.list.find(player => player.id === game.winner2Id).iconName : null,
        loser1Icon: players.list.length ? players.list.find(player => player.id === game.loser1Id).iconName : null,
        loser2Icon: players.list.length ? players.list.find(player => player.id === game.loser2Id).iconName : null,
        winner1Name: players.list.length ? players.list.find(player => player.id === game.winner1Id).username : null,
        winner2Name: players.list.length ? players.list.find(player => player.id === game.winner2Id).username : null,
        loser1Name: players.list.length ? players.list.find(player => player.id === game.loser1Id).username : null,
        loser2Name: players.list.length ? players.list.find(player => player.id === game.loser2Id).username : null
      }
    ))

    return (
      <Content>
        <PlayersContent>
          <Title>Top Players</Title>
          <TopPlayers topPlayers={topPlayers.list}/>
        </PlayersContent>
        <LatestGamesContent>
          <Title>Latest Games</Title>
          <LatestGames latestGames={mappedLatestGames}/>
        </LatestGamesContent>
      </Content>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    topPlayers: state.player.topPlayers,
    players: state.player.players,
    latestGames: state.game.latestGames
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getTopPlayers,
    getLatestGames,
    getAllPlayers
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(DashboardScene));

const Content = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 900px;
`;

const Title = styled.div`
  font-size: 1.5em;
  margin: 20px 0;
  width: 100%;
  text-align: center;
`;

const PlayersContent = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

const LatestGamesContent = styled.div`
  margin-top: 50px;
	display: flex;
	flex-direction: column;
	align-items: center;
`;
