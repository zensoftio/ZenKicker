import React from 'react';
import styled from 'styled-components';
import {GameBlock} from '../../components/game-block';
import GamesListHead from '../games-list-head';
import {NoContent} from '../no-content';
import {MediaViews} from "../../helpers/style-variables";

export const LatestGames = ({latestGames}) => {

  const isMobile = window.outerWidth <= MediaViews.MOBILE;
  return (
    <div>
      <GamesListHead/>
      <LatestGamesContainer>
        {
          latestGames.length ? latestGames.map(i => <GameBlock key={i.id} losersGoals={i.losersGoals} isMobile={isMobile}
                                                               winner1Icon={i.winner1.iconPath} winner1Id={i.winner1.id}
                                                               winner1Name={i.winner1.username}
                                                               winner2Id={i.winner2.id} winner2Icon={i.winner2.iconPath}
                                                               winner2Name={i.winner2.username}
                                                               loser1Id={i.loser1.id} loser1Icon={i.loser1.iconPath}
                                                               loser1Name={i.loser1.username}
                                                               loser2Id={i.loser2.id} loser2Icon={i.loser2.iconPath}
                                                               loser2Name={i.loser2.username} date={i.date}
                                                               reportedBy={i.reportedBy.username} reportedById={i.reportedBy.id}/>) :
            <NoContent/>
        }
      </LatestGamesContainer>
    </div>
  )
}

export default LatestGames

const LatestGamesContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	overflow: auto;
`;
