import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getActivePlayers, getAllPlayers} from '../../actions';
import {withRouter} from 'react-router-dom';
import PlayersTabs from '../../components/players-tabs';

const Content = styled.div`
  display: flex;
  justify-content: space-between;
`;

const PlayersContent = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

const GamesContent = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

class DashboardScene extends Component {

  componentDidMount() {
    this.props.actions.getActivePlayers();
    this.props.actions.getAllPlayers();
  }

  render() {
    const {players, activePlayers} = this.props;

    return (
      <Content>
        <PlayersContent>
          <div>Players</div>
          <PlayersTabs players={players ? players.list : []} activePlayers={activePlayers ? activePlayers.list : []}/>
        </PlayersContent>
        <GamesContent>
          <div>Latest Games</div>
        </GamesContent>
      </Content>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    activePlayers: state.user.activePlayers,
    players: state.user.players
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getActivePlayers,
    getAllPlayers
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(DashboardScene));