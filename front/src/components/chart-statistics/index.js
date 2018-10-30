import React from 'react';
import styled from 'styled-components';
import PlayerStatistic from '../player-statistic';

const setWeekValue = (week) => {
  if (week === 0) return 'Current week';
  if (week === 1) return `${week} week ago`;
  return `${week} weeks ago`;
}

const ChartStatistics = ({deltaStatistic, gamesCountStatistic}) => {

  const mappedDeltaStatistic = deltaStatistic ? Object.values(deltaStatistic).map((item, index) =>
    ({delta: item, week: setWeekValue(index)})) : []
  const mappedGamesCountStatistic = gamesCountStatistic ? Object.values(gamesCountStatistic).map((item, index) =>
    ({games: item, week: setWeekValue(index)})) : []

  return (
    <Content>
      <PlayerStatistic data={mappedDeltaStatistic} lineDataKey='delta' title='Delta per week'/>
      <PlayerStatistic data={mappedGamesCountStatistic} lineDataKey='games' title='Count of games per week'/>
    </Content>
  )
}

export default ChartStatistics;

const Content = styled.div`
  display: flex;
  flex-direction: column;
`;