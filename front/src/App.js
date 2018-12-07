import React, {Component} from 'react';
import Config from 'react-global-configuration';
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
    const {currentUser, isLogin} = this.props;
    const isMobile = window.outerWidth <= MediaViews.MOBILE;

    if (!currentUser) {
      return null;
    }

    if (isLogin === false) {
      window.location.href = Config.get('login_callback');
      return null;
    }

    return (
      <Container>
        {
          isMobile ? <MobileMenu id={currentUser.id} iconPath={currentUser.iconPath} username={currentUser.username}/> :
            <HorizontalMenu id={currentUser.id} iconPath={currentUser.iconPath} username={currentUser.username}/>
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
    isLogin: state.authentication.isLogin
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
  min-height: 100vh;
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
	margin-top: 120px;
	display: flex;
	justify-content: center;
	@media (max-width: ${MediaViews.MOBILE}px) {
	  margin-top: 90px;
	  padding: 0 20px;
  }
`;