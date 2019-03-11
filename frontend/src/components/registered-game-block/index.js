import React from 'react';
import PropTypes from "prop-types";
import styled from 'styled-components';
import UserPhoto from '../../components-ui/user-photo';
import {MediaViews} from "../../helpers/style-variables";

export const RegisteredGameBlock = ({
                            winner1Icon, winner2Icon, loser1Icon, loser2Icon, losersGoals,
                            winner1Name, winner2Name, loser1Name, loser2Name, isMobile
                          }) => (
  <Content>
    <GameContent>
      <LeftTeam>
        <User>
          {
            !isMobile &&
            <Photo>
              <UserPhoto photo={winner1Icon}/>
            </Photo>
          }
          <UsernameLeft>{winner1Name}</UsernameLeft>
        </User>
        <User>
          {
            !isMobile &&
            <Photo>
              <UserPhoto photo={winner2Icon}/>
            </Photo>
          }
          <UsernameLeft>{winner2Name}</UsernameLeft>
        </User>
      </LeftTeam>
      <Score>
        <span>10</span> : <span>{losersGoals}</span>
      </Score>
      <RightTeam>
        <User>
          <UsernameRight>{loser1Name}</UsernameRight>
          {
            !isMobile &&
            <Photo>
              <UserPhoto photo={loser1Icon}/>
            </Photo>
          }
        </User>
        <User>
          <UsernameRight>{loser2Name}</UsernameRight>
          {
            !isMobile &&
            <Photo>
              <UserPhoto photo={loser2Icon}/>
            </Photo>
          }
        </User>
      </RightTeam>
    </GameContent>
  </Content>
)

RegisteredGameBlock.propTypes = {
  winner1Icon: PropTypes.string,
  winner2Icon: PropTypes.string,
  loser1Icon: PropTypes.string,
  loser2Icon: PropTypes.string,
  losersGoals: PropTypes.number.isRequired,
  winner1Name: PropTypes.string.isRequired,
  winner2Name: PropTypes.string.isRequired,
  loser1Name: PropTypes.string.isRequired,
  loser2Name: PropTypes.string.isRequired,
  isMobile: PropTypes.bool.isRequired
}

const Content = styled.div`
  display: flex;
  flex-direction: column;
  padding: 15px 20px;
  width: max-content;
  min-height: 100px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
    max-height: calc(100vh - 50px);
  }
`;


const GameContent = styled.div`
  display: flex;
  align-items: center;
  @media (max-width: ${MediaViews.MOBILE}px) {
    flex-direction: column;
  }
`;

const Team = styled.div`
  display: flex;
  flex-direction: column;
  @media (max-width: ${MediaViews.MOBILE}px) {
    align-items: center;
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
      margin-top: 20px;
    }
  }
`;

const LeftTeam = styled(Team)`
  align-items: flex-start;
`;

const RightTeam = styled(Team)`
  align-items: flex-end;
`;

const User = styled.div`
  display: flex;
  font-size: 1.3em;
  align-items: center;
  margin-bottom: 10px;
  &:last-child {
    margin-bottom: 0;
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1.5em;
  }
`;

const UsernameLeft = styled.div`
  padding-left: 10px;
  min-width: 150px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding-left: 0;
    text-align: center;
  }
`;

const UsernameRight = styled.div`
  padding-right: 10px;
  min-width: 150px;
  text-align: right;
  @media (max-width: ${MediaViews.MOBILE}px) {
  padding-left: 0;
    text-align: center;
  }
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
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 2em;
  }
`;
