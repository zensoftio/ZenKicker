import React from 'react';
import styled from 'styled-components';
import PropTypes from "prop-types";
import Chart from "../chart";

const setWeekValue = (week) => {
  if (week === 0) return 'Current week';
  if (week === 1) return `${week} week ago`;
  return `${week} weeks ago`;
}

const ChartStatistics = ({ratingStatistic, gamesCountStatistic, isMobile}) => {

  const mappedRatingStatistic = ratingStatistic.map((item, index) =>
    ({rating: item, week: setWeekValue(9 - index)}));
  const mappedGamesCountStatistic = gamesCountStatistic.map((item, index) =>
    ({games: item, week: setWeekValue(9 - index)}));

  return (
    <Content>
      <Chart data={mappedRatingStatistic} lineDataKey='rating' xDataKey='week' title='Rating per week'
             isMobile={isMobile}/>
      <Chart data={mappedGamesCountStatistic} lineDataKey='games' xDataKey='week' title='Count of games per week'
             isMobile={isMobile}/>
    </Content>
  )
}

export default ChartStatistics;

ChartStatistics.propTypes = {
  ratingStatistic: PropTypes.arrayOf(PropTypes.number).isRequired,
  gamesCountStatistic: PropTypes.arrayOf(PropTypes.number).isRequired,
  isMobile: PropTypes.bool.isRequired,
}

const Content = styled.div`
  display: flex;
  flex-direction: column;
`;