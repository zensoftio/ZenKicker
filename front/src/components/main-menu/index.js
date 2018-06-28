import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import {MainMenuLink} from "../../components-ui/buttons/main-menu-link";
import {MenuGroup} from "../../components-ui/menu-group";

const Content = styled.section`
	width: 300px;
	height: 100%;
`;

const Title = styled(Link)`
	with: 100%;
	font-size: 22px;
	padding: 20px;
	margin-bottom: 20px;
	display: block;
	color: black;
	text-decoration: none;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	text-align: center;
	&>span {
		color: red;
	}
`;

export const MainMenu = () => (
	<Content>
		<Title to="/">Zen<span>Kicker</span></Title>
		<MainMenuLink link="/rating">Rating</MainMenuLink>
		<MenuGroup title="Tournament">
			<MainMenuLink link="/group-stage">Group stage</MainMenuLink>
			<MainMenuLink link="/playoffs">Playoffs</MainMenuLink>
		</MenuGroup>
	</Content>
)
