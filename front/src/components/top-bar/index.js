import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';

import signOutIco from '../../shared/images/icons/sign-out.png';
import {getTitleFromUrl} from '../../helpers/string-helpers';

const Content = styled.section`
	width: 100%;
	height: 70px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 20px;
	box-sizing: border-box;
`;

const MainTitle = styled.div`
	font-size: 1.8em;
	padding-left: 200px;
`;

const Username = styled(Link)`
  color: black;
  text-decoration: none;
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

const AuthenticationContent = styled.div`
  display: flex;
`;

export const TopBar = ({currentUser, match}) => (
  <Content>
    <MainTitle>{getTitleFromUrl(match.url)}</MainTitle>
    <AuthenticationContent>
      <Username to={`/player/${currentUser.id}`}>{currentUser.username}</Username>
      <a href="/logout"><SignOut/></a>
    </AuthenticationContent>
  </Content>
)
