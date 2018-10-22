import React from 'react';
import {ProfileBlock} from '../profile-block';
import PlayersListHead from '../players-list-head';

const TopPlayers = ({topPlayers}) => (
  <div>
    <PlayersListHead />
    {
      topPlayers && topPlayers.map((item, index) =>
        <ProfileBlock key={item.id} id={item.id} index={index + 1} username={item.username} countGames={item.countGames}
                      rated={item.rated} rating={item.rating} iconName={item.iconName}/>)
    }

  </div>
)

export default TopPlayers;