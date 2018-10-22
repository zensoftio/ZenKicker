import React from 'react';
import styled from 'styled-components';
import {GameBlock} from '../../components/game-block';
import GamesListHead  from '../games-list-head';

export const LatestGames = ({latestGames}) => (
  <div>
    <GamesListHead/>
    <LatestGamesContainer>
      {
        latestGames && latestGames.map(i => <GameBlock key={i.id} losersGoals={i.losersGoals} winner1Icon={i.winner1Icon}
                                                       winner1Id={i.winner1Id} winner2Id={i.winner2Id} loser1Id={i.loser1Id}
                                                       loser2Id={i.loser2Id} winner2Icon={i.winner2Icon} loser1Icon={i.loser1Icon}
                                                       loser2Icon={i.loser2Icon} winner1Name={i.winner1Name} winner2Name={i.winner2Name}
                                                       loser1Name={i.loser1Name} loser2Name={i.loser2Name}/>)
      }
    </LatestGamesContainer>
  </div>
)

export default LatestGames

const LatestGamesContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	overflow: auto;
`;
