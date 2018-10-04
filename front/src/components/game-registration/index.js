import React, {Component} from 'react';
import styled from 'styled-components';
import {Button} from '../../components-ui/buttons/button';
import {Input} from '../../components-ui/input';
import Popup from '../popup';

const Content = styled.div`
  position: absolute;
  right: 20px;
  top: 10px;
`;

const PopupTitle = styled.div`
  font-size: 1.5em;
  margin-bottom: 40px;
`;

const InputsContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: max-content;
  height: max-content;
  background-color: #fff;
  padding: 40px 80px;
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
      winner1: '',
      winner2: '',
      loser1: '',
      loser2: '',
      score: '',
      registrationError: null,
    }
  }

  clearValues = () => this.setState({
    winner1: '',
    winner2: '',
    loser1: '',
    loser2: '',
    score: '',
    registrationError: null,
  })

  onWinner1Change = (value) => this.setState({winner1: value, registrationError: null})
  onWinner2Change = (value) => this.setState({winner2: value, registrationError: null})
  onLoser1Change = (value) => this.setState({loser1: value, registrationError: null})
  onLoser2Change = (value) => this.setState({loser2: value, registrationError: null})
  onScoreChange = (value) => this.setState({score: value, registrationError: null})

  onChangePasswordClick = async () => {
    const {winner1, winner2, loser1, loser2, score} = this.state;
    try {
      const data = {
        winner1Id: winner1,
        winner2Id: winner2,
        loser1Id: loser1,
        loser2Id: loser2,
        losersGoals: score
      };
      this.clearValues();
      this.child.onPopupClose()
    } catch (err) {
      const error = err.response.data.message;
      this.setState({registrationError: error});
    }
  }

  render() {
    const {winner1, winner2, loser1, loser2, score, registrationError} = this.state;

    return (
      <Content>
        <Popup buttonTitle='Register Game' ref={instance => {this.child = instance}}>
          <InputsContainer>
            <PopupTitle>Register game</PopupTitle>
            <div>
              <Input value={winner1} onChange={(e) => this.onWinner1Change(e.target.value)}
                     placeholder='Winner 1'/>
              <Input value={winner2} onChange={(e) => this.onWinner2Change(e.target.value)}
                     placeholder='Winner 2'/>
            </div>
            <div>
              <Input value={loser1} onChange={(e) => this.onLoser1Change(e.target.value)}
                     placeholder='Loser 1'/>
              <Input value={loser2} onChange={(e) => this.onLoser2Change(e.target.value)}
                     placeholder='Loser 2'/>
            </div>
            <RegistrationError>{registrationError}</RegistrationError>
            <Button onClick={this.onChangePasswordClick}>Confirm</Button>
          </InputsContainer>
        </Popup>
      </Content>
    )
  }
}

export default GameRegistration