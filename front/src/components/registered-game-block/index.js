import React from 'react';
import styled from 'styled-components';
import UserPhoto from '../../components-ui/user-photo';

export const RegisteredGameBlock = ({
                            winner1Icon, winner2Icon, loser1Icon, loser2Icon, losersGoals,
                            winner1Name, winner2Name, loser1Name, loser2Name
                          }) => (
  <Content>
    <GameContent>
      <Team>
        <User>
          <Photo>
            <UserPhoto photo={winner1Icon}/>
          </Photo>
          <UsernameLeft>{winner1Name}</UsernameLeft>
        </User>
        <User>
          <Photo>
            <UserPhoto photo={winner2Icon}/>
          </Photo>
          <UsernameLeft>{winner2Name}</UsernameLeft>
        </User>
      </Team>
      <Score>
        <span>10</span> : <span>{losersGoals}</span>
      </Score>
      <Team>
        <User>
          <UsernameRight>{loser1Name}</UsernameRight>
          <Photo>
            <UserPhoto photo={loser1Icon}/>
          </Photo>
        </User>
        <User>
          <UsernameRight>{loser2Name}</UsernameRight>
          <Photo>
            <UserPhoto photo={loser2Icon}/>
          </Photo>
        </User>
      </Team>
    </GameContent>
  </Content>
)

const Content = styled.div`
  display: flex;
  flex-direction: column;
  padding: 15px 20px;
  width: max-content;
  min-height: 100px;
`;


const GameContent = styled.div`
  display: flex;
  align-items: center;
`;

const Team = styled.div`
  display: flex;
  flex-direction: column;
`;

const User = styled.div`
  display: flex;
  font-size: 1.3em;
  align-items: center;
  margin-bottom: 10px;
  &:last-child {
    margin-bottom: 0;
  }
`;

const UsernameLeft = styled.div`
  padding-left: 10px;
  min-width: 150px;
`;

const UsernameRight = styled.div`
  padding-right: 10px;
  min-width: 150px;
  text-align: right;
`;

const Photo = styled.div`
  max-width: 70px;
  min-width: 70px;
  max-height: 70px;
  min-height: 70px;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border-radius: 100%;
  display: flex;
`;

const Score = styled.div`
  padding: 0 40px;
  font-size: 3em;
  }
`;
