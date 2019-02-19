import React from 'react';
import Config from 'react-global-configuration';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import {MainMenuLink} from "../../components-ui/buttons/main-menu-link";
import GameRegistration from '../game-registration';

import ExitToAppIco from '@material-ui/icons/ExitToApp';
import UserPhoto from '../../components-ui/user-photo';
import {Colors} from '../../helpers/style-variables';

import MenuIco from '@material-ui/icons/Menu';
import CloseIco from '@material-ui/icons/Close';
import {PlayerModel} from "../../common/global-prop-types";

class MobileMenu extends React.Component {
  state = {
    isOpen: false
  }

  onMenuClick = () => this.setState({isOpen: !this.state.isOpen});
  onMenuClose = () => this.setState({isOpen: false});

  handleOnBackgroundClick = (e) => {
    if (e.target.classList.contains(Content.styledComponentId)) {
      this.onMenuClose();
    }
  }

  signIn = () => window.location.href = Config.get('login_callback')

  render() {
    const {currentUser} = this.props;
    return(
      <Content onClick={this.handleOnBackgroundClick}>
        <Title to="/">Zen<span>Kicker</span></Title>
        <MenuButton onClick={this.onMenuClick}>{this.state.isOpen ?  <CloseIco /> : <MenuIco />}</MenuButton>
        {
          this.state.isOpen &&
          <TopBar>
            {
              currentUser ?
                <UserSection>
                  <User to={`/players/${currentUser.id}`} onClick={this.onMenuClose}>
                    <Photo>
                      <UserPhoto photo={currentUser.iconPath}/>
                    </Photo>
                    <Username>{currentUser.fullName}</Username>
                  </User>
                  <a href="/logout"><LogOutIco/></a>
                </UserSection> :
                <UserSection>
                  <SignIn onClick={this.signIn}>Sign In</SignIn>
                </UserSection>
            }
            {
              currentUser && <GameRegistration />
            }
            <Navigation>
              <MainMenuLink link="/players"  onClick={this.onMenuClose}>Players</MainMenuLink>
              <MainMenuLink link="/games"  onClick={this.onMenuClose}>Games</MainMenuLink>
            </Navigation>
          </TopBar>
        }
      </Content>
    )
  }
}

export default MobileMenu;

MobileMenu.propTypes = {
  currentUser: PlayerModel
}

const Content = styled.div`
	width: 100%;
	height: 50px;
	min-height: 50px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: ${Colors.THEME_COLOR};
	box-sizing: border-box;
	padding: 0 20px;
`;

const Title = styled(Link)`
	width: max-content;
	font-size: 1.5em;
	display: block;
	color: ${Colors.MAIN_COLOR};
	text-decoration: none;
	&>span {
		color: black;
	}
`;

const MenuButton = styled.div`
	display: flex;
	align-items: center;
	justify-content: center;
`;

const TopBar = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	position: fixed;
	box-sizing: border-box;
	align-items: flex-start;
	top: 50px;
	left: 0;
	right: 0;
	padding: 20px;
	height: calc(100vh - 50px);
	z-index: 2;
	background-color: ${Colors.THEME_COLOR};
`;

const Navigation = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
  margin-top: 20px;
`;

const UserSection = styled.div`
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
`;

const User = styled(Link)`
  display: flex;
  width: 100%;
  align-items: center;
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
  overflow: hidden;
  text-overflow: ellipsis;
	margin: 0 20px;
`;

const LogOutIco = styled(ExitToAppIco)`
  color: #000;
`;

const SignIn = styled.div`
  color: ${Colors.MAIN_COLOR};
  cursor: pointer;
  &:hover {
    text-decoration: underline;
  }
`;
