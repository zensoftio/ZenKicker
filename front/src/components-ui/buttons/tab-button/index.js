import React from 'react';
import styled from 'styled-components';
import {capitalizeString} from '../../../helpers/string-helpers';
import {Colors} from '../../../helpers/style-variables';

const Content = styled.div`
  padding: 10px 20px;
  cursor: pointer;
  color: ${props => props.active ? Colors.MAIN_COLOR : 'black'};
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