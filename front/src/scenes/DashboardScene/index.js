import React, {Component} from 'react';
import styled from 'styled-components';
import moment from 'moment';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getLatestGames, getAllPlayers, getGamesCountPerWeek, getPlayersDashboard} from '../../actions';
import {withRouter} from 'react-router-dom';
import LatestGames from '../../components/latest-games';
import Chart from '../../components/chart';
import PlayersOfWeek from "../../components/players-of-week";
import {MediaViews} from "../../helpers/style-variables";

class DashboardScene extends Component {

  componentDidMount() {
    const {getAllPlayers, getLatestGames, getGamesCountPerWeek, getPlayersDashboard} = this.props.actions;
    getAllPlayers();
    getLatestGames();
    getGamesCountPerWeek();
    getPlayersDashboard();
  }

  render() {
    const {playersDashboard, latestGames, gamesPerLastWeek} = this.props;

    const daysOfWeek = moment.weekdays();
    const dayIndex = daysOfWeek.indexOf(moment().format('dddd'));
    const mappedDaysOfWeek = [...daysOfWeek.slice(dayIndex + 1), ...daysOfWeek.slice(0, dayIndex + 1)];

    const mappedGamesCountStatistic = gamesPerLastWeek.map((item, index) =>
      ({count: item, day: mappedDaysOfWeek[index]}));

    const isMobile = window.outerWidth <= MediaViews.MOBILE;
    return (
      <Content>
        <div>
          <PlayersOfWeek players={playersDashboard}/>
        </div>
        <div>
          <Chart data={mappedGamesCountStatistic} lineDataKey='count' xDataKey='day'
                 title='Games per day' width={isMobile ? window.outerWidth - 50 : undefined}/>
        </div>
        <div>
          <Title>Latest games</Title>
          <LatestGames latestGames={latestGames.list}/>
        </div>
      </Content>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    playersDashboard: state.player.playersDashboard,
    fullListOfPlayers: state.player.fullListOfPlayers,
    latestGames: state.game.latestGames,
    gamesPerLastWeek: state.game.gamesPerLastWeek,
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getLatestGames,
    getAllPlayers,
    getGamesCountPerWeek,
    getPlayersDashboard
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(DashboardScene));

const Content = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 900px;
  
  &>div {
    margin-top: 60px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  &>div:first-child {
    margin-top: 0;
  }
  
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
    &>div {
      margin-top: 30px;
    }
  }
`;

const Title = styled.div`
  font-size: 1.3em;
  margin: 20px 0;
  width: 100%;
  text-align: center;
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1.2em;
  }
`;
