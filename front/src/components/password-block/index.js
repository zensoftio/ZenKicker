import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import {Input} from '../../components-ui/input';
import {updatePassword} from '../../actions/user';
import Popup from '../popup';
import {Colors} from '../../helpers/style-variables';

class PasswordBlock extends Component {
  constructor(props) {
    super(props);
    this.popupChild = React.createRef();
    this.state = {
      newPassword: '',
      currentPassword: '',
      newPasswordConfirm: '',
      passwordError: null,
    }
  }

  clearValues = () => this.setState({
    newPassword: '',
    currentPassword: '',
    passwordError: null,
    newPasswordConfirm: ''
  })

  onCurrentPasswordChange = (value) => this.setState({currentPassword: value, passwordError: null})
  onNewPasswordChange = (value) => this.setState({newPassword: value, passwordError: null})
  onNewPasswordConfirmChange = (value) => this.setState({newPasswordConfirm: value, passwordError: null})

  onChangePasswordClick = async () => {
    if (this.state.newPassword === '' || this.state.currentPassword === '' || this.state.newPasswordConfirm === '')
      return this.setState({passwordError: 'All fields are required'});
    if (this.state.newPassword !== this.state.newPasswordConfirm)
      return this.setState({passwordError: 'Passwords doesn\'t match'});
    try {
      const data = {
        currentPassword: this.state.currentPassword,
        newPassword: this.state.newPassword
      };
      await updatePassword(data);
      this.clearValues();
      this.popupChild.current.onPopupClose()
    } catch (err) {
      const error = err.response.data.message;
      this.setState({passwordError: error});
    }
  }

  render() {
    const {newPassword, currentPassword, passwordError, newPasswordConfirm} = this.state;

    return (
      <Popup buttonTitle='Change password' ref={this.popupChild} clearValues={this.clearValues}>
        <InputsContainer>
          <PopupTitle>Change password</PopupTitle>
          <Input value={currentPassword} onChange={(e) => this.onCurrentPasswordChange(e.target.value)}
                 placeholder='Enter old password' type='password'/>
          <Input value={newPassword} onChange={(e) => this.onNewPasswordChange(e.target.value)}
                 placeholder='Enter new password' type='password'/>
          <Input value={newPasswordConfirm} onChange={(e) => this.onNewPasswordConfirmChange(e.target.value)}
                 placeholder='Confirm new password' type='password'/>
          <PasswordError>{passwordError}</PasswordError>
          <Button onClick={this.onChangePasswordClick}>Confirm</Button>
        </InputsContainer>
      </Popup>
    )
  }
}

export default PasswordBlock;

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
  background-color: ${Colors.THEME_COLOR}
`;

const PasswordError = styled.span`
  color: ${Colors.ERROR_COLOR};
  display: flex;
  align-items: center;
  margin: 20px 0;
`;
