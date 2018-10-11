import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import UserPhoto from '../../components-ui/user-photo';

const Content = styled.div`
  display: flex;
	box-shadow: 0 2px 4px rgba(0,0,0,0.1);
	align-items: center;
	padding: 15px 20px;
	width: max-content;
	min-height: 80px;
	box-sizing: border-box;
`;

const Team = styled.div`
  display: flex;
`;

const User = styled(Link)`
  display: flex;
  img {
    max-width: 50px;
    min-width: 50px;
    max-height: 50px;
    min-height: 50px;
    margin-left: 10px;
    border-radius: 100%;
    &:first-child {
      padding-left: 0;
    }
  }
`;

const Score = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 40px;
  font-size: 1.3em;
`;

export const GameBlock = ({winner1Icon, winner1Id, winner2Icon, winner2Id, loser1Icon, loser1Id, loser2Icon, loser2Id, losersGoals}) => (
  <Content>
    <Team>
      <User to={`/player/${winner1Id}`}>
        <UserPhoto photo={winner1Icon}/>
      </User>
      <User to={`/player/${winner2Id}`}>
        <UserPhoto photo={winner2Icon}/>
      </User>
    </Team>
    <Score>10 : {losersGoals}</Score>
    <Team>
      <User to={`/player/${loser1Id}`}>
        <UserPhoto photo={loser1Icon}/>
      </User>
      <User to={`/player/${loser2Id}`}>
        <UserPhoto photo={loser2Icon}/>
      </User>
    </Team>
  </Content>
)
