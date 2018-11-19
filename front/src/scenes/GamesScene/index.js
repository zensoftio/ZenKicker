import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getAllGames, appendToGames, getAllPlayers} from '../../actions';
import {withRouter} from 'react-router-dom';
import AllGames from '../../components/all-games';

class GamesScene extends Component {

  componentDidMount() {
    const {getAllGames, getAllPlayers} = this.props.actions;
    getAllGames();
    getAllPlayers();
  }

  render() {
    const {players, games, actions} = this.props;

    const mappedGames = games.list.map(game => (
      {
        ...game,
        winner1Icon: players.list.find(player => player.id === game.winner1Id).iconName || null,
        winner2Icon: players.list.find(player => player.id === game.winner2Id).iconName || null,
        loser1Icon: players.list.find(player => player.id === game.loser1Id).iconName || null,
        loser2Icon: players.list.find(player => player.id === game.loser2Id).iconName || null,
        winner1Name: players.list.length ? players.list.find(player => player.id === game.winner1Id).username : null,
        winner2Name: players.list.length ? players.list.find(player => player.id === game.winner2Id).username : null,
        loser1Name: players.list.length ? players.list.find(player => player.id === game.loser1Id).username : null,
        loser2Name: players.list.length ? players.list.find(player => player.id === game.loser2Id).username : null,
        reportedBy: players.list.length ? players.list.find(player => player.id === game.reportedById).username : null,
      }
    ))

    return (
      <Content>
        <AllGames games={mappedGames ? mappedGames : []} appendToGames={actions.appendToGames} totalCount={games.totalCount}/>
      </Content>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    players: state.player.players,
    games: state.game.games,
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getAllGames,
    appendToGames,
    getAllPlayers
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(GamesScene));

const Content = styled.div`
  display: flex;
  justify-content: space-between;
	flex-direction: column;
	align-items: center;
`;
