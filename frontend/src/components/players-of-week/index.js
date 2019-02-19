import React from 'react';
import styled from "styled-components";
import UserPhoto from "../../components-ui/user-photo";
import {Link} from "react-router-dom";

import {Colors, MediaViews} from "../../helpers/style-variables";
import {PlayerDashboardModel} from "../../common/global-prop-types";

const PlayersOfWeek = ({players}) => {
  if (!players || !players.firstPlace) return null;

  return (
    <Content>
      <PlacesContainer>
        <PlaceContainer to={`/players/${players.firstPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.firstPlace.iconPath}/>
          </PhotoBlock>
          <Place><span>1</span>st</Place>
          <Name>{players.firstPlace.fullName}</Name>
        </PlaceContainer>
        <PlaceContainer to={`/players/${players.secondPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.secondPlace.iconPath}/>
          </PhotoBlock>
          <Place><span>2</span>nd</Place>
          <Name>{players.secondPlace.fullName}</Name>
        </PlaceContainer>
        <PlaceContainer to={`/players/${players.thirdPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.thirdPlace.iconPath}/>
          </PhotoBlock>
          <Place><span>3</span>rd</Place>
          <Name>{players.thirdPlace.fullName}</Name>
        </PlaceContainer>
      </PlacesContainer>
      <div>
        <PlaceContainer to={`/players/${players.loser.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.loser.iconPath}/>
          </PhotoBlock>
          <LastPlace>LOSER</LastPlace>
          <Name>{players.loser.fullName}</Name>
        </PlaceContainer>
      </div>
    </Content>
  )
}

export default PlayersOfWeek;

PlayersOfWeek.propTypes = {
  players: PlayerDashboardModel,
}

const Content = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  @media (max-width: ${MediaViews.MOBILE}px) {
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
`;

const PlacesContainer = styled.div`
  display: flex;
  align-items: flex-start;
`;

const Place = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  position: absolute;
  top: 90px;
  right: 0;
  font-weight: bold;
  background-color: ${Colors.THEME_COLOR};
  border-radius: 100%;
  span {
    font-size: 1.8em;
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.7em;
    width: 30px;
    height: 30px;
    top: 50px;
    right: 20px;
    span {
      font-size: 1.5em;
    }
  }
`;

const LastPlace = styled(Place)`
  font-size: 1.1em;
  justify-content: center;
  width: 70px;
  height: 70px;
  position: absolute;
  top: 70px;
  right: -10px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.6em;
    width: 40px;
    height: 40px;
    top: 45px;
    right: 10px;
  }
`;

const Name = styled.div`
  display: flex;
  font-size: 1em;
  margin-top: 10px;
  max-width: 100px;
  width: 100px;
  text-align: center;
  overflow: hidden;
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.8em;
  }
`;

const PlaceContainer = styled(Link)`
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
  color: #000;
  text-decoration: none;
  padding: 10px;
  &:hover {
    background-color: ${Colors.HOVER_COLOR};
  }
`;

const PhotoBlock = styled.div`
  width: 120px;
  height: 120px;
  border-radius: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 60px;
    height: 60px;
  }
`;
