import React from 'react';
import {ProfileBlock} from '../profile-block';
import PlayersListHead from '../players-list-head';
import {NoContent} from '../no-content';

const TopPlayers = ({topPlayers}) => (
  <div>
    <PlayersListHead />
    {
      topPlayers.length ? topPlayers.map((item, index) =>
        <ProfileBlock key={item.id} id={item.id} index={index + 1} username={item.username} countGames={item.countGames}
                      rated={item.rated} rating={item.rating} iconName={item.iconName}/>) :
        <NoContent/>
    }
  </div>
)

export default TopPlayers;