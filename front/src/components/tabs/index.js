import React, {Component} from 'react';
import styled from 'styled-components';
import TabButton from '../../components-ui/buttons/tab-button';
import {ProfileBlock} from '../ProfileBlock';

const TabButtonContainer = styled.div`
  display: flex;
  margin-bottom: 20px;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	width: max-content;
	font-size: 1.2em;
`;

class Tabs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAllPlayersTab: false
    }
  }

  renderTab = () => {
    if (this.state.isAllPlayersTab) {
      return this.props.players && this.props.players.map((item, index) =>
        <ProfileBlock key={item.id} id={item.id} index={index + 1} username={item.username} countGames={item.countGames}
                      rated={item.rated} rating={item.rating}/>)
    }
    return this.props.activePlayers && this.props.activePlayers.map((item, index) =>
      <ProfileBlock key={item.id} id={item.id} index={index + 1} username={item.username} countGames={item.countGames}
                    rated={item.rated} rating={item.rating}/>)
  }

  setActivePlayersTab = () => this.setState({isAllPlayersTab: false})
  setAllPlayersTab = () => this.setState({isAllPlayersTab: true})

  render() {
    return (
      <div>
        <TabButtonContainer>
          <TabButton name='active' onButtonClick={this.setActivePlayersTab} isActive={!this.state.isAllPlayersTab}/>
          <TabButton name='all' onButtonClick={this.setAllPlayersTab} isActive={this.state.isAllPlayersTab}/>
        </TabButtonContainer>
        {this.renderTab()}

      </div>

    );
  }
}

export default Tabs;