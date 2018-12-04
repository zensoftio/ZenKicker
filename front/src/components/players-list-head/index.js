import React from 'react';
import styled from 'styled-components';
import {MediaViews} from "../../helpers/style-variables";

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

const Content = styled.div`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	align-items: center;
	padding: 15px 20px;
	width: 100%;
	box-sizing: border-box;
	margin-bottom: 5px;
	font-size: 1.2em;
	
	@media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.8em;
    padding: 10px;
  }
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
  max-width: 330px;
  min-width: 330px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 20%;
    min-width: 20%;
  }
`;

const StatisticColumn = styled.div`
  max-width: 160px;
  min-width: 160px;
  padding-left: 20px;
  box-sizing: border-box;
  text-align: right;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 23.3%;
    min-width: 23.3%;
  }
`;

