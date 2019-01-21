import React, {Component} from 'react';
import PropTypes from "prop-types";
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
import {GameModel, PlayerDashboardModel, PlayerStatsModel} from "../../common/global-prop-types";

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
                 title='Games per day' isMobile={isMobile}/>
        </div>
        <div>
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

DashboardScene.propTypes = {
  playersDashboard: PlayerDashboardModel,
  fullListOfPlayers: PropTypes.arrayOf(PlayerStatsModel),
  latestGames: PropTypes.shape({
    list: PropTypes.arrayOf(GameModel),
    totalCount: PropTypes.number.isRequired
  }).isRequired,
  gamesPerLastWeek: PropTypes.arrayOf(PropTypes.number.isRequired)
}

const Content = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 900px;
  
  &>div {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
  }
`;