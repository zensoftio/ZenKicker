import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getAllGames, appendToGames, getAllPlayers} from '../../actions';
import {withRouter} from 'react-router-dom';
import AllGames from '../../components/all-games';
import {getUserInfo} from "../../helpers/get-user-info";
import {Colors} from "../../helpers/style-variables";

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
        ...getUserInfo(players, game),
        reportedBy: players.list.length ? players.list.find(i => i.player.id === game.reportedById).player.username : null,
      }
    ))

    return (
      <Content>
        <GamesCount>{games.totalCount} <span>games played</span></GamesCount>
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

const GamesCount = styled.div`
  font-size: 3.7em;
  margin-bottom: 20px;
  color: ${Colors.MAIN_COLOR}
  padding-left: 125px;
  span {
    font-size: .3em;
  }
`;
