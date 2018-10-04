import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import {Input} from '../../components-ui/input';
import Popup from '../popup';
import DropdownInput from '../../components-ui/dropdown-input';
import {registerGame} from '../../actions';

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
  color: red;
  display: flex;
  align-items: center;
  margin: 20px 0;
`;

class GameRegistration extends Component {
  constructor(props) {
    super(props);
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

  onChangePasswordClick = async () => {
    const {winner1Id, winner2Id, loser1Id, loser2Id, losersGoals} = this.state;
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
      this.child.onPopupClose()
    } catch (err) {
      const error = err.response.data.errors[0].message;
      this.setState({registrationError: error});
    }
  }

  render() {
    const {registrationError} = this.state;
    const {players} = this.props;

    const playersList = players && players.map(i => ({value: i.id, label: i.username}));
    const goals = [
      {value: 0, label: 0},
      {value: 1, label: 1},
      {value: 2, label: 2},
      {value: 3, label: 3},
      {value: 4, label: 4},
      {value: 5, label: 5},
      {value: 6, label: 6},
      {value: 7, label: 7},
      {value: 8, label: 8},
      {value: 9, label: 9},
    ]

    return (
      <Content>
        <Popup buttonTitle='Register Game' ref={instance => {this.child = instance}}>
          <InputsContainer>
            <PopupTitle>Register game</PopupTitle>
            <Container>
              <Block>
                <DropdownInput data={playersList} onChange={this.onWinner1Change} placeholder='Winner 1'/>
                <DropdownInput data={playersList} onChange={this.onWinner2Change} placeholder='Winner 2'/>
              </Block>
              <ScoreBlock>
                <div>10 :</div>
                <DropdownInput data={goals} onChange={this.onLosersGoalsChange} placeholder=''/>
              </ScoreBlock>
              <Block>
                <DropdownInput data={playersList} onChange={this.onLoser1Change} placeholder='Loser 1'/>
                <DropdownInput data={playersList} onChange={this.onLoser2Change} placeholder='Loser 2'/>
              </Block>
            </Container>

            <RegistrationError>{registrationError}</RegistrationError>
            <Button onClick={this.onChangePasswordClick}>Confirm</Button>
          </InputsContainer>
        </Popup>
      </Content>
    )
  }
}

export default GameRegistration