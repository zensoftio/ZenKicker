import React, {Component} from 'react';
import {Switch, Route, Redirect, withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import styled from 'styled-components';
import {getCurrent} from "./actions";
import {MainMenu} from "./components/main-menu";
import RatingScene from "./scenes/RatingScene";
import ProfileScene from "./scenes/ProfileScene";

const NotFound = () => <div>not found</div>

const Container = styled.div`
	height: 100vh;
  width: 100%;
  display: flex;
`;

const Content = styled.div`
	width: 100%;
	padding: 20px;
	padding-top: 0;
`;


class App extends Component {

	componentDidMount() {
	  this.props.actions.getCurrent();
	}

	render() {
	  const {currentUser} = this.props;
	  if (!currentUser.username) {
	    return null
    }
		return (
			<Container>
				<MainMenu currentUser={currentUser}/>
				<Content>
					<Switch>
						<Route exact path="/rating" component={RatingScene}/>
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
	const props = {
    currentUser: state.user.current
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
