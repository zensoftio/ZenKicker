import React, {Component} from 'react';
import {Switch, Route} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import './App.css';
import {getCurrent} from "./actions";

import LoginScene from './scenes/login';
import {MainMenu} from "./components/main-menu";

const AppTest = () => <div>hello</div>

class App extends Component {

	componentDidMount() {
	}

	render() {
		return (
			<div className='container'>
				<MainMenu logoUrl={'/logo/system.png'} isAdmin={isAdmin} actualLogo={actualLogo}/>
				<div className='content'>
					<Switch>
						<Route exact path="/" component={AppTest}/>
						<Route exact path="/login" component={LoginScene}/>
						{/*<Redirect from="/" exact to="/statistics"/>*/}
						{/*<Route path="/not-found" component={NotFoundScene}/>*/}
						{/*<Redirect from="*" exact to="/not-found"/>*/}
						{/*{*/}
						{/*isAdmin ? (*/}
						{/*<Switch>*/}
						{/*<Route path="/campaigns" component={CampaignsScene}/>*/}
						{/*<Route path="/backup-campaigns" component={BackupCampaignsScene}/>*/}
						{/*<Route path="/media" component={MediaScene}/>*/}
						{/*<Route path="/zones" component={ZonesScene}/>*/}
						{/*<Route path="/clients" component={ClientsScene}/>*/}
						{/*<Route path="/not-found" component={NotFoundScene}/>*/}
						{/*<Redirect from="/" exact to="/statistics"/>*/}
						{/*<Redirect from="*" exact to="/not-found"/>*/}
						{/*</Switch>*/}
						{/*) : <Redirect from="*" exact to="/statistics"/>*/}
						{/*}*/}
					</Switch>
				</div>
			</div>

		);
	}
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
	const props = {};
	return props;
}
const mapDispatchToProps = (dispatch) => {
	const actions = {
		getCurrent
	};
	const actionMap = {actions: bindActionCreators(actions, dispatch)};
	return actionMap;
}

export default connect(mapStateToProps, mapDispatchToProps)(App);
