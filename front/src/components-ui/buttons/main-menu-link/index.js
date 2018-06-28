import React from 'react';
import styled from 'styled-components';
import {NavLink} from 'react-router-dom';

const LinkButton = styled(NavLink)`
	width: 100%;
  padding: 12px 25px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  cursor: pointer;
  background: fade_out(black, 0.5);
  border-left: 3px solid transparent;
  text-decoration: none;

  &:hover, &.active{
		border-left: solid 3px red;
		
    span{
      opacity: 1;
    }
  }
`;

const MenuItem = styled.span`
  line-height: 20px;
  font-weight: 400;
  color: black;
  opacity: 0.5;
  text-transform: capitalize;
  left: 8px;
`;

export const MainMenuLink = ({children, link}) => (
	<LinkButton to={link} activeClassName='active'>
		<MenuItem>{children.replace('_', ' ')}</MenuItem>
	</LinkButton>
)
