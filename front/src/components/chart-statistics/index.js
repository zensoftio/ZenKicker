import React from 'react';
import styled from 'styled-components';
import Chart from '../chart';

const setWeekValue = (week) => {
  if (week === 0) return 'Current week';
  if (week === 1) return `${week} week ago`;
  return `${week} weeks ago`;
}

const ChartStatistics = ({deltaStatistic, gamesCountStatistic}) => {

  const mappedDeltaStatistic = deltaStatistic.map((item, index) =>
    ({delta: item, week: setWeekValue(index)}));
  const mappedGamesCountStatistic = gamesCountStatistic.map((item, index) =>
    ({games: item, week: setWeekValue(index)}));

  return (
    <Content>
      <Chart data={mappedDeltaStatistic} lineDataKey='delta' xDataKey='week' title='Delta per week'/>
      <Chart data={mappedGamesCountStatistic} lineDataKey='games' xDataKey='week' title='Count of games per week'/>
    </Content>
  )
}

export default ChartStatistics;

const Content = styled.div`
  display: flex;
  flex-direction: column;
`;