import React from 'react';
import PropTypes from "prop-types";
import moment from 'moment';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import UserPhoto from '../../components-ui/user-photo';
import {Colors, MediaViews} from "../../helpers/style-variables";

export const GameBlock = ({
                            winner1Icon, winner1Id, winner2Icon, winner2Id, loser1Icon, loser1Id, loser2Icon, loser2Id, losersGoals,
                            winner1Name, winner2Name, loser1Name, loser2Name, won, delta, reportedBy, date, reportedById, isMobile
                          }) => (
  <Content>
    <GameContent>
      <Team>
        <LeftUser to={`/players/${winner1Id}`}>
          {
            !isMobile &&
            <Photo>
              <UserPhoto photo={winner1Icon}/>
            </Photo>
          }
          <UsernameLeft>{winner1Name}</UsernameLeft>
        </LeftUser>
        <LeftUser to={`/players/${winner2Id}`}>
          {
            !isMobile &&
            <Photo>
              <UserPhoto photo={winner2Icon}/>
            </Photo>
          }
          <UsernameLeft>{winner2Name}</UsernameLeft>
        </LeftUser>
      </Team>
      <Score>
        <span>10</span> : <span>{losersGoals}</span>
        {
          delta && <Delta won={won}>{won && '+'}{delta}</Delta>
        }
      </Score>
      <Team>
        <RightUser to={`/players/${loser1Id}`}>
          <UsernameRight>{loser1Name}</UsernameRight>
          {
            !isMobile &&
            <Photo>
              <UserPhoto photo={loser1Icon}/>
            </Photo>
          }
        </RightUser>
        <RightUser to={`/players/${loser2Id}`}>
          <UsernameRight>{loser2Name}</UsernameRight>
          {
            !isMobile &&
            <Photo>
              <UserPhoto photo={loser2Icon}/>
            </Photo>
          }
        </RightUser>
      </Team>
    </GameContent>
    {
      reportedBy &&
      <InfoContent>
        <div>{moment(date).format('ddd DD, MMM YYYY')}</div>
        <div>Reported by: <Link to={`/players/${reportedById}`}>{reportedBy}</Link></div>
      </InfoContent>
    }
  </Content>
)

GameBlock.propTypes = {
  winner1Icon: PropTypes.string,
  winner1Id: PropTypes.number.isRequired,
  winner2Icon: PropTypes.string,
  winner2Id: PropTypes.number.isRequired,
  loser1Icon: PropTypes.string,
  loser1Id: PropTypes.number.isRequired,
  loser2Icon: PropTypes.string,
  loser2Id: PropTypes.number.isRequired,
  losersGoals: PropTypes.number.isRequired,
  winner1Name: PropTypes.string.isRequired,
  winner2Name: PropTypes.string.isRequired,
  loser1Name: PropTypes.string.isRequired,
  loser2Name: PropTypes.string.isRequired,
  won: PropTypes.bool,
  delta: PropTypes.number,
  reportedBy: PropTypes.string.isRequired,
  date: PropTypes.number.isRequired,
  reportedById: PropTypes.number.isRequired,
  isMobile: PropTypes.bool.isRequired
}

const Content = styled.div`
  display: flex;
  margin-bottom: 20px;
  flex-direction: column;
  padding: 15px 20px;
  width: 100%;
  box-sizing: border-box;
  min-height: 100px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding: 10px 0;
  }
`;

const InfoContent = styled.div`
  margin-top: 20px;
  font-size: 0.9em;
  display: flex;
  justify-content: space-between;
  background-color: ${Colors.HOVER_COLOR};
  padding: 10px 20px;
  border-radius: 0 0 10px 10px;
  
  div {
    a {
      text-decoration: none;
      padding-left: 5px;
      color: ${Colors.MAIN_COLOR};
      &:hover {
        text-decoration: underline;
      }
    }
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding: 10px;
    font-size: 0.7em;
  }
`;

const GameContent = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
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
    background-color: ${Colors.HOVER_COLOR};
  }
`;

const LeftUser = styled(User)`
  justify-content: flex-start;
`;

const RightUser = styled(User)`
  justify-content: flex-end;
`;

const Username = styled.div`
  min-width: 150px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.8em;
    min-width: 0;
    width: 120px;
    text-overflow: ellipsis;
    overflow: hidden;
  }
`;

const UsernameLeft = styled(Username)`
  padding-left: 10px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding-left: 0;
  }
`;

const UsernameRight = styled(Username)`
  padding-right: 10px;
  text-align: right;
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding-right: 5px;
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
    font-size: 1em;
  }
`;

const Delta = styled.div`
  font-size: 0.8em;
  text-align: center;
  padding-top: 10px;
  color: ${({won}) => won ? 'green' : 'red'};
`;