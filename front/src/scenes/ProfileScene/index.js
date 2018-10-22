import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getPlayer, getPlayerGames, appendToPlayerGames, getGamesCountPerWeeks} from '../../actions';
import {withRouter} from 'react-router-dom';
import ProfileMainInfo from '../../components/profile-main-info';
import PlayerGames from '../../components/player-games';

class ProfileScene extends Component {

  componentDidMount() {
    const {match, actions} = this.props;
    const playerId = match.params.id;
    actions.getPlayer(playerId);
    actions.getPlayerGames(playerId);
    actions.getGamesCountPerWeeks(playerId);
  }

  componentDidUpdate() {
    const {player, actions, match} = this.props;
    const playerId = match.params.id;
    if (player && player.id !== +playerId) {
      actions.getPlayer(playerId);
      actions.getPlayerGames(playerId);
      actions.getGamesCountPerWeeks(playerId);
    }
  }

  render() {
    const {player, match, currentUser, players, playerGames, actions} = this.props;
    const playerId = match.params.id;

    if (!player) return null;
    if (player.id !== +playerId) return null;

    const mappedGames = playerGames.list.map(game => (
      {
        ...game,
        winner1Icon: players.list.find(player => player.id === game.winner1Id).iconName || null,
        winner2Icon: players.list.find(player => player.id === game.winner2Id).iconName || null,
        loser1Icon: players.list.find(player => player.id === game.loser1Id).iconName || null,
        loser2Icon: players.list.find(player => player.id === game.loser2Id).iconName || null,
        winner1Name: players.list.length ? players.list.find(player => player.id === game.winner1Id).username : null,
        winner2Name: players.list.length ? players.list.find(player => player.id === game.winner2Id).username : null,
        loser1Name: players.list.length ? players.list.find(player => player.id === game.loser1Id).username : null,
        loser2Name: players.list.length ? players.list.find(player => player.id === game.loser2Id).username : null
      }
    ))

    const isCurrent = +playerId === currentUser.id;
    return (
      <Content>
        <ProfileMainInfo countGames={player.countGames} rated={player.rated} rating={player.rating} id={this.props.match.params.id}
                         username={player.username} isCurrent={isCurrent}/>
        <PlayerGames games={mappedGames ? mappedGames : []} appendToGames={actions.appendToPlayerGames} totalCount={playerGames.totalCount}
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
    gamesCount: state.game.gamesCount
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getPlayer,
    getPlayerGames,
    appendToPlayerGames,
    getGamesCountPerWeeks
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
