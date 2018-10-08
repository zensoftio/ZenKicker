import React, {Component} from 'react';
import styled from 'styled-components';
import {GameBlock} from '../../components/game-block';
import TabButton from '../../components-ui/buttons/tab-button';
import InfiniteScroll  from '../infinite-scroll';

const Content = styled.div`
`;

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
	max-height: 580px;
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

class GamesTabs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAllGamesTab: false
    }
  }

  onLoadMore = () => this.props.appendToGamesAction(this.props.games.length);

  renderTab = () => {
    const {games, latestGames, totalCount} = this.props;
    if (this.state.isAllGamesTab) {
      return (
        <InfiniteScroll data={games} onLoadMore={this.onLoadMore} totalCount={totalCount}>
          {games && games.map(i => <GameBlock key={i.id} losersGoals={i.losersGoals} winner1Icon={i.winner1Icon}
                                                              winner2Icon={i.winner2Icon} loser1Icon={i.loser1Icon} loser2Icon={i.loser2Icon}/>)}
        </InfiniteScroll>
      );
    }
    return (
      latestGames && latestGames.map(i => <GameBlock key={i.id} losersGoals={i.losersGoals} winner1Icon={i.winner1Icon}
                                                     winner2Icon={i.winner2Icon} loser1Icon={i.loser1Icon} loser2Icon={i.loser2Icon}/>)
    );
  }

  setActivePlayersTab = () => this.setState({isAllGamesTab: false})
  setAllPlayersTab = () => this.setState({isAllGamesTab: true})

  render() {
    const {isAllGamesTab} = this.state;

    return (
      <Content>
        <TabButtonContainer>
          <TabButton name='latest' onButtonClick={this.setActivePlayersTab} isActive={!isAllGamesTab}/>
          <TabButton name='all' onButtonClick={this.setAllPlayersTab} isActive={isAllGamesTab}/>
        </TabButtonContainer>
        <Head>
          <Winners>Winners</Winners>
          <Losers>Losers</Losers>
        </Head>
        <LatestGames>
          {this.renderTab()}
        </LatestGames>

      </Content>

    );
  }

}

export default GamesTabs