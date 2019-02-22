import React, {Component} from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import TabButton from '../../components-ui/buttons/tab-button';
import {ProfileBlock} from '../profile-block';
import PlayersListHead from '../players-list-head';
import InfiniteScroll from '../infinite-scroll';
import {NoContent} from '../no-content';
import {MediaViews} from "../../helpers/style-variables";
import DropdownInput from "../../components-ui/dropdown-input";
import {PlayerStatsModel} from "../../common/global-prop-types";

const renderColumnOptions = [
  {value: 'firstPart', label: 'LWS, LLS, Win'},
  {value: 'secondPart', label: 'Games, Rated, Rating'}
]

class PlayersTabs extends Component {
  constructor(props) {
    super(props);
    this.onSortChange = this.onSortChange.bind(this);
    this.state = {
      isAllPlayersTab: false,
      renderColumnsValue: renderColumnOptions[0]
    }
  }

  componentDidMount() {
    this.props.getActivePlayers()
  }

  onLoadMorePlayers = () => this.props.appendToPlayers();
  onLoadMoreActivePlayers = () => this.props.appendToActivePlayers();

  renderTab = (isMobile) => {
    const {players, activePlayers} = this.props;
    const {isAllPlayersTab, renderColumnsValue} = this.state;
    if (isAllPlayersTab) {
      return (
        <InfiniteScroll data={players.list} onLoadMore={this.onLoadMorePlayers} totalCount={players.totalCount}>
          {players.list.length ? players.list.map((item, index) =>
            <ProfileBlock key={item.player.id} id={item.player.id} index={index + 1} fullName={item.player.fullName} countGames={item.countGames}
                          rated={item.rated} rating={item.rating} iconPath={item.player.iconPath} isMobile={isMobile}
                          longestWinningStreak={item.longestWinningStreak} longestLossesStreak={item.longestLossesStreak}
                          winningPercentage={item.winningPercentage} renderColumns={renderColumnsValue.value}/>) :
            <NoContent/>
          }
        </InfiniteScroll>
      )
    }
    return (
      <InfiniteScroll data={activePlayers.list} onLoadMore={this.onLoadMoreActivePlayers} totalCount={activePlayers.totalCount}>
        {activePlayers.list.length ? activePlayers.list.map((item, index) =>
          <ProfileBlock key={item.player.id} id={item.player.id} index={index + 1} fullName={item.player.fullName} countGames={item.countGames}
                        rated={item.rated} rating={item.rating} iconPath={item.player.iconPath} isMobile={isMobile}
                        longestWinningStreak={item.longestWinningStreak} longestLossesStreak={item.longestLossesStreak}
                        winningPercentage={item.winningPercentage} renderColumns={renderColumnsValue.value}/>) :
          <NoContent/>
        }
      </InfiniteScroll>
    )
  }

  setActivePlayersTab = () => {
    this.setState({isAllPlayersTab: false});
    this.props.getActivePlayers()
  }
  setAllPlayersTab = () => {
    this.setState({isAllPlayersTab: true});
    this.props.getAllPlayers()
  }

  onSortChange = (value) => {
    const {initSort, sort} = this.props;
    const sortDirection = sort.sortDirection === 'DESC' ? 'ASC' : 'DESC';
    const isAllPlayers = this.state.isAllPlayersTab;

    initSort({sortBy: value, sortDirection: sortDirection}, isAllPlayers);
  }

  onOptionChange = (value) => this.setState({renderColumnsValue: value})

  render() {
    const {isAllPlayersTab, renderColumnsValue} = this.state;
    const {sort} = this.props;

    const isMobile = window.outerWidth <= MediaViews.MOBILE;

    return (
      <Content>
        <TabButtonContainer>
          <TabButton name='active' onButtonClick={this.setActivePlayersTab} isActive={!isAllPlayersTab}/>
          <TabButton name='all' onButtonClick={this.setAllPlayersTab} isActive={isAllPlayersTab}/>
        </TabButtonContainer>
        {
          isMobile &&
          <DropdownInput value={renderColumnsValue} options={renderColumnOptions}
                         onChange={this.onOptionChange}/>
        }
        <PlayersListHead onSortChange={this.onSortChange} sortBy={sort.sortBy} sortDirection={sort.sortDirection}
                         renderColumns={renderColumnsValue.value} isMobile={isMobile}/>
        <Players>
          {this.renderTab(isMobile)}
        </Players>
        <Helper>
          <div>
            * LWS - Longest winning streak
          </div>
          <div>
            * LLS - Longest losses streak
          </div>
        </Helper>
      </Content>

    );
  }
}

export default PlayersTabs;

PlayersTabs.propTypes = {
  players: PropTypes.shape({
    list: PropTypes.arrayOf(PlayerStatsModel),
    totalCount: PropTypes.number.isRequired
  }).isRequired,
  activePlayers: PropTypes.shape({
    list: PropTypes.arrayOf(PlayerStatsModel),
    totalCount: PropTypes.number.isRequired
  }).isRequired,
  appendToPlayers: PropTypes.func.isRequired,
  appendToActivePlayers: PropTypes.func.isRequired,
  initSort: PropTypes.func.isRequired,
  sort: PropTypes.shape({
    sortBy: PropTypes.string.isRequired,
    sortDirection: PropTypes.string.isRequired
  }),
  getActivePlayers: PropTypes.func.isRequired,
  getAllPlayers: PropTypes.func.isRequired,
}

const Content = styled.div`
  width: 900px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
  }
`;

const TabButtonContainer = styled.div`
  display: flex;
  margin-bottom: 20px;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	width: max-content;
	font-size: 1.2em;
	@media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1em;
    width: 100%;
  }
`;

const Players = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	max-height: 650px;
	width: 100%;
	overflow-y: auto;
	overflow-x: hidden;
	&>div {
	  width: 100%;
	}
	@media (max-width: ${MediaViews.MOBILE}px) {
    max-height: calc(100vh - 280px);
  }
`;


const Helper = styled.div`
  margin-top: 20px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    margin-top: 10px;
    font-size: 0.8em;
  }
`;
