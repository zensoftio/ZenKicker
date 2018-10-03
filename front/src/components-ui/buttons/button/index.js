import React from 'react';
import styled from 'styled-components';

const Content = styled.div`
	width: max-content;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  padding: 12px 25px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  cursor: pointer;
  border-left: solid 3px red;

  &:hover {
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  }
  &:active {
    border-left: solid 3px green;
  }  
`;

export const Button = ({children, onClick}) => (
  <Content onClick={onClick}>
    {children}
  </Content>
)
