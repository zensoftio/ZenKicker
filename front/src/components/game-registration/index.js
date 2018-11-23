import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import Popup from '../popup';
import DropdownInput from '../../components-ui/dropdown-input';
import {
  getActivePlayers, getAllGames, getAllPlayers, getLatestGames, registerGame, getGamesCountPerWeek, getLastGame,
  getPlayersDashboard, getPlayer, getPlayerDeltaStatistic, getPlayerGames, getPlayerGamesCountStatistic, getRelations
} from '../../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Colors} from '../../helpers/style-variables';
import {getGoalValues} from '../../helpers/goal-values';
import {RegisteredGameBlock} from "../registered-game-block";

import reverseIcon from '../../shared/images/icons/reverse.png';
import {getPlayerInfo} from "../../helpers/get-player-info";
import {withRouter} from "react-router-dom";

class GameRegistration extends Component {
  constructor(props) {
    super(props);
    this.popupChild = React.createRef();
    this.state = {
      losersGoals: null,
      registrationError: null,
      isGameRegistration: false,
      winner1: null,
      winner2: null,
      loser1: null,
      loser2: null,
      playerId: null
    }
  }

  componentDidMount() {
    this.setState({playerId: this.props.location.pathname.split('/')[2]})
  }

  setDefaultValue = (playerId) => {
    const result = this.props.players.list.find(i => i.player.id === playerId)
    return ({value: result.player.id, label: result.player.username})
  }

  setDefaultValues = async () => {
    await this.props.actions.getLastGame();
    const {lastGame} = this.props;
    if (!lastGame) return null;

    this.setState({
      winner1: this.setDefaultValue(lastGame.winner1Id),
      winner2: this.setDefaultValue(lastGame.winner2Id),
      loser1: this.setDefaultValue(lastGame.loser1Id),
      loser2: this.setDefaultValue(lastGame.loser2Id),
    })
  }

  clearValues = () => this.setState({
    losersGoals: null,
    registrationError: null,
    isGameRegistration: false,
    winner1: null,
    winner2: null,
    loser1: null,
    loser2: null,
  })

  onWinner1Change = (option) => this.setState({registrationError: null, winner1: option})
  onWinner2Change = (option) => this.setState({registrationError: null, winner2: option})
  onLoser1Change = (option) => this.setState({registrationError: null, loser1: option})
  onLoser2Change = (option) => this.setState({registrationError: null, loser2: option})
  onLosersGoalsChange = ({value}) => this.setState({losersGoals: value, registrationError: null})

  onCancelRegistration = () => {
    if (this.state.isGameRegistration) {
      this.setState({isGameRegistration: false})
    } else {
      this.clearValues();
      this.popupChild.current.onPopupClose();
    }
  }

  onRegisterGame = () => {
    const {winner1, winner2, loser1, loser2, losersGoals} = this.state;

    if (winner1 === null || winner2 === null || loser1 === null || loser2 === null || losersGoals === null) {
      return this.setState({registrationError: 'All fields should be filled'});
    }

    this.state.isGameRegistration ? this.onRegistrationConfirm() : this.setState({isGameRegistration: true})
  }

  updateData = ({getActivePlayers, getAllPlayers, getLatestGames, getAllGames, getPlayersDashboard, getGamesCountPerWeek,
                  getPlayer, getPlayerDeltaStatistic, getPlayerGames, getPlayerGamesCountStatistic, getRelations}) => {
    const {playerId} = this.state;
    if (playerId) {
      getPlayer(playerId);
      getPlayerDeltaStatistic(playerId);
      getPlayerGames(playerId);
      getPlayerGamesCountStatistic(playerId);
      getRelations(playerId);
    }
    getActivePlayers();
    getAllPlayers();
    getLatestGames();
    getAllGames();
    getPlayersDashboard();
    getGamesCountPerWeek();
  }

  onRegistrationConfirm = async () => {
    const {winner1, winner2, loser1, loser2, losersGoals} = this.state;
    const data = {
      winner1Id: winner1.value,
      winner2Id: winner2.value,
      loser1Id: loser1.value,
      loser2Id: loser2.value,
      losersGoals
    };
    try {
      await registerGame(data);
      this.clearValues();
      this.popupChild.current.onPopupClose();
      this.updateData(this.props.actions)
    } catch (err) {
      console.log({err})
      const error = err.response.data.errors[0].message;
      this.setState({registrationError: error});
    }
  }

  getFilteredPlayerList = () => {
    const {players} = this.props;
    const {winner1, winner2, loser1, loser2} = this.state;
    const chosenPlayers = [winner1 && winner1.value, winner2 && winner2.value, loser1 && loser1.value, loser2 && loser2.value];
    const mapPlayers = players.list.map(i => ({value: i.player.id, label: i.player.username}));
    return mapPlayers.filter(i => !chosenPlayers.includes(i.value))
  }

  teamReverse = () => {
    const {winner1, winner2, loser1, loser2} = this.state;
    this.setState({
      winner1: loser1,
      winner2: loser2,
      loser1: winner1,
      loser2: winner2,
    })
  }

  renderContainer = () => {
    const {winner1, winner2, loser1, loser2, losersGoals} = this.state;
    if (!this.state.isGameRegistration) {
      return (
        <Container>
          <Block>
            <DropdownInput value={winner1} options={this.getFilteredPlayerList()}
                           onChange={this.onWinner1Change} placeholder='Winner 1' isClearable/>
            <DropdownInput value={winner2} options={this.getFilteredPlayerList()}
                           onChange={this.onWinner2Change} placeholder='Winner 2' isClearable/>
          </Block>
          <ScoreBlock>
            <div>10 :</div>
            <DropdownInput options={getGoalValues()} onChange={this.onLosersGoalsChange} placeholder=''/>
          </ScoreBlock>
          <Block>
            <DropdownInput value={loser1} options={this.getFilteredPlayerList()}
                           onChange={this.onLoser1Change} placeholder='Loser 1' isClearable/>
            <DropdownInput value={loser2} options={this.getFilteredPlayerList()}
                           onChange={this.onLoser2Change} placeholder='Loser 2' isClearable/>
          </Block>
        </Container>
      )
    }
    const {players} = this.props;
    const playersIds = {
      winner1Id: winner1.value,
      winner2Id: winner2.value,
      loser1Id: loser1.value,
      loser2Id: loser2.value,
    }
    const registeredPlayers = getPlayerInfo(players, playersIds);
    return (
      <RegisteredGameBlock losersGoals={losersGoals} winner1Icon={registeredPlayers.winner1Icon}
                           winner2Icon={registeredPlayers.winner2Icon}
                           loser1Icon={registeredPlayers.loser1Icon} loser2Icon={registeredPlayers.loser2Icon}
                           winner1Name={registeredPlayers.winner1Name}
                           winner2Name={registeredPlayers.winner2Name} loser1Name={registeredPlayers.loser1Name}
                           loser2Name={registeredPlayers.loser2Name}/>
    )
  }

  render() {
    const {registrationError, isGameRegistration} = this.state;

    return (
      <Content>
        <Popup buttonTitle='Register Game' ref={this.popupChild} clearValues={this.clearValues}
               loadData={this.setDefaultValues}>
          <InputsContainer>
            <PopupTitle>
              {isGameRegistration ? 'Are you sure?' : 'Register game'}
            </PopupTitle>
            {!isGameRegistration && <TeamReverse alt='reverse' src={reverseIcon} onClick={this.teamReverse} />}
            {this.renderContainer()}
            <RegistrationError>{registrationError}</RegistrationError>
            <ButtonsContainer>
              <Button onClick={this.onRegisterGame}>{!isGameRegistration ? 'OK' : 'Confirm'}</Button>
              <Indent/>
              <Button onClick={this.onCancelRegistration}>Cancel</Button>
            </ButtonsContainer>
          </InputsContainer>
        </Popup>
      </Content>
    )
  }
}

const mapStateToProps = (state) => { // eslint-disable-line no-unused-vars
  const props = {
    players: state.player.players,
    lastGame: state.game.lastGame
  };
  return props;
}
const mapDispatchToProps = (dispatch) => {
  const actions = {
    getAllPlayers, getActivePlayers, getLatestGames, getAllGames, getPlayersDashboard, getGamesCountPerWeek, getLastGame,
    getPlayer, getPlayerDeltaStatistic, getPlayerGames, getPlayerGamesCountStatistic, getRelations
  };
  const actionMap = {actions: bindActionCreators(actions, dispatch)};
  return actionMap;
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(GameRegistration));


const Content = styled.div``;

const PopupTitle = styled.div`
  font-size: 1.5em;
  margin-bottom: 40px;
`;

const TeamReverse = styled.img`
  width: 50px;
  height: 50px;
  cursor: pointer;
  margin-bottom: 20px;
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
  background-color: ${Colors.THEME_COLOR}
`;

const RegistrationError = styled.span`
  color: ${Colors.ERROR_COLOR};
  display: flex;
  align-items: center;
  margin: 20px 0;
`;

const ButtonsContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;
const Indent = styled.div`
  width: 20px;
`;