import React from 'react';
import styled from 'styled-components';
import {ProfileBlock} from '../profile-block';

const Head = styled.div`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	align-items: center;
	padding: 15px 0 15px 20px;
	width: max-content;
`;

const IndexColumn = styled.div`
  max-width: 60px;
  min-width: 60px;
`;

const PlayerColumn = styled.div`
  max-width: 290px;
  min-width: 290px;
`;

const StatisticColumn = styled.div`
  max-width: 120px;
  min-width: 120px;
`;

const TopPlayers = ({topPlayers}) => (
  <div>
    <Head>
      <IndexColumn>#</IndexColumn>
      <PlayerColumn>Player</PlayerColumn>
      <StatisticColumn>Games</StatisticColumn>
      <StatisticColumn>Rated</StatisticColumn>
      <StatisticColumn>Rating</StatisticColumn>
    </Head>
    {
      topPlayers && topPlayers.map((item, index) =>
        <ProfileBlock key={item.id} id={item.id} index={index + 1} username={item.username} countGames={item.countGames}
                      rated={item.rated} rating={item.rating} iconName={item.iconName}/>)
    }

  </div>
)

export default TopPlayers;