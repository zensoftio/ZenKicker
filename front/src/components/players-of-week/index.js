import React from 'react';
import styled from "styled-components";
import UserPhoto from "../../components-ui/user-photo";
import {Link} from "react-router-dom";

import firstPlaceIcon from '../../shared/images/first-place.png';
import secondPlaceIcon from '../../shared/images/second-place.png';
import thirdPlaceIcon from '../../shared/images/third-place.png';
import crabIcon from '../../shared/images/crab.png';

const PlayersOfWeek = ({players}) => {
  if (!players || !players.firstPlace) return null;

  return (
    <Content>
      <PlacesContainer>
        <PlaceContainer to={`/player/${players.firstPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.firstPlace.iconName}/>
            <PlaceIcon src={firstPlaceIcon}/>
          </PhotoBlock>
          <Name>{players.firstPlace.username}</Name>
        </PlaceContainer>
        <PlaceContainer to={`/player/${players.secondPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.secondPlace.iconName}/>
            <PlaceIcon src={secondPlaceIcon}/>
          </PhotoBlock>
          <Name>{players.secondPlace.username}</Name>
        </PlaceContainer>
        <PlaceContainer to={`/player/${players.thirdPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.thirdPlace.iconName}/>
            <PlaceIcon src={thirdPlaceIcon}/>
          </PhotoBlock>
          <Name>{players.thirdPlace.username}</Name>
        </PlaceContainer>
      </PlacesContainer>
      <div>
        <PlaceContainer to={`/player/${players.loser.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.loser.iconName}/>
            <PlaceIcon src={crabIcon}/>
          </PhotoBlock>
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
`;

const PlaceContainer = styled(Link)`
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #000;
  text-decoration: none;
  padding: 10px;
  &:hover {
    background-color: #fafafa
  }
`;

const PhotoBlock = styled.div`
  width: 120px;
  height: 120px;
  position: relative;
`;

const PlaceIcon = styled.img`
  position: absolute;
  bottom: -15px;
  right: 0;
  height: 60px;
`;
