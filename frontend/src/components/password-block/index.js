import React from 'react';
import PropTypes from "prop-types";
import {Input} from '../../components-ui/input';
import styled from "styled-components";
import {Colors, MediaViews} from "../../helpers/style-variables";

const PasswordBlock = ({
                         currentPassword, newPassword, newPasswordConfirm, onCurrentPasswordChange, onNewPasswordChange, onNewPasswordConfirmChange, error
                       }) => (

  <Content>
    <Label>Password: <ErrorText>{error}</ErrorText></Label>
    <Input value={currentPassword} onChange={onCurrentPasswordChange}
           placeholder='Enter current password' type='password'/>
    <Input value={newPassword} onChange={onNewPasswordChange}
           placeholder='Enter new password' type='password'/>
    <Input value={newPasswordConfirm} onChange={onNewPasswordConfirmChange}
           placeholder='Confirm new password' type='password'/>
  </Content>

);

PasswordBlock.propTypes = {
  currentPassword: PropTypes.string.isRequired,
  newPassword: PropTypes.string.isRequired,
  newPasswordConfirm: PropTypes.string.isRequired,
  error: PropTypes.string,
  onCurrentPasswordChange: PropTypes.func.isRequired,
  onNewPasswordChange: PropTypes.func.isRequired,
  onNewPasswordConfirmChange: PropTypes.func.isRequired,
};

export default PasswordBlock;

const Content = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
  margin-bottom: 20px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    margin-bottom: 10px;
  }
`;

const Label = styled.div`
  padding-left: 10px;
  display: flex;
  align-items: center;
`;

const ErrorText = styled.div`
  color: ${Colors.ERROR_COLOR};
  padding: 0 10px;
`;
