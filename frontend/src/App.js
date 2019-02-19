import React, {Component} from 'react';
import {Switch, Route, Redirect, withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import styled from 'styled-components';
import {
	getCurrent
} from "./actions";
import DashboardScene from "./scenes/DashboardScene";
import PlayersScene from "./scenes/PlayersScene";
import GamesScene from "./scenes/GamesScene";
import ProfileScene from "./scenes/ProfileScene";
import {HorizontalMenu} from './components/horizontal-menu';
import NotFoundScene from './scenes/NotFoundScene';
import {MediaViews} from "./helpers/style-variables";
import MobileMenu from "./components/mobile-menu";

class App extends Component {

	componentDidMount() {
		this.props.actions.getCurrent();
	}

	render() {
		const {currentUser} = this.props;
		const isMobile = window.outerWidth <= MediaViews.MOBILE;

		return (
			<Container>
				{
					isMobile ? <MobileMenu currentUser={currentUser}/> :
						<HorizontalMenu currentUser={currentUser}/>
				}
				<Content>
					<Switch>
						<Route exact path="/dashboard" component={DashboardScene}/>
						<Route exact path="/players" component={PlayersScene}/>
						<Route exact path="/games" component={GamesScene}/>
						<Route exact path="/players/:id" component={ProfileScene}/>
						<Route path="/not-found" component={NotFoundScene}/>
						<Redirect from="/" exact to="/dashboard"/>
						<Redirect from="*" exact to="/not-found"/>
					</Switch>
				</Content>
			</Container>
		);
	}
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
	const props = {
		currentUser: state.user.current,
	};
	return props;
}
const mapDispatchToProps = (dispatch) => {
	const actions = {
		getCurrent
	};
	const actionMap = {actions: bindActionCreators(actions, dispatch)};
	return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));

const Container = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Content = styled.div`
	width: 100%;
	padding: 50px 50px 10px 50px;
	box-sizing: border-box;
	display: flex;
	justify-content: center;
  height: calc(100vh - 70px);
  overflow-y: auto;
	
	&>div {
    height: max-content;
	}
	
	@media (max-width: ${MediaViews.MOBILE}px) {
	  height: calc(100vh - 50px);
	  padding: 20px 20px 0 20px;
  }
`;