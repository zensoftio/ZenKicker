import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import {Input} from '../../components-ui/input';
import {updatePassword} from '../../actions/user';

const Content = styled.div`
  display: flex;
`;

const PopupContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
`;

const PopupTitle = styled.div`
  font-size: 1.5em;
  margin-bottom: 40px;
`;

const InputsContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: max-content;
  height: max-content;
  background-color: #fff;
  padding: 40px 80px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  border-radius: 2px;
`;

const PasswordError = styled.span`
  color: red;
  display: flex;
  align-items: center;
  margin: 20px 0;
`;

class PasswordBlock extends Component {
  constructor(props) {
    super(props);
    this.state = {
      newPassword: '',
      currentPassword: '',
      passwordError: null,
      isPopupOpen: false
    }
  }

  onPopupOpen = () => this.setState({isPopupOpen: true})
  onPopupClose = () => this.setState({isPopupOpen: false, newPassword: '', currentPassword: '', passwordError: null,})

  handleOnBackgroundClick = (e) => {
    if (e.target.classList.contains(PopupContainer.styledComponentId)) {
      this.onPopupClose()
    }
  }

  onNewPasswordChange = (value) => this.setState({newPassword: value, passwordError: null})
  onCurrentPasswordChange = (value) => this.setState({currentPassword: value, passwordError: null})

  onChangePasswordClick = async () => {
    if (this.state.newPassword === this.state.currentPassword) return;
    if (this.state.newPassword === '' || this.state.currentPassword === '') return this.setState({passwordError: 'All fields are required'});
    try {
      const data = {
        currentPassword: this.state.currentPassword,
        newPassword: this.state.newPassword
      };
      await updatePassword(data);
      this.onPopupClose()
    } catch (err) {
      const error = err.response.data.message;
      this.setState({passwordError: error})
    }
  }

  render() {
    const {newPassword, currentPassword, passwordError, isPopupOpen} = this.state;

    return (
      <Content>
        {!isPopupOpen && <Button onClick={this.onPopupOpen}>Change password</Button>}
        {
          isPopupOpen &&
            <PopupContainer onClick={this.handleOnBackgroundClick}>
              <InputsContainer>
                <PopupTitle>Change password</PopupTitle>
                <Input value={currentPassword} onChange={(e) => this.onCurrentPasswordChange(e.target.value)}
                       placeholder='Enter old password' type='password'/>
                <Input value={newPassword} onChange={(e) => this.onNewPasswordChange(e.target.value)}
                       placeholder='Enter new password' type='password'/>
                <PasswordError>{passwordError}</PasswordError>
                <Button onClick={this.onChangePasswordClick}>Confirm</Button>
              </InputsContainer>
            </PopupContainer>
        }
      </Content>
    )
  }
}

export default PasswordBlock