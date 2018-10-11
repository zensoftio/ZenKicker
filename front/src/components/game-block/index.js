import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';

const defaultPhoto = 'https://www.hgvrecruitmentcentre.co.uk/wp-content/uploads/2018/04/1_MccriYX-ciBniUzRKAUsAw.png';

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
        {
          winner1Icon ?
            <img alt="avatar" src={`http://localhost/images/icons/${winner1Icon}`}/> :
            <img alt="avatar" src={defaultPhoto} />
        }
      </User>
      <User to={`/player/${winner2Id}`}>
        {
          winner2Icon ?
            <img alt="avatar" src={`http://localhost/images/icons/${winner2Icon}`}/> :
            <img alt="avatar" src={defaultPhoto} />
        }
      </User>
    </Team>
    <Score>10 : {losersGoals}</Score>
    <Team>
      <User to={`/player/${loser1Id}`}>
        {
          loser1Icon ?
            <img alt="avatar" src={`http://localhost/images/icons/${loser1Icon}`}/> :
            <img alt="avatar" src={defaultPhoto} />
        }
      </User>
      <User to={`/player/${loser2Id}`}>
        {
          loser2Icon ?
            <img alt="avatar" src={`http://localhost/images/icons/${loser2Icon}`}/> :
            <img alt="avatar" src={defaultPhoto} />
        }
      </User>
    </Team>
  </Content>
)
