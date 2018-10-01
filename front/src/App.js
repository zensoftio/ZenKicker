import React, {Component} from 'react';
import {Switch, Route, Redirect, withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import styled from 'styled-components';
import {getCurrent} from "./actions";
import {MainMenu} from "./components/main-menu";
import RatingScene from "./scenes/RatingScene";
import GroupStageScene from "./scenes/GroupStageScene";
import PlayoffsScene from "./scenes/PlayoffsScene";
import ProfileScene from "./scenes/ProfileScene";

const NotFound = () => <div>not found</div>

const Container = styled.div`
	height: 100vh;
  width: 100%;
  display: flex;
`;

const Content = styled.div`
	width: 100%;
`;


class App extends Component {

	componentDidMount() {
	  this.props.actions.getCurrent();
	}

	render() {
		return (
			<Container>
				<MainMenu />
				<Content>
					<Switch>
						<Route exact path="/rating" component={RatingScene}/>
						<Route exact path="/group-stage" component={GroupStageScene}/>
						<Route exact path="/playoffs" component={PlayoffsScene}/>
						<Route exact path="/player/:id" component={ProfileScene}/>
						<Route path="/not-found" component={NotFound}/>
						<Redirect from="/" exact to="/rating"/>
						<Redirect from="*" exact to="/not-found"/>
					</Switch>
				</Content>
			</Container>

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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));
