import React from 'react';
import styled from 'styled-components';
import {capitalizeString} from '../../../helpers/string-helpers';

const Content = styled.div`
  padding: 20px;
  cursor: pointer;
  color: ${props => props.active ? 'red' : 'black'};
  background-color: ${props => props.active ? '#fafafa' : '#fff'};
  width: 90px;
  text-align: center;
  &:hover {
    background-color: ${props => props.active ? '#fafafa' : '#fdfdfd'};
  }
`;

const TabButton = ({name, onButtonClick, isActive}) =>
  <Content active={isActive} onClick={onButtonClick}>{capitalizeString(name)}</Content>

export default TabButton;