import React from 'react';
import moment from 'moment';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import UserPhoto from '../../components-ui/user-photo';
import {Colors, MediaViews} from "../../helpers/style-variables";

export const GameBlock = ({
                            winner1Icon, winner1Id, winner2Icon, winner2Id, loser1Icon, loser1Id, loser2Icon, loser2Id, losersGoals,
                            winner1Name, winner2Name, loser1Name, loser2Name, won, delta, reportedBy, date, reportedById
                          }) => (
  <Content>
    <GameContent>
      <Team>
        <User to={`/players/${winner1Id}`}>
          <Photo>
            <UserPhoto photo={winner1Icon}/>
          </Photo>
          <UsernameLeft>{winner1Name}</UsernameLeft>
        </User>
        <User to={`/players/${winner2Id}`}>
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
        <User to={`/players/${loser1Id}`}>
          <UsernameRight>{loser1Name}</UsernameRight>
          <Photo>
            <UserPhoto photo={loser1Icon}/>
          </Photo>
        </User>
        <User to={`/players/${loser2Id}`}>
          <UsernameRight>{loser2Name}</UsernameRight>
          <Photo>
            <UserPhoto photo={loser2Icon}/>
          </Photo>
        </User>
      </Team>
    </GameContent>
    {
      reportedBy &&
      <InfoContent>
        <div>Reported by: <Link to={`/players/${reportedById}`}>{reportedBy}</Link></div>
        <div><span>{moment(date).format('HH:MM, ddd DD, MMM YYYY')}</span></div>
      </InfoContent>
    }
  </Content>
)

const Content = styled.div`
  display: flex;
  border-bottom: #efefef solid 1px;
  flex-direction: column;
  padding: 15px 20px;
  width: max-content;
  min-height: 100px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
    padding: 10px 0;
    box-sizing: border-box;
  }
`;

const InfoContent = styled.div`
  padding-top: 15px;
  font-size: 1.1em;
  display: flex;
  justify-content: space-between;
  div {
    a {
      text-decoration: none;
      padding-left: 5px;
      color: ${Colors.MAIN_COLOR};
      &:hover {
        text-decoration: underline;
      }
    }
    span {
      color: ${Colors.MAIN_COLOR};
    }
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.9em;
  }
`;

const GameContent = styled.div`
  display: flex;
  align-items: center;
  @media (max-width: ${MediaViews.MOBILE}px) {
    justify-content: space-between;
  }
`;

const Team = styled.div`
  display: flex;
  flex-direction: column;
  @media (max-width: ${MediaViews.MOBILE}px) {
  }
  
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
    background-color: ${Colors.HOVER_COLOR};
  }
`;

const UsernameLeft = styled.div`
  padding-left: 10px;
  min-width: 150px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    min-width: 0;
    width: 55px;
    text-overflow: ellipsis;
    overflow: hidden;
  }
`;

const UsernameRight = styled.div`
  padding-right: 10px;
  min-width: 150px;
  text-align: right;
  @media (max-width: ${MediaViews.MOBILE}px) {
    min-width: 0;
    width: 55px;
    text-overflow: ellipsis;
    overflow: hidden;
  }
`;

const Photo = styled.div`
  max-width: 50px;
  min-width: 50px;
  max-height: 50px;
  min-height: 50px;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  display: flex;
  border-radius: 100%;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 30px;
    min-width: 30px;
    max-height: 30px;
    min-height: 30px;
  }
`;

const Score = styled.div`
  padding: 0 40px;
  font-size: 1.3em;
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding: 0 10px;
    font-size: 1.1em;
  }
`;

const Delta = styled.div`
  font-size: 0.8em;
  text-align: center;
  padding-top: 10px;
  color: ${({won}) => won ? 'green' : 'red'};
`;