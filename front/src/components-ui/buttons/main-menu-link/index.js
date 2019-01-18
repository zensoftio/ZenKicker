import React from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import {NavLink} from 'react-router-dom';
import {Colors, MediaViews} from '../../../helpers/style-variables';

export const MainMenuLink = ({children, link, onClick}) => (
	<LinkButton exact to={link} activeClassName='active' onClick={onClick ? onClick : null}>
		<MenuItem>{children.replace('_', ' ')}</MenuItem>
	</LinkButton>
)

MainMenuLink.propTypes = {
  children: PropTypes.string.isRequired,
  onClick: PropTypes.func,
  link: PropTypes.string.isRequired
}

const LinkButton = styled(NavLink)`
  padding: 22px 25px 10px 25px;
  margin: 0 10px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  cursor: pointer;
  background: fade_out(black, 0.5);
  border-bottom: 3px solid #efefef;
  text-decoration: none;
  width: 100%;

  &:hover, &.active {
		border-bottom: solid 3px ${Colors.MAIN_COLOR};
    span{
      opacity: 1;
    }
  }
  
  @media (max-width: ${MediaViews.MOBILE}px) {
  	border-left: 3px solid #efefef;
  	border-bottom: none;
  	padding: 5px 25px;
  	margin: 0 0 20px 0;
  
    &:hover, &.active {
			border-left: solid 3px ${Colors.MAIN_COLOR};
			border-bottom: none;
		}
  }
`;

const MenuItem = styled.span`
  font-weight: 400;
  color: black;
  opacity: 0.5;
  text-transform: capitalize;
`;
