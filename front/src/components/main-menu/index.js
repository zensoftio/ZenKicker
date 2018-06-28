import * as React from 'react'
import {MainMenuLink} from '../../components/buttons/main_menu_link/index'
import LogoMenu from "../../../components/logo_menu";

const styles = require('./index.scss')

export const MainMenu = ({isAdmin, actualLogo, logoUrl}) => (
	<section>
		<LogoMenu logoUrl={logoUrl} actualLogo={actualLogo}/>
		<MainMenuLink link="/statistics">statistics</MainMenuLink>
		<MainMenuLink link="/campaigns">campaigns</MainMenuLink>
		<MainMenuLink link="/backup-campaigns">backup_campaigns</MainMenuLink>
		<MainMenuLink link="/media">media</MainMenuLink>
		<MainMenuLink link="/zones">zones</MainMenuLink>
		<MainMenuLink link="/clients">clients</MainMenuLink>
	</section>
)
