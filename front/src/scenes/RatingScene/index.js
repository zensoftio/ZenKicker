import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getActivePlayers, getAllPlayers} from '../../actions';
import {withRouter} from 'react-router-dom';
import Tabs from '../../components/tabs';

const Content = styled.div`
	padding: 40px 20px 20px 20px;
	display: flex;
	flex-direction: column;
`;

class RatingScene extends Component {

  componentDidMount() {
    this.props.actions.getActivePlayers();
    this.props.actions.getAllPlayers();
  }

  render() {
    const {players, activePlayers} = this.props;
    return (
      <div>
        <Content>
          <Tabs players={players} activePlayers={activePlayers}/>
        </Content>
      </div>

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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(RatingScene));