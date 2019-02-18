import React, {Component} from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getActivePlayers, getAllPlayers, appendToPlayers, appendToActivePlayers, initSort} from '../../actions';
import {withRouter} from 'react-router-dom';
import PlayersTabs from '../../components/players-tabs';
import {MediaViews} from "../../helpers/style-variables";
import {PlayerStatsModel} from "../../common/global-prop-types";

class PlayersScene extends Component {

  render() {
    const {players, activePlayers, sort, actions} = this.props;
    return (
      <Content>
        <PlayersTabs players={players} activePlayers={activePlayers} appendToPlayers={actions.appendToPlayers}
                     appendToActivePlayers={actions.appendToActivePlayers} initSort={actions.initSort}
                     sort={sort} getActivePlayers={actions.getActivePlayers} getAllPlayers={actions.getAllPlayers}/>
      </Content>
    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    activePlayers: state.player.activePlayers,
    players: state.player.players,
    sort: state.player.sort
  };
  return props;
}
const mapDispatchToProps = (dispatch, getState) => {
  const actions = {
    getActivePlayers,
    getAllPlayers,
    appendToPlayers,
    appendToActivePlayers,
    initSort
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(PlayersScene));

PlayersScene.propTypes = {
  activePlayers: PropTypes.shape({
    list: PropTypes.arrayOf(PlayerStatsModel),
    totalCount: PropTypes.number.isRequired
  }).isRequired,
  players: PropTypes.shape({
    list: PropTypes.arrayOf(PlayerStatsModel),
    totalCount: PropTypes.number.isRequired
  }).isRequired,
  sort: PropTypes.shape({
    sortBy: PropTypes.string.isRequired,
    sortDirection: PropTypes.string.isRequired,
  }).isRequired,
}

const Content = styled.div`
  display: flex;
  justify-content: center;
	flex-direction: column;
	align-items: center;
	@media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
  }
`;