import React, {Component} from 'react';
import styled from 'styled-components';
import {Table} from '../../components/table';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {getActivePlayers, getAllPlayers} from '../../actions';
import {withRouter} from 'react-router-dom';
import {capitalizeString} from '../../helpers/string-helpers';

const Content = styled.div`
	padding: 40px 20px 20px 20px;
	display: flex;
	flex-direction: column;
`;

const TabButtonContainer = styled.div`
  display: flex;
  margin-bottom: 20px;
`;

const TabButton = styled.div`
  padding: 0 10px;
  cursor: pointer;
  color: ${props => props.active ? 'red' : 'black'};
`;

class RatingScene extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAllPlayersTab: false
    }
  }

  componentDidMount() {
    this.props.actions.getActivePlayers();
    this.props.actions.getAllPlayers();
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
        <Content>
          <TabButtonContainer>
            <TabButton active={!this.state.isAllPlayersTab} onClick={this.setActivePlayersTab}>{capitalizeString('active')}</TabButton>
            <TabButton active={this.state.isAllPlayersTab} onClick={this.setAllPlayersTab}>{capitalizeString('all')}</TabButton>
          </TabButtonContainer>
          {this.renderTab()}
        </Content>
      </div>

    );
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    activePlayers: state.user.activePlayers,
    players: state.user.players
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getActivePlayers,
    getAllPlayers
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
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

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(RatingScene));