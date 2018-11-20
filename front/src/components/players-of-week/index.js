import React from 'react';
import styled from "styled-components";
import UserPhoto from "../../components-ui/user-photo";
import {Link} from "react-router-dom";

import firstPlaceIcon from '../../shared/images/first-place.png';
import secondPlaceIcon from '../../shared/images/second-place.png';
import thirdPlaceIcon from '../../shared/images/third-place.png';
import crabIcon from '../../shared/images/crab.png';

const PlayersOfWeek = ({players, loser}) => {
  if (!players.totalCount || players.totalCount < 4 || !loser) return null;
  const firstPlace = players.list[0];
  const secondPlace = players.list[1];
  const thirdPlace = players.list[2];

  return (
    <Content>
      <PlacesContainer>
        <SecondPlace to={`/player/${secondPlace.id}`}>
          <SecondPlacePhoto>
            <UserPhoto photo={secondPlace.iconName}/>
            <PlaceIcon src={secondPlaceIcon}/>
          </SecondPlacePhoto>
          <Name>{secondPlace.username}</Name>
        </SecondPlace>
        <FirstPlace to={`/player/${firstPlace.id}`}>
          <FirstPlacePhoto>
            <UserPhoto photo={firstPlace.iconName}/>
            <PlaceIcon src={firstPlaceIcon}/>
          </FirstPlacePhoto>
          <Name>{firstPlace.username}</Name>
        </FirstPlace>
        <ThirdPlace to={`/player/${thirdPlace.id}`}>
          <ThirdPlacePhoto>
            <UserPhoto photo={thirdPlace.iconName}/>
            <PlaceIcon src={thirdPlaceIcon}/>
          </ThirdPlacePhoto>
          <Name>{thirdPlace.username}</Name>
        </ThirdPlace>
      </PlacesContainer>
      <div>
        <FirstPlace to={`/player/${loser.id}`}>
          <FirstPlacePhoto>
            <UserPhoto photo={loser.iconName}/>
            <PlaceIcon src={crabIcon}/>
          </FirstPlacePhoto>
          <Name>{loser.username}</Name>
        </FirstPlace>
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
  align-items: flex-end;
`;

const Name = styled.div`
  display: flex;
  font-size: 1.2em;
  margin-top: 10px;
`;

const SecondPlace = styled(Link)`
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #000;
  text-decoration: none;
  &:hover {
    text-decoration: underline;
  }
`;

const SecondPlacePhoto = styled.div`
  width: 120px;
  height: 120px;
  position: relative;
`;

const FirstPlace = styled(Link)`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 20px;
  color: #000;
  text-decoration: none;
  &:hover {
    text-decoration: underline;
  }
`;

const FirstPlacePhoto = styled.div`
  width: 150px;
  height: 150px;
  position: relative;
`;

const ThirdPlace = styled(Link)`
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #000;
  text-decoration: none;
  &:hover {
    text-decoration: underline;
  }
`;

const ThirdPlacePhoto = styled.div`
  width: 90px;
  height: 90px;
  position: relative;
`;

const PlaceIcon = styled.img`
  position: absolute;
  bottom: -15px;
  right: 0;
  height: 60px;
`;
