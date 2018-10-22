import React from 'react';
import styled from 'styled-components';

const Content = styled.div`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	align-items: center;
	padding: 15px 20px;
	width: 100%;
	box-sizing: border-box;
	margin-bottom: 5px;
	font-size: 1.2em;
`;

const IndexColumn = styled.div`
  max-width: 50px;
  min-width: 50px;
`;

const PlayerColumn = styled.div`
  max-width: 330px;
  min-width: 330px;
`;

const StatisticColumn = styled.div`
  max-width: 160px;
  min-width: 160px;
  padding-left: 20px;
  box-sizing: border-box;
  text-align: right;
`;

const PlayersListHead = () => (
  <Content>
    <IndexColumn>#</IndexColumn>
    <PlayerColumn>Player</PlayerColumn>
    <StatisticColumn>Games</StatisticColumn>
    <StatisticColumn>Rated</StatisticColumn>
    <StatisticColumn>Rating</StatisticColumn>
  </Content>
)

export default PlayersListHead;