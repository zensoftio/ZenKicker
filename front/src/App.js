import React, {Component} from 'react';
import {Switch, Route, Redirect, withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import styled from 'styled-components';
import {getCurrent} from "./actions";
import {MainMenu} from "./components/main-menu";
import DashboardScene from "./scenes/DashboardScene";
import ProfileScene from "./scenes/ProfileScene";
import GameRegistration from './components/game-registration';

const NotFound = () => <div>not found</div>

const Container = styled.div`
	height: 100vh;
  width: 100%;
  display: flex;
  position: relative;
`;

const Content = styled.div`
	width: 100%;
	padding: 50px;
	padding-top: 0;
	box-sizing: border-box;
`;

const MainContentContainer = styled.div`
	width: 100%;
`;

const GameRegistrationContainer = styled.div`
	width: 100%;
	display: flex;
	justify-content: flex-end;
	padding: 20px;
	box-sizing: border-box;
`;

class App extends Component {

	componentDidMount() {
	  this.props.actions.getCurrent();
	}

	render() {
	  const {currentUser} = this.props;
	  if (!currentUser) {
	    return null
    }
		return (
			<Container>
				<MainMenu currentUser={currentUser}/>
        <MainContentContainer>
          <GameRegistrationContainer>
            <GameRegistration players={this.props.players ? this.props.players.list : []} registerGame={this.props.actions.registerGame}/>
          </GameRegistrationContainer>

          <Content>
            <Switch>
              <Route exact path="/dashboard" component={DashboardScene}/>
              <Route exact path="/player/:id" component={ProfileScene}/>
              <Route path="/not-found" component={NotFound}/>
              <Redirect from="/" exact to="/dashboard"/>
              <Redirect from="*" exact to="/not-found"/>
            </Switch>
          </Content>
        </MainContentContainer>
			</Container>

		);
	}
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
	const props = {
    currentUser: state.user.current,
    players: state.user.players
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
