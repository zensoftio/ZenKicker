import React, {Component} from 'react';
import styled from 'styled-components';
import TabButton from '../../components-ui/buttons/tab-button';
import {ProfileBlock} from '../profile-block';
import InfiniteScroll from '../infinite-scroll';

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
          {players.list.map((item, index) =>
            <ProfileBlock key={item.id} id={item.id} index={index + 1} username={item.username} countGames={item.countGames}
                          rated={item.rated} rating={item.rating} iconName={item.iconName}/>)}
        </InfiniteScroll>
      )
    }
    return (
      <InfiniteScroll data={activePlayers.list} onLoadMore={this.onLoadMoreActivePlayers} totalCount={activePlayers.totalCount}>
        {activePlayers.list.map((item, index) =>
          <ProfileBlock key={item.id} id={item.id} index={index + 1} username={item.username} countGames={item.countGames}
                        rated={item.rated} rating={item.rating} iconName={item.iconName}/>)}
      </InfiniteScroll>
    )
  }

  setActivePlayersTab = () => this.setState({isAllPlayersTab: false})
  setAllPlayersTab = () => this.setState({isAllPlayersTab: true})

  render() {
    const {isAllPlayersTab} = this.state;
    return (
      <div>
        <TabButtonContainer>
          <TabButton name='active' onButtonClick={this.setActivePlayersTab} isActive={!isAllPlayersTab}/>
          <TabButton name='all' onButtonClick={this.setAllPlayersTab} isActive={isAllPlayersTab}/>
        </TabButtonContainer>
        <Head>
          <IndexColumn>#</IndexColumn>
          <PlayerColumn>Player</PlayerColumn>
          <StatisticColumn>Games</StatisticColumn>
          <StatisticColumn>Rated</StatisticColumn>
          <StatisticColumn>Rating</StatisticColumn>
        </Head>
        <Players>
          {this.renderTab()}
        </Players>
      </div>

    );
  }
}

export default PlayersTabs;

const TabButtonContainer = styled.div`
  display: flex;
  margin-bottom: 20px;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	width: max-content;
	font-size: 1.2em;
`;

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

const Players = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	max-height: 650px;
	overflow: auto;
`;
