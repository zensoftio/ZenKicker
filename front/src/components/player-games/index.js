import React from 'react';
import styled from 'styled-components';
import {GameBlock} from '../../components/game-block';
import InfiniteScroll  from '../infinite-scroll';

const PlayerGames = ({games, totalCount, appendToGames, playerId}) => {

  const onLoadMore = () => appendToGames(games.length, playerId);

  return (
    <Content>
      <Head>
        <Winners>Winners</Winners>
        <Losers>Losers</Losers>
      </Head>
      <LatestGames>
        <InfiniteScroll data={games} onLoadMore={onLoadMore} totalCount={totalCount}>
          {games.map(i => <GameBlock key={i.id} losersGoals={i.losersGoals} winner1Icon={i.winner1Icon} winner1Id={i.winner1Id}
                                     winner2Id={i.winner2Id} loser1Id={i.loser1Id} loser2Id={i.loser2Id} winner2Icon={i.winner2Icon}
                                     loser1Icon={i.loser1Icon} loser2Icon={i.loser2Icon}/>)}
        </InfiniteScroll>
      </LatestGames>
    </Content>
  );
}

export default PlayerGames;

const Content = styled.div`
`;

const Head = styled.div`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	align-items: center;
	padding: 15px 0;
	width: 100%;
	min-width: 300px;
	box-sizing: border-box;
	font-size: 1.2em;
`;

const LatestGames = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	max-height: 700px;
	overflow: auto;
`;

const Winners = styled.div`
	max-width: 50%;
  min-width: 50%;
  text-align: center;
  padding-right: 40px;
	box-sizing: border-box;
	color: green;
	border-right: solid 1px #e0e0e0;
`;

const Losers = styled.div`
	max-width: 50%;
  min-width: 50%;
  padding-left: 40px;
  text-align: center;
	box-sizing: border-box;
	color: red;
`;
