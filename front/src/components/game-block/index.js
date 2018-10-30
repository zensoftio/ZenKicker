import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import UserPhoto from '../../components-ui/user-photo';

export const GameBlock = ({winner1Icon, winner1Id, winner2Icon, winner2Id, loser1Icon, loser1Id, loser2Icon, loser2Id, losersGoals,
                            winner1Name, winner2Name, loser1Name, loser2Name, won, delta}) => (
  <Content>
    <Team>
      <User to={`/player/${winner1Id}`}>
        <Photo>
          <UserPhoto photo={winner1Icon}/>
        </Photo>
        <UsernameLeft>{winner1Name}</UsernameLeft>
      </User>
      <User to={`/player/${winner2Id}`}>
        <Photo>
          <UserPhoto photo={winner2Icon}/>
        </Photo>
        <UsernameLeft>{winner2Name}</UsernameLeft>
      </User>
    </Team>
    <Score>
      <span>10</span> : <span>{losersGoals}</span>
      {
        delta && <Delta won={won}>{won && '+'}{delta}</Delta>
      }
    </Score>
    <Team>
      <User to={`/player/${loser1Id}`}>
        <UsernameRight>{loser1Name}</UsernameRight>
        <Photo>
          <UserPhoto photo={loser1Icon}/>
        </Photo>
      </User>
      <User to={`/player/${loser2Id}`}>
        <UsernameRight>{loser2Name}</UsernameRight>
        <Photo>
          <UserPhoto photo={loser2Icon}/>
        </Photo>
      </User>
    </Team>
  </Content>
)

const Content = styled.div`
  display: flex;
	border-bottom: #efefef solid 1px;
	align-items: center;
	padding: 15px 20px;
	width: max-content;
	min-height: 100px;
`;

const Team = styled.div`
  display: flex;
  flex-direction: column;
`;

const User = styled(Link)`
  display: flex;
  align-items: center;
  color: #000;
  text-decoration: none;
  margin-bottom: 10px;
  &:last-child {
    margin-bottom: 0;
  }
  &:hover {
    background-color: #fafafa
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
  max-width: 50px;
  min-width: 50px;
  max-height: 50px;
  min-height: 50px;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border-radius: 100%;
  display: flex;
`;

const Score = styled.div`
  padding: 0 40px;
  font-size: 1.3em;
  color: red;
  span {
    &:first-child {
      color: green;
    }
  }
`;

const Delta = styled.div`
  font-size: 0.8em;
  text-align: center;
  padding-top: 10px;
  color: ${({won}) => won ? 'green' : 'red'};
`;