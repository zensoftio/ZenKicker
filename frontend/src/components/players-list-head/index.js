import React from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import {MediaViews} from "../../helpers/style-variables";

import ExpandLessIco from '@material-ui/icons/ExpandLess';
import ExpandMoreIco from '@material-ui/icons/ExpandMore';

const isColumnSorting = (sortBy, sortDirection, field) => {
  if (sortBy === field) {
      return sortDirection === 'DESC' ? <CustomExpandMoreIco/> : <CustomExpandLessIco/>
  }
  return null
}

const renderStatisticColumns = ({onSortChange, sortDirection, sortBy, renderColumns, isMobile}) => {
  if (!isMobile) return (
    <React.Fragment>
      <StatisticColumn onClick={() => onSortChange('longestWinningStreak')}>{isColumnSorting(sortBy, sortDirection, 'longestWinningStreak')}*LWS</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('longestLossesStreak')}>{isColumnSorting(sortBy, sortDirection, 'longestLossesStreak')}*LLS</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('winningPercentage')}>{isColumnSorting(sortBy, sortDirection, 'winningPercentage')}Win (%)</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('countGames')}>{isColumnSorting(sortBy, sortDirection, 'countGames')}Games</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('rated')}>{isColumnSorting(sortBy, sortDirection, 'rated')}Rated</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('rating')}>{isColumnSorting(sortBy, sortDirection, 'rating')}Rating</StatisticColumn>
    </React.Fragment>
  )
  if (renderColumns === 'firstPart') return (
    <React.Fragment>
      <StatisticColumn onClick={() => onSortChange('longestWinningStreak')}>{isColumnSorting(sortBy, sortDirection, 'longestWinningStreak')}*LWS</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('longestLossesStreak')}>{isColumnSorting(sortBy, sortDirection, 'longestLossesStreak')}*LLS</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('winningPercentage')}>{isColumnSorting(sortBy, sortDirection, 'winningPercentage')}Win (%)</StatisticColumn>
    </React.Fragment>
  )
  if (renderColumns === 'secondPart') return (
    <React.Fragment>
      <StatisticColumn onClick={() => onSortChange('countGames')}>{isColumnSorting(sortBy, sortDirection, 'countGames')}Games</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('rated')}>{isColumnSorting(sortBy, sortDirection, 'rated')}Rated</StatisticColumn>
      <StatisticColumn onClick={() => onSortChange('rating')}>{isColumnSorting(sortBy, sortDirection, 'rating')}Rating</StatisticColumn>
    </React.Fragment>
  )
}

const PlayersListHead = ({onSortChange, sortDirection, sortBy, renderColumns, isMobile}) => (
  <Content>
    <IndexColumn>#</IndexColumn>
    <PlayerColumn>Player</PlayerColumn>
    {
      renderStatisticColumns({onSortChange, sortDirection, sortBy, renderColumns, isMobile})
    }
  </Content>
)

export default PlayersListHead;

PlayersListHead.propTypes = {
  onSortChange: PropTypes.func.isRequired,
  sortDirection: PropTypes.string.isRequired,
  sortBy: PropTypes.string.isRequired,
  renderColumns: PropTypes.string.isRequired,
  isMobile: PropTypes.bool.isRequired,
}

const Content = styled.div`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	align-items: center;
	padding: 15px 20px;
	width: 100%;
	box-sizing: border-box;
	margin-bottom: 5px;
	font-size: 1em;
	
	@media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.8em;
    padding: 10px;
  }<
`;

const IndexColumn = styled.div`
  max-width: 50px;
  min-width: 50px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 10%;
    min-width: 10%;
  }
`;

const PlayerColumn = styled.div`
  max-width: 220px;
  min-width: 220px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 25%;
    min-width: 25%;
  }
`;

const StatisticColumn = styled.div`
  max-width: 98px;
  min-width: 98px;
  padding-left: 20px;
  box-sizing: border-box;
  text-align: right;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 22%;
    min-width: 22%;
  }
`;

const CustomExpandLessIco = styled(ExpandLessIco)`
  font-size: 1em !important;
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1em !important;
  }
`;

const CustomExpandMoreIco = styled(ExpandMoreIco)`
  font-size: 1em !important;
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1em !important;
  }
`;
