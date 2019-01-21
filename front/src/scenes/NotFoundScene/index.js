import React from 'react';
import styled from 'styled-components';
import {Colors} from '../../helpers/style-variables';

const NotFoundScene = () => (
  <Content>
    <ErrorCode>404</ErrorCode>
    <ErrorText>WHAT ARE YOU DOING, <span>SICK BASTARD</span>?</ErrorText>
    <ErrorText>GET AWAY FROM HERE!</ErrorText>
  </Content>
)
export default NotFoundScene;

const Content = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 80px 0;
`;

const ErrorCode = styled.div`
  font-size: 15em;
  color: ${Colors.ERROR_COLOR};
`;

const ErrorText = styled.div`
  font-size: 2em;
  span {
    font-weight: bold;
  }
`;
