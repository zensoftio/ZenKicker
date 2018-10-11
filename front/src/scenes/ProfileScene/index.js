import React, {Component} from 'react';
import styled from 'styled-components';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getPlayer} from '../../actions';
import {withRouter} from 'react-router-dom';
import ProfileMainInfo from '../../components/profile-main-info';
import PasswordBlock from '../../components/password-block';

const Content = styled.div`
	display: flex;
	padding: 100px 0 20px 0;
`;

const ChangePasswordContainer = styled.div`
	margin-top: 80px;
`;

class ProfileScene extends Component {

  componentDidMount() {
    const playerId = this.props.match.params.id;
    this.props.actions.getPlayer(playerId);
  }

  componentDidUpdate() {
    const {player} = this.props;
    const playerId = this.props.match.params.id;
    if (player && player.id !== +playerId) {
      this.props.actions.getPlayer(playerId);
    }
  }

  render() {
    const {player, match, currentUser} = this.props;
    const playerId = match.params.id;

    if (!player) return null;
    if (player.id !== +playerId) return null;

    const isCurrent = +playerId === currentUser.id;
    return (
      <div>
        <Content>
          <ProfileMainInfo countGames={player.countGames} rated={player.rated} rating={player.rating} id={this.props.match.params.id}
                           username={player.username} isCurrent={isCurrent}/>
        </Content>
        <ChangePasswordContainer>
          {
            isCurrent && <PasswordBlock />
          }
        </ChangePasswordContainer>
      </div>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    player: state.player.player,
    currentUser: state.user.current
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getPlayer
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(ProfileScene));