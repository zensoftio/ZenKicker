import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import {Input} from '../../components-ui/input';
import {updatePassword} from '../../actions/user';
import Popup from '../popup';
import {Colors} from '../../helpers/style-variables';

class PasswordBlock extends Component {
  popupChild = React.createRef();

  state = {
    newPassword: '',
    currentPassword: '',
    newPasswordConfirm: '',
    passwordError: null,
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
    const {newPassword, currentPassword, newPasswordConfirm} = this.state;
    if (newPassword === '' || currentPassword === '' || newPasswordConfirm === '')
      return this.setState({passwordError: 'All fields are required'});
    if (newPassword !== newPasswordConfirm)
      return this.setState({passwordError: 'Passwords doesn\'t match'});
    try {
      const data = {
        currentPassword: currentPassword,
        newPassword: newPassword
      };
      await updatePassword(data);
      this.clearValues();
      this.popupChild.current.onPopupClose()
    } catch (err) {
      const error = err.response.data.message;
      this.setState({passwordError: error});
    }
  }

  isButtonDisabled = () => {
    const {newPassword, currentPassword, newPasswordConfirm, passwordError} = this.state;
    return !newPassword || !newPasswordConfirm || !!passwordError || !currentPassword || (newPassword !== newPasswordConfirm)
  }

  onCancelClick = () => {
    this.clearValues();
    this.popupChild.current.onPopupClose()
  }

  render() {
    const {newPassword, currentPassword, passwordError, newPasswordConfirm} = this.state;

    return (
      <Popup buttonTitle='Change password' ref={this.popupChild} clearValues={this.clearValues}>
        <div>
          <PopupTitle>Change password</PopupTitle>
          <Input value={currentPassword} onChange={(e) => this.onCurrentPasswordChange(e.target.value)}
                 placeholder='Enter old password' type='password'/>
          <Input value={newPassword} onChange={(e) => this.onNewPasswordChange(e.target.value)}
                 placeholder='Enter new password' type='password'/>
          <Input value={newPasswordConfirm} onChange={(e) => this.onNewPasswordConfirmChange(e.target.value)}
                 placeholder='Confirm new password' type='password'/>
          <PasswordError>{passwordError}</PasswordError>
          <ButtonsContainer>
            <Button onClick={this.onChangePasswordClick} isDisabled={this.isButtonDisabled()}>Confirm</Button>
            <Indent/>
            <Button onClick={this.onCancelClick}>Cancel</Button>
          </ButtonsContainer>
        </div>
      </Popup>
    )
  }
}

export default PasswordBlock;

const PopupTitle = styled.div`
  font-size: 1.5em;
  margin-bottom: 40px;
`;

const ButtonsContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const PasswordError = styled.span`
  color: ${Colors.ERROR_COLOR};
  display: flex;
  align-items: center;
  margin: 20px 0;
`;

const Indent = styled.div`
  width: 20px;
`;