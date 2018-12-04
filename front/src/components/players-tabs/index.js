import React, {Component} from 'react';
import styled from 'styled-components';
import TabButton from '../../components-ui/buttons/tab-button';
import {ProfileBlock} from '../profile-block';
import PlayersListHead from '../players-list-head';
import InfiniteScroll from '../infinite-scroll';
import {NoContent} from '../no-content';

class PlayersTabs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAllPlayersTab: false
    }
  }

  onLoadMorePlayers = () => this.props.appendToPlayers(this.props.players.list.length);
  onLoadMoreActivePlayers = () => this.props.appendToActivePlayers(this.props.activePlayers.list.length);

  renderTab = () => {
    const {players, activePlayers} = this.props;
    const {isAllPlayersTab} = this.state;
    if (isAllPlayersTab) {
      return (
        <InfiniteScroll data={players.list} onLoadMore={this.onLoadMorePlayers} totalCount={players.totalCount}>
          {players.list.length ? players.list.map((item, index) =>
            <ProfileBlock key={item.player.id} id={item.player.id} index={index + 1} username={item.player.username} countGames={item.countGames}
                          rated={item.rated} rating={item.rating} iconPath={item.player.iconPath}/>) :
            <NoContent/>
          }
        </InfiniteScroll>
      )
    }
    return (
      <InfiniteScroll data={activePlayers.list} onLoadMore={this.onLoadMoreActivePlayers} totalCount={activePlayers.totalCount}>
        {activePlayers.list.length ? activePlayers.list.map((item, index) =>
          <ProfileBlock key={item.player.id} id={item.player.id} index={index + 1} username={item.player.username} countGames={item.countGames}
                        rated={item.rated} rating={item.rating} iconPath={item.player.iconPath}/>) :
          <NoContent/>
        }
      </InfiniteScroll>
    )
  }

  setActivePlayersTab = () => this.setState({isAllPlayersTab: false})
  setAllPlayersTab = () => this.setState({isAllPlayersTab: true})

  render() {
    const {isAllPlayersTab} = this.state;
    return (
      <Content>
        <TabButtonContainer>
          <TabButton name='active' onButtonClick={this.setActivePlayersTab} isActive={!isAllPlayersTab}/>
          <TabButton name='all' onButtonClick={this.setAllPlayersTab} isActive={isAllPlayersTab}/>
        </TabButtonContainer>
        <PlayersListHead />
        <Players>
          {this.renderTab()}
        </Players>
      </Content>

    );
  }
}

export default PlayersTabs;

const Content = styled.div`
  width: 900px;
`;
const TabButtonContainer = styled.div`
  display: flex;
  margin-bottom: 20px;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	width: max-content;
	font-size: 1.2em;
`;

const Players = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	max-height: 650px;
	width: 100%;
	overflow-y: auto;
	overflow-x: hidden;
`;
