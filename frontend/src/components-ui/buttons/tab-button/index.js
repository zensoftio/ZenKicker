import React from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import {capitalizeString} from '../../../helpers/string-helpers';
import {Colors, MediaViews} from '../../../helpers/style-variables';

const TabButton = ({name, onButtonClick, isActive}) =>
  <Content active={isActive} onClick={onButtonClick}>{capitalizeString(name)}</Content>

export default TabButton;

TabButton.propTypes = {
  name: PropTypes.string.isRequired,
  onButtonClick: PropTypes.func.isRequired,
  isActive: PropTypes.bool.isRequired
}

const Content = styled.div`
  padding: 10px 20px;
  cursor: pointer;
  color: ${props => props.active ? Colors.MAIN_COLOR : 'black'};
  background-color: ${props => props.active ? '#fff' : Colors.THEME_COLOR};
  width: 90px;
  text-align: center;
  &:hover {
    background-color: ${props => props.active ? '#fafafa' : '#fdfdfd'};
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
  }
`;
