import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import {MainMenuLink} from "../../components-ui/buttons/main-menu-link";

import signOutIco from '../../shared/images/icons/sign-out.png';
import UserPhoto from '../../components-ui/user-photo';
import {Colors} from '../../helpers/style-variables';

export const MainMenu = ({currentUser}) => (
	<Content>
		<Title to="/">Zen<span>Kicker</span></Title>
    <AuthenticationContent>
      <div>
        <UserPhoto photo={currentUser.iconPath}/>
        <Username to={`/players/${currentUser.id}`}>{currentUser.username}</Username>
      </div>
      <a href="/logout"><SignOut/></a>
    </AuthenticationContent>
		<MainMenuLink link="/dashboard">Dashboard</MainMenuLink>
	</Content>
)

const Content = styled.section`
	min-width: 300px;
	height: 100%;
`;

const Title = styled(Link)`
	width: 100%;
	font-size: 22px;
	box-sizing: border-box;
	padding: 20px;
	margin-bottom: 20px;
	display: block;
	color: black;
	text-decoration: none;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	text-align: center;
	&>span {
		color: ${Colors.MAIN_COLOR};
	}
`;

const AuthenticationContent = styled.div`
  display: flex;
  padding: 0 0 30px 10px;
  justify-content: space-between;
  align-items: center;
  div {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  img {
    max-width: 30px;
    min-width: 30px;
    max-height: 30px;
    min-height: 30px;
  }
`;

const Username = styled(Link)`
  color: black;
  text-decoration: none;
  padding-left: 15px;
  max-width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
	&:hover {
		text-decoration: underline;
	}
	margin-right: 20px;
`;

const SignOut = styled.div`
  width: 17px;
  height: 17px;
  background-image: url(${signOutIco});
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
`;