import React from 'react';
import styled from "styled-components";
import UserPhoto from "../../components-ui/user-photo";
import {Link} from "react-router-dom";

import firstPlaceIcon from '../../shared/images/first-place.png';
import secondPlaceIcon from '../../shared/images/second-place.png';
import thirdPlaceIcon from '../../shared/images/third-place.png';
import crabIcon from '../../shared/images/crab.png';
import {Colors, MediaViews} from "../../helpers/style-variables";

const PlayersOfWeek = ({players}) => {
  if (!players || !players.firstPlace) return null;

  return (
    <Content>
      <PlacesContainer>
        <PlaceContainer to={`/players/${players.firstPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.firstPlace.iconPath}/>
          </PhotoBlock>
          <PlaceIcon src={firstPlaceIcon}/>
          <Name>{players.firstPlace.username}</Name>
        </PlaceContainer>
        <PlaceContainer to={`/players/${players.secondPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.secondPlace.iconPath}/>
          </PhotoBlock>
          <PlaceIcon src={secondPlaceIcon}/>
          <Name>{players.secondPlace.username}</Name>
        </PlaceContainer>
        <PlaceContainer to={`/players/${players.thirdPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.thirdPlace.iconPath}/>
          </PhotoBlock>
          <PlaceIcon src={thirdPlaceIcon}/>
          <Name>{players.thirdPlace.username}</Name>
        </PlaceContainer>
      </PlacesContainer>
      <div>
        <PlaceContainer to={`/players/${players.loser.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.loser.iconPath}/>
          </PhotoBlock>
          <PlaceIcon src={crabIcon}/>
          <Name>{players.loser.username}</Name>
        </PlaceContainer>
      </div>
    </Content>
  )
}

export default PlayersOfWeek;

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

const Name = styled.div`
  display: flex;
  font-size: 1.2em;
  margin-top: 10px;
  max-width: 100px;
  word-break: break-word;
  text-align: center;
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.9em;
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

const PlaceIcon = styled.img`
  position: absolute;
  top: 80px;
  right: 10px;
  height: 60px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    height: 35px;
    top: 50px;
  }
`;
