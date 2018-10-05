import React, {Component} from 'react';
import styled from 'styled-components';
import {updateUsername} from '../../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getCurrent} from "../../actions";

const Content = styled.div`
  display: flex;
  position: relative;
`;

const Username = styled.div`
  font-size: 1.7em;
  padding: 5px 40px 5px 10px;
  width: 400px;
  box-sizing: border-box;
`;

const UsernameInput = styled.input`
  font-size: 1.7em;
  padding: 5px 10px;
  width: 400px;
  box-sizing: border-box;
  border: none;
  border-radius: 5px;
  outline: none;
  &:hover {
    background-color: #fbfbfb
  }
  &:focus {
		background-color: #f7f7f7
  }
`;

const UsernameError = styled.span`
  color: #C74242;
  display: flex;
  align-items: center;
  position: absolute;
  left: 10px;
  top: -20px;
`;

class UsernameBlock extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: this.props.player.username,
      usernameError: null
    }
  }

  onUsernameChange = (value) => this.setState({username: value, usernameError: null})

  onUsernameBlur = async () => {
    if (this.state.username === this.props.player.username) return;
    try {
      const data = {
        username: this.state.username
      };
      await updateUsername(data);
      this.props.actions.getCurrent();
    } catch (err) {
      const error = err.response.data.message;
      this.setState({usernameError: error})
    }
  }

  render() {
    const {username, usernameError} = this.state;
    const {isCurrent} = this.props;

    return (
      <Content>
        {
          isCurrent ?
            <UsernameInput value={username} onChange={(e) => this.onUsernameChange(e.target.value)}
                           onBlur={this.onUsernameBlur}/> :
            <Username>{username}</Username>
        }

        <UsernameError>{usernameError}</UsernameError>
      </Content>
    )
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    player: state.user.player,
    currentUser: state.user.current
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getCurrent
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default connect(mapStateToProps, mapDispatchToProps)(UsernameBlock);