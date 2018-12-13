import React from 'react';
import styled from 'styled-components';
import {Colors} from '../../../helpers/style-variables';

export const Button = ({children, onClick, isDisabled = false, buttonIcon = null}) => (
  <Content isDisabled={isDisabled} onClick={onClick}>
    {buttonIcon && <Icon>{buttonIcon}</Icon>}
    {children}
  </Content>
)

const Content = styled.div`
	width: max-content;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  padding: 10px 20px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  cursor: pointer;
  
  pointer-events: ${({isDisabled}) => isDisabled ? 'none' : 'auto'};
  color: ${({isDisabled}) => isDisabled ? Colors.Button.DISABLED_TEXT_COLOR : Colors.Button.TEXT_COLOR};

  &:hover {
    box-shadow: 0 3px 4px rgba(0,0,0,0.1);
  }
  &:active {
    box-shadow: 0 3px 4px rgba(0,0,0,0.2);
  }
`;

const Icon =  styled.div`
  margin-right: 10px;
  display: flex;
`;