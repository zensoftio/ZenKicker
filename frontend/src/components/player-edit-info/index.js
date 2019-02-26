import React from 'react';
import UsernameBlock from "../username-block";
import PasswordBlock from "../password-block";
import EmailBlock from "../email-block";
import PropTypes from "prop-types";
import styled from "styled-components";

const PlayerEditInfo = ({
                          fullName, currentPassword, newPassword, newPasswordConfirm, email, onFullNameChange, onEmailChange, onCurrentPasswordChange, onNewPasswordChange, onNewPasswordConfirmChange, passwordError, fullNameError, emailError
                        }) => (

  <Content>
    <UsernameBlock fullName={fullName} onChange={(e) => onFullNameChange(e.target.value)} error={fullNameError}/>
    <EmailBlock email={email} onEmailChange={(e) => onEmailChange(e.target.value)} error={emailError}/>
    <PasswordBlock currentPassword={currentPassword} newPassword={newPassword} newPasswordConfirm={newPasswordConfirm}
                   onCurrentPasswordChange={(e) => onCurrentPasswordChange(e.target.value)}
                   onNewPasswordChange={(e) => onNewPasswordChange(e.target.value)} error={passwordError}
                   onNewPasswordConfirmChange={(e) => onNewPasswordConfirmChange(e.target.value)}/>
  </Content>

);


export default PlayerEditInfo;

PlayerEditInfo.propTypes = {
  fullName: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
  onEmailChange: PropTypes.func.isRequired,
  currentPassword: PropTypes.string.isRequired,
  newPassword: PropTypes.string.isRequired,
  newPasswordConfirm: PropTypes.string.isRequired,
  passwordError: PropTypes.string,
  fullNameError: PropTypes.string,
  emailError: PropTypes.string,
  onCurrentPasswordChange: PropTypes.func.isRequired,
  onNewPasswordChange: PropTypes.func.isRequired,
  onNewPasswordConfirmChange: PropTypes.func.isRequired,
};

const Content = styled.div`
  margin-top: 20px;
  width: 100%;
`;
