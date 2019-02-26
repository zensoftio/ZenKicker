import React from 'react';
import PropTypes from "prop-types";
import {Input} from '../../components-ui/input';
import styled from "styled-components";
import {Colors, MediaViews} from "../../helpers/style-variables";

const EmailBlock = ({email, onEmailChange, error}) => (

  <Content>
    <Label>Email: <ErrorText>{error}</ErrorText></Label>
    <Input value={email} onChange={onEmailChange} placeholder='Enter new email' type='text'/>
  </Content>

);

EmailBlock.propTypes = {
  email: PropTypes.string.isRequired,
  onEmailChange: PropTypes.func.isRequired,
  error: PropTypes.string,
};

export default EmailBlock;

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
