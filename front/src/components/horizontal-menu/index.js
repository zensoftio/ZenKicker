import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import {MainMenuLink} from "../../components-ui/buttons/main-menu-link";
import GameRegistration from '../game-registration';

import signOutIco from '../../shared/images/icons/sign-out.png';
const defaultPhoto = 'https://www.hgvrecruitmentcentre.co.uk/wp-content/uploads/2018/04/1_MccriYX-ciBniUzRKAUsAw.png';

const Content = styled.section`
	width: 100%;
	height: 60px;
	min-height: 60px;
	display: flex;
	align-items: center;
`;

const Title = styled(Link)`
	width: 300px;
	font-size: 22px;
	box-sizing: border-box;
	padding: 20px;
	display: block;
	color: black;
	text-decoration: none;
	text-align: center;
	&>span {
		color: red;
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
  img {
    max-width: 40px;
    min-width: 40px;
    max-height: 40px;
    min-height: 40px;
    border-radius: 100%;
  }
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

export const HorizontalMenu = ({currentUser}) => (
  <Content>
    <Title to="/">Zen<span>Kicker</span></Title>
    <TopBar>
      <Navigation>
        <MainMenuLink link="/players">Players</MainMenuLink>
        <MainMenuLink link="/games">Games</MainMenuLink>
      </Navigation>
      <RightSection>
        <GameRegistration />
        <User to={`/player/${currentUser.id}`}>
          {
            currentUser.iconName ?
              <img alt="avatar" src={`http://localhost/images/icons/${currentUser.iconName}`}/> :
              <img alt="avatar" src={defaultPhoto} />
          }
          <Username>{currentUser.username}</Username>
        </User>
        <a href="/logout"><SignOut/></a>
      </RightSection>
    </TopBar>
  </Content>
)
