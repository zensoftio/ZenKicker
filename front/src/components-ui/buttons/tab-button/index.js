import React from 'react';
import styled from 'styled-components';
import {capitalizeString} from '../../../helpers/string-helpers';

const Content = styled.div`
  padding: 0 10px;
  cursor: pointer;
  color: ${props => props.active ? 'red' : 'black'};
`;

const TabButton = ({name, onButtonClick, isActive}) =>
  <Content active={isActive} onClick={onButtonClick}>{capitalizeString(name)}</Content>

export default TabButton;