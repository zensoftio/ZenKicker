import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import {MainMenuLink} from "../../components-ui/buttons/main-menu-link";
import GameRegistration from '../game-registration';

import signOutIco from '../../shared/images/icons/sign-out.png';
import UserPhoto from '../../components-ui/user-photo';
import {Colors} from '../../helpers/style-variables';

export const HorizontalMenu = ({id, iconName, username}) => (
  <Content>
    <Title to="/">Zen<span>Kicker</span></Title>
    <TopBar>
      <Navigation>
        <MainMenuLink link="/players">Players</MainMenuLink>
        <MainMenuLink link="/games">Games</MainMenuLink>
      </Navigation>
      <RightSection>
        <GameRegistration />
        <User to={`/player/${id}`}>
          <Photo>
            <UserPhoto photo={iconName}/>
          </Photo>
          <Username>{username}</Username>
        </User>
        <a href="/logout"><SignOut/></a>
      </RightSection>
    </TopBar>
  </Content>
)

const Content = styled.section`
	width: 100%;
	height: 70px;
	min-height: 70px;
	display: flex;
	align-items: center;
	position: fixed;
	background-color: ${Colors.THEME_COLOR};
	z-index: 10;
`;

const Title = styled(Link)`
	width: 300px;
	font-size: 22px;
	box-sizing: border-box;
	padding: 20px;
	display: block;
	color: ${Colors.MAIN_COLOR};
	text-decoration: none;
	text-align: center;
	&>span {
		color: black;
	}
`;

const TopBar = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
`;

const Navigation = styled.div`
  display: flex;
  padding: 0 40px;
  margin-left: 200px;
`;

const RightSection = styled.div`
  display: flex;
  align-items: center;
  padding: 0 20px;  
`;

const User = styled(Link)`
  min-width: 150px;
  display: flex;
  align-items: center;
  padding: 0 20px 0 60px;
  color: black;
  text-decoration: none;
	&:hover {
		text-decoration: underline;
	}
`;

const Photo = styled.div`
  display: flex;
  align-items: center;
  overflow: hidden;
  max-width: 40px;
  min-width: 40px;
  max-height: 40px;
  min-height: 40px;
  justify-content: center;
  border-radius: 100%;
`;

const Username = styled.div`
  padding-left: 15px;
  max-width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
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
