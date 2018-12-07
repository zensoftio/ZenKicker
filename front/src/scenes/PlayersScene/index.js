import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getActivePlayers, getAllPlayers, appendToPlayers, appendToActivePlayers} from '../../actions';
import {withRouter} from 'react-router-dom';
import PlayersTabs from '../../components/players-tabs';
import {MediaViews} from "../../helpers/style-variables";

class PlayersScene extends Component {

  componentDidMount() {
    const {getActivePlayers, getAllPlayers} = this.props.actions;
    getActivePlayers();
    getAllPlayers();
  }

  render() {
    const {players, activePlayers, actions} = this.props;
    return (
      <Content>
        <PlayersTabs players={players} activePlayers={activePlayers} appendToPlayers={actions.appendToPlayers}
                     appendToActivePlayers={actions.appendToActivePlayers} getAllPlayersAction={actions.getAllPlayers}
                     getActivePlayersAction={actions.getActivePlayers}/>
      </Content>
    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    activePlayers: state.player.activePlayers,
    players: state.player.players,
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getActivePlayers,
    getAllPlayers,
    appendToPlayers,
    appendToActivePlayers
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(PlayersScene));

const Content = styled.div`
  display: flex;
  justify-content: center;
	flex-direction: column;
	align-items: center;
	@media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
  }
`;