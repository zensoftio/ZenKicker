import React, {Component} from 'react';
import styled from 'styled-components';
import moment from 'moment';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getTopPlayers, getLatestGames, getAllPlayers, getGamesCountPerWeek} from '../../actions';
import {withRouter} from 'react-router-dom';
import LatestGames from '../../components/latest-games';
import TopPlayers from '../../components/top-players';
import Chart from '../../components/chart';

class DashboardScene extends Component {

  componentDidMount() {
    const {getTopPlayers, getAllPlayers, getLatestGames, getGamesCountPerWeek} = this.props.actions;
    getTopPlayers();
    getAllPlayers();
    getLatestGames();
    getGamesCountPerWeek();
  }

  render() {
    const {players, topPlayers, latestGames, gamesPerLastWeek} = this.props;

    const mappedLatestGames = players.list && latestGames.list.map(game => (
      {
        ...game,
        winner1Icon: players.list.length ? players.list.find(player => player.id === game.winner1Id).iconName : null,
        winner2Icon: players.list.length ? players.list.find(player => player.id === game.winner2Id).iconName : null,
        loser1Icon: players.list.length ? players.list.find(player => player.id === game.loser1Id).iconName : null,
        loser2Icon: players.list.length ? players.list.find(player => player.id === game.loser2Id).iconName : null,
        winner1Name: players.list.length ? players.list.find(player => player.id === game.winner1Id).username : null,
        winner2Name: players.list.length ? players.list.find(player => player.id === game.winner2Id).username : null,
        loser1Name: players.list.length ? players.list.find(player => player.id === game.loser1Id).username : null,
        loser2Name: players.list.length ? players.list.find(player => player.id === game.loser2Id).username : null
      }
    ))

    const daysOfWeek = moment.weekdays();
    const dayIndex = daysOfWeek.indexOf(moment().format('dddd'));
    const mappedDaysOfWeek = [...daysOfWeek.slice(dayIndex + 1), ...daysOfWeek.slice(0, dayIndex + 1)];

    const mappedGamesCountStatistic = gamesPerLastWeek.map((item, index) =>
      ({count: item, day: mappedDaysOfWeek[index]}));

    return (
      <Content>
        <div>
          <Chart data={mappedGamesCountStatistic} lineDataKey='count' title='Games per week' xDataKey='day'/>
        </div>
        <div>
          <Title>Top 5 players</Title>
          <TopPlayers topPlayers={topPlayers.list}/>
        </div>
        <div>
          <Title>Latest games</Title>
          <LatestGames latestGames={mappedLatestGames}/>
        </div>
      </Content>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    topPlayers: state.player.topPlayers,
    players: state.player.players,
    latestGames: state.game.latestGames,
    gamesPerLastWeek: state.game.gamesPerLastWeek
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getTopPlayers,
    getLatestGames,
    getAllPlayers,
    getGamesCountPerWeek
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
`;

const Title = styled.div`
  font-size: 1.3em;
  margin: 20px 0;
  width: 100%;
  text-align: center;
`;
