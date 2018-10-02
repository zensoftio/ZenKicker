import React, {Component} from 'react';
import styled from 'styled-components';
import {Table} from '../../components/table';
import TabButton from '../../components-ui/buttons/tab-button';

const TabButtonContainer = styled.div`
  display: flex;
  margin-bottom: 20px;
`;

class Tabs extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAllPlayersTab: false
    }
  }

  renderTab = () => {
    if (this.state.isAllPlayersTab) return <Table columns={getColumns()} data={this.props.players.list}/>
    return <Table columns={getColumns()} data={this.props.activePlayers.list}/>
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

const getColumns = () => [
  {
    Header: '',
    Cell: ({index}) => <span>{index + 1}</span>,
    width: 50,
    style: {
      textAlign: 'center'
    }
  },
  {
    Header: "Player",
    accessor: 'username',
    width: 250,
    style: {
      textAlign: 'center'
    }
  },
  {
    Header: "Games",
    accessor: 'games',
    width: 100,
    style: {
      textAlign: 'center'
    }
  },
  {
    Header: "Rated",
    accessor: 'rated',
    width: 150,
    style: {
      textAlign: 'center'
    }
  },
  {
    Header: "Rating",
    accessor: 'rating',
    width: 150,
    style: {
      textAlign: 'center'
    }
  }
];

export default Tabs;