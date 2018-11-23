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
        <PlaceContainer to={`/players/${players.firstPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.firstPlace.iconName}/>
          </PhotoBlock>
          <PlaceIcon src={firstPlaceIcon}/>
          <Name>{players.firstPlace.username}</Name>
        </PlaceContainer>
        <PlaceContainer to={`/players/${players.secondPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.secondPlace.iconName}/>
          </PhotoBlock>
          <PlaceIcon src={secondPlaceIcon}/>
          <Name>{players.secondPlace.username}</Name>
        </PlaceContainer>
        <PlaceContainer to={`/players/${players.thirdPlace.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.thirdPlace.iconName}/>
          </PhotoBlock>
          <PlaceIcon src={thirdPlaceIcon}/>
          <Name>{players.thirdPlace.username}</Name>
        </PlaceContainer>
      </PlacesContainer>
      <div>
        <PlaceContainer to={`/players/${players.loser.id}`}>
          <PhotoBlock>
            <UserPhoto photo={players.loser.iconName}/>
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
  position: relative;
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
  border-radius: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
`;

const PlaceIcon = styled.img`
  position: absolute;
  bottom: 30px;
  right: 5px;
  height: 60px;
`;
