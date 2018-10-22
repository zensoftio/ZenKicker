import React, {Component} from 'react';
import {Switch, Route, Redirect, withRouter} from 'react-router-dom';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import styled from 'styled-components';
import {
	getCurrent,
  getAllPlayers,
  getActivePlayers,
  getLatestGames,
  getAllGames
} from "./actions";
import DashboardScene from "./scenes/DashboardScene";
import PlayersScene from "./scenes/PlayersScene";
import GamesScene from "./scenes/GamesScene";
import ProfileScene from "./scenes/ProfileScene";
import {HorizontalMenu} from './components/horizontal-menu';

const NotFound = () => <div>not found</div>

class App extends Component {

	componentDidMount() {
	  this.props.actions.getCurrent();
	  this.props.actions.getAllPlayers();
	}

	render() {
	  const {currentUser} = this.props;

	  if (!currentUser || !currentUser.username) {
	    return null
    }
		return (
			<Container>
				<HorizontalMenu currentUser={currentUser}/>
        <Content>
          <Switch>
            <Route exact path="/dashboard" component={DashboardScene}/>
            <Route exact path="/players" component={PlayersScene}/>
            <Route exact path="/games" component={GamesScene}/>
            <Route exact path="/player/:id" component={ProfileScene}/>
            <Route path="/not-found" component={NotFound}/>
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
    players: state.player.players
  };
	return props;
}
const mapDispatchToProps = (dispatch) => {
	const actions = {
		getCurrent,
    getAllPlayers,
    getActivePlayers,
    getLatestGames,
    getAllGames
	};
	const actionMap = {actions: bindActionCreators(actions, dispatch)};
	return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(App));

const Container = styled.div`
  width: 100%;
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
`;

const Content = styled.div`
	width: 100%;
	padding: 0 50px 20px 50px;
	padding-top: 0;
	box-sizing: border-box;
	margin-top: 40px;
	display: flex;
	justify-content: center;
`;