import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import Popup from '../popup';
import DropdownInput from '../../components-ui/dropdown-input';
import {
  getActivePlayers,
  getAllGames,
  getAllPlayers,
  getLatestGames,
  registerGame,
  getTopPlayers,
  getGamesCountPerWeek
} from '../../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Colors} from '../../helpers/style-variables';
import {getGoalValues} from '../../helpers/goal-values';

class GameRegistration extends Component {
  constructor(props) {
    super(props);
    this.popupChild = React.createRef();
    this.state = {
      winner1Id: null,
      winner2Id: null,
      loser1Id: null,
      loser2Id: null,
      losersGoals: null,
      registrationError: null,
    }
  }

  clearValues = () => this.setState({
    winner1Id: null,
    winner2Id: null,
    loser1Id: null,
    loser2Id: null,
    losersGoals: null,
    registrationError: null,
  })

  onWinner1Change = ({value}) => this.setState({winner1Id: value, registrationError: null})
  onWinner2Change = ({value}) => this.setState({winner2Id: value, registrationError: null})
  onLoser1Change = ({value}) => this.setState({loser1Id: value, registrationError: null})
  onLoser2Change = ({value}) => this.setState({loser2Id: value, registrationError: null})
  onLosersGoalsChange = ({value}) => this.setState({losersGoals: value, registrationError: null})

  onRegisterGame = async () => {
    const {winner1Id, winner2Id, loser1Id, loser2Id, losersGoals} = this.state;
    if (winner1Id === null || winner2Id === null || loser1Id === null || loser2Id === null || losersGoals === null) {
      return this.setState({registrationError: 'All fields should be filled'});
    }
    const {getActivePlayers, getAllPlayers, getLatestGames, getAllGames, getTopPlayers, getGamesCountPerWeek} = this.props.actions;
    const data = {
      winner1Id,
      winner2Id,
      loser1Id,
      loser2Id,
      losersGoals
    };
    try {
      await registerGame(data);
      this.clearValues();
      this.popupChild.current.onPopupClose();
      getActivePlayers();
      getAllPlayers();
      getLatestGames();
      getAllGames();
      getTopPlayers();
      getGamesCountPerWeek();
    } catch (err) {
      console.log({err})
      const error = err.response.data.errors[0].message;
      this.setState({registrationError: error});
    }
  }

  getFilteredPlayerList = () => {
    const {players} = this.props;
    const {winner1Id, winner2Id, loser1Id, loser2Id} = this.state;
    const chosenPlayers = [winner1Id, winner2Id, loser1Id, loser2Id];
    const mapPlayers = players.list.map(i => ({value: i.id, label: i.username}));
    return mapPlayers.filter(i => !chosenPlayers.includes(i.value))
  }

  render() {
    const {registrationError} = this.state;

    return (
      <Content>
        <Popup buttonTitle='Register Game' ref={this.popupChild} clearValues={this.clearValues}>
          <InputsContainer>
            <PopupTitle>Register game</PopupTitle>
            <Container>
              <Block>
                <DropdownInput data={this.getFilteredPlayerList()} onChange={this.onWinner1Change} placeholder='Winner 1'/>
                <DropdownInput data={this.getFilteredPlayerList()} onChange={this.onWinner2Change} placeholder='Winner 2'/>
              </Block>
              <ScoreBlock>
                <div>10 :</div>
                <DropdownInput data={getGoalValues()} onChange={this.onLosersGoalsChange} placeholder=''/>
              </ScoreBlock>
              <Block>
                <DropdownInput data={this.getFilteredPlayerList()} onChange={this.onLoser1Change} placeholder='Loser 1'/>
                <DropdownInput data={this.getFilteredPlayerList()} onChange={this.onLoser2Change} placeholder='Loser 2'/>
              </Block>
            </Container>
            <RegistrationError>{registrationError}</RegistrationError>
            <Button onClick={this.onRegisterGame}>Confirm</Button>
          </InputsContainer>
        </Popup>
      </Content>
    )
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    players: state.player.players
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getAllPlayers,
    getActivePlayers,
    getLatestGames,
    getAllGames,
    getTopPlayers,
    getGamesCountPerWeek
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default connect(mapStateToProps, mapDispatchToProps)(GameRegistration);


const Content = styled.div``;

const PopupTitle = styled.div`
  font-size: 1.5em;
  margin-bottom: 40px;
`;

const Container = styled.div`
  display: flex;
  width: 600px;
  align-items: center;
`;

const Block = styled.div`
  width: 40%;
`;

const ScoreBlock = styled.div`
  display: flex;
  align-items: center;
`;

const InputsContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: max-content;
  height: max-content;
  background-color: #fff;
  padding: 40px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  border-radius: 2px;
`;

const RegistrationError = styled.span`
  color: ${Colors.ERROR_COLOR};
  display: flex;
  align-items: center;
  margin: 20px 0;
`;
