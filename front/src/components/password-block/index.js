import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import {Input} from '../../components-ui/input';
import {updatePassword} from '../../actions/user';
import Popup from '../popup';

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
    }
  }

  clearValues = () => this.setState({newPassword: '', currentPassword: '', passwordError: null})

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
      this.clearValues();
      this.child.onPopupClose()
    } catch (err) {
      const error = err.response.data.message;
      this.setState({passwordError: error});
    }
  }

  render() {
    const {newPassword, currentPassword, passwordError} = this.state;

    return (
      <Popup buttonTitle='Change password' ref={instance => {this.child = instance}}>
        <InputsContainer>
          <PopupTitle>Change password</PopupTitle>
          <Input value={currentPassword} onChange={(e) => this.onCurrentPasswordChange(e.target.value)}
                 placeholder='Enter old password' type='password'/>
          <Input value={newPassword} onChange={(e) => this.onNewPasswordChange(e.target.value)}
                 placeholder='Enter new password' type='password'/>
          <PasswordError>{passwordError}</PasswordError>
          <Button onClick={this.onChangePasswordClick}>Confirm</Button>
        </InputsContainer>
      </Popup>
    )
  }
}

export default PasswordBlock