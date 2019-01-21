import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import {MainMenuLink} from "../../components-ui/buttons/main-menu-link";
import GameRegistration from '../game-registration';

import ExitToAppIco from '@material-ui/icons/ExitToApp';
import UserPhoto from '../../components-ui/user-photo';
import {Colors} from '../../helpers/style-variables';
import {PlayerModel} from "../../common/global-prop-types";

export const HorizontalMenu = ({id, iconPath, username}) => (
  <Content>
    <Title to="/">Zen<span>Kicker</span></Title>
    <TopBar>
      <Navigation>
        <MainMenuLink link="/players">Players</MainMenuLink>
        <MainMenuLink link="/games">Games</MainMenuLink>
      </Navigation>
      <UserSection>
        <GameRegistration />
        <User to={`/players/${id}`}>
          <Photo>
            <UserPhoto photo={iconPath}/>
          </Photo>
          <Username>{username}</Username>
        </User>
        <a href="/logout"><LogOutIco/></a>
      </UserSection>
    </TopBar>
  </Content>
)

HorizontalMenu.propTypes = PlayerModel.isRequired;

const Content = styled.section`
	width: 100%;
	height: 70px;
	min-height: 70px;
	display: flex;
	align-items: center;
	background-color: ${Colors.THEME_COLOR};
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

const UserSection = styled.div`
  display: flex;
  align-items: center;
  padding: 0 20px;
  
  a {
    display: flex;
  }
`;

const User = styled(Link)`
  min-width: 150px;
  display: flex;
  align-items: center;
  padding-left: 60px;
  color: black;
  text-decoration: none;
  margin-right: 20px;
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
`;

const LogOutIco = styled(ExitToAppIco)`
  color: #000;
`;