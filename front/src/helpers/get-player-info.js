export const getPlayerInfo = (players, playersIds) => {
  if (!players.list.length || !playersIds.winner1Id) return null;
  return ({
    winner1Icon: players.list.find(i => i.player.id === playersIds.winner1Id).player.iconName || null,
    winner2Icon: players.list.find(i => i.player.id === playersIds.winner2Id).player.iconName || null,
    loser1Icon: players.list.find(i => i.player.id === playersIds.loser1Id).player.iconName || null,
    loser2Icon: players.list.find(i => i.player.id === playersIds.loser2Id).player.iconName || null,
    winner1Name: players.list.find(i => i.player.id === playersIds.winner1Id).player.username || null,
    winner2Name: players.list.find(i => i.player.id === playersIds.winner2Id).player.username || null,
    loser1Name: players.list.find(i => i.player.id === playersIds.loser1Id).player.username || null,
    loser2Name: players.list.find(i => i.player.id === playersIds.loser2Id).player.username || null
  })
}