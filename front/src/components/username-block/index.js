import React, {Component} from 'react';
import styled from 'styled-components';
import PropTypes from "prop-types";
import {updateUsername} from '../../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getPlayer, getCurrent} from "../../actions";
import {Colors, MediaViews} from '../../helpers/style-variables';

import EditIco from '@material-ui/icons/Edit';
import DoneIco from '@material-ui/icons/Done';

class UsernameBlock extends Component {
  constructor(props) {
    super(props);
    this.onKeyDown = this.onKeyDown.bind(this);
    this.state = {
      username: this.props.player.username,
      usernameError: null,
      isSubmitButtonDisplay: false
    }
  }

  onUsernameChange = (e) => this.setState({username: e.target.value, usernameError: null})

  onUsernameFocus = () => this.setState({isSubmitButtonDisplay: true})

  onSubmit = async () => {
    if (this.state.username === this.props.player.username) {
      return this.setState({isSubmitButtonDisplay: false});
    };
    try {
      const data = {
        username: this.state.username
      };
      await updateUsername(data);
      this.props.actions.getPlayer(this.props.player.id);
      this.props.actions.getCurrent();
      this.setState({isSubmitButtonDisplay: false});
    } catch (err) {
      const error = err.response.data.message;
      this.setState({usernameError: error})
    }
  }

  onKeyDown = (e) => e.key === 'Enter' && this.onSubmit()

  render() {
    const {username, usernameError, isSubmitButtonDisplay} = this.state;
    const {isCurrent} = this.props;

    return (
      <Content>
        {
          isCurrent ?
            <UsernameInputBlock>
              <EditIco />
              <UsernameInput value={username} onChange={this.onUsernameChange}
                             onFocus={this.onUsernameFocus} onKeyDown={this.onKeyDown}/>
              {
                isSubmitButtonDisplay && <CustomDoneIco onClick={this.onSubmit}/>
              }
            </UsernameInputBlock> :
            <Username>{username}</Username>
        }

        <UsernameError>{usernameError}</UsernameError>
      </Content>
    )
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
    getPlayer,
    getCurrent,
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default connect(mapStateToProps, mapDispatchToProps)(UsernameBlock);

UsernameBlock.propTypes = {
  isCurrent: PropTypes.bool.isRequired,
}

const Content = styled.div`
  display: flex;
  position: relative;
  @media (max-width: ${MediaViews.MOBILE}px) {
    margin-bottom: 20px;
  }
`;

const Username = styled.div`
  font-size: 1.7em;
  padding: 5px 40px 5px 10px;
  width: 400px;
  box-sizing: border-box;
  @media (max-width: ${MediaViews.MOBILE}px) {
    text-align: center;
  }
`;

const UsernameInputBlock = styled.div`
  display: flex;
  align-items: center;
`;

const UsernameInput = styled.input`
  font-size: 1.7em;
  background-color: #fbfbfb;
  padding: 5px 10px;
  width: 400px;
  box-sizing: border-box;
  border: none;
  border-radius: 5px;
  outline: none;
  &:hover {
    background-color: #f9f9f9
  }
  &:focus {
		background-color: #f7f7f7
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    text-align: center;
    width: 100%;
  }
`;

const UsernameError = styled.span`
  color: ${Colors.ERROR_COLOR};
  display: flex;
  align-items: center;
  position: absolute;
  left: 10px;
  top: -20px;
`;

const CustomDoneIco = styled(DoneIco)`
  margin-left: 10px;
  cursor: pointer;
  border: 2px solid ${Colors.MAIN_COLOR};
  border-radius: 5px;
  width: 1.2em !important;
  height: 1.2em !important;
  color: ${Colors.MAIN_COLOR};
`;