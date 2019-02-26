import React, {Component} from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import Popup from '../popup';

import SettingsIco from '@material-ui/icons/Settings';
import {MediaViews} from "../../helpers/style-variables";
import ProfilePhotoBlock from "../profile-photo-block";
import PlayerEditInfo from "../player-edit-info";
import {updateFullName} from "../../actions";
import {updateEmail, updatePassword, updatePhoto} from "../../actions/user";

class EditProfileInfo extends Component {

  popupChild = React.createRef();

  state = {
    activeTab: 'Full Name',
    fullName: this.props.fullName,
    currentPassword: '',
    newPassword: '',
    newPasswordConfirm: '',
    email: this.props.email,
    fileError: null,
    passwordError: null,
    emailError: null,
    fullNameError: null
  };

  onFullNameChange = (value) => this.setState({
    fullName: value,
    fullNameError: null
  });
  onCurrentPasswordChange = (value) => this.setState({
    currentPassword: value,
    passwordError: null
  });
  onNewPasswordChange = (value) => this.setState({
    newPassword: value,
    passwordError: null
  });
  onNewPasswordConfirmChange = (value) => this.setState({
    newPasswordConfirm: value,
    passwordError: null
  });
  onEmailChange = (value) => this.setState({
    email: value,
    emailError: null
  });

  onPhotoChange = async (file) => {
    try {
      const data = new FormData();
      data.append('file', file);
      await updatePhoto(data);
      this.setState({fileError: null});
      this.props.getPlayer(this.props.playerId);
      this.props.getCurrent();
    } catch (e) {
      this.setState({fileError: e.response.data.message});
      console.log({e});
    }
  };

  onSave = async () => {
    const {fullName, email, currentPassword, newPassword, newPasswordConfirm} = this.state;

    if (fullName !== this.props.fullName) {
      try {
        await updateFullName({fullName});
        this.setState({fullNameError: null});
        this.props.getCurrent();
        this.props.getPlayer(this.props.playerId);
      } catch (e) {
        const error = e.response.data.errors ? `Full Name ${e.response.data.errors[0].message}` : e.response.data.message;
        this.setState({fullNameError: error});
        console.log({e});
      }
    }
    if (email !== this.props.email) {
      try {
        await updateEmail({email});
        this.setState({emailError: null});
        this.props.getPlayer(this.props.playerId);
      } catch (e) {
        const error = e.response.data.errors ? `Email ${e.response.data.errors[0].message}` : e.response.data.message;
        this.setState({emailError: error});
        console.log({e});
      }
    }
    if (currentPassword && newPasswordConfirm === newPassword) {
      try {
        await updatePassword({
          newPassword,
          currentPassword
        });
        this.setState({
          passwordError: null,
          currentPassword: '',
          newPasswordConfirm: '',
          newPassword: '',
        });
      } catch (e) {
        const error = e.response.data.errors ? `Fields ${e.response.data.errors[0].message}` : e.response.data.message;
        this.setState({passwordError: error});
        console.log({e});
      }
    }
    if (newPasswordConfirm !== newPassword) {
      this.setState({passwordError: 'The passwords doesn\'t match'});
    }
  };

  clearValues = () => {
    this.setState({
      fullName: this.props.fullName,
      currentPassword: '',
      newPassword: '',
      newPasswordConfirm: '',
      email: this.props.email,
      fileError: null,
      passwordError: null,
      emailError: null,
      fullNameError: null
    });
  };

  onCancel = () => {
    this.popupChild.current.onPopupClose();
    this.clearValues();
  };

  isButtonDisabled = () => {
    const {
      fullName, email, currentPassword, newPassword, newPasswordConfirm, passwordError, emailError, fullNameError
    } = this.state;

    if (!!passwordError || !!emailError || !!fullNameError) return true;
    if ((fullName === this.props.fullName) && (email === this.props.email) && !currentPassword) return true;
    return !fullName && !email && !currentPassword && !newPassword && !newPasswordConfirm;

  };


  render() {
    const {
      fullName, email, currentPassword, newPassword, newPasswordConfirm, passwordError, emailError, fullNameError, fileError
    } = this.state;
    return (

      <Content>
        <Popup buttonIcon={<SettingsIco/>} ref={this.popupChild} clearValues={() => this.clearValues()}
               loadData={this.setDefaultValues}>
          <div>
            <PopupTitle>
              Edit info
            </PopupTitle>
            <EditContainer>
              <ProfilePhotoBlock onPhotoChange={(e) => this.onPhotoChange(e.target.files[0])}
                                 iconPath={this.props.iconPath}
                                 error={fileError}/>
              <PlayerEditInfo fullName={fullName} onFullNameChange={this.onFullNameChange} email={email}
                              onEmailChange={this.onEmailChange} currentPassword={currentPassword}
                              newPassword={newPassword} newPasswordConfirm={newPasswordConfirm}
                              fullNameError={fullNameError}
                              onCurrentPasswordChange={this.onCurrentPasswordChange} passwordError={passwordError}
                              onNewPasswordChange={this.onNewPasswordChange} emailError={emailError}
                              onNewPasswordConfirmChange={this.onNewPasswordConfirmChange}/>
            </EditContainer>
            <ButtonsContainer>
              <Button isDisabled={this.isButtonDisabled()} onClick={() => this.onSave()}>Save</Button>
              <Indent/>
              <Button onClick={() => this.onCancel()}>Cancel</Button>
            </ButtonsContainer>
          </div>
        </Popup>
      </Content>

    );
  }
}

export default EditProfileInfo;

EditProfileInfo.propTypes = {
  iconPath: PropTypes.string,
  playerId: PropTypes.string.isRequired
};

const Content = styled.div``;

const PopupTitle = styled.div`
  font-size: 1.5em;
  margin-bottom: 30px;
`;

const ButtonsContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;
const Indent = styled.div`
  width: 20px;
`;

const EditContainer = styled.div`
  display: flex;
  min-width: 400px;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  @media (max-width: ${MediaViews.MOBILE}px) {
    flex-direction: column;
    align-items: center;
    min-width: calc(100vw - 50px);
  }
`;
