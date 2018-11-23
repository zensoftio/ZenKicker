import React from 'react';
import styled from 'styled-components';
import UserPhoto from "../../components-ui/user-photo";
import {Link} from "react-router-dom";

const RelationBlock = ({relation}) => (
  <Content to={`/players/${relation.playerId}`}>
    <Photo>
      <UserPhoto photo={relation.iconName}/>
    </Photo>
    <Username>{relation.partnerName}</Username>
  </Content>
)

export default RelationBlock;

const Content = styled(Link)`
  display: flex;
  padding: 10px 0;
  align-items: center;
  color: #000;
  text-decoration: none;
  &:hover {
    background-color: #fafafa
  }
`;

const Photo = styled.div`
  max-width: 50px;
  min-width: 50px;
  max-height: 50px;
  min-height: 50px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Username = styled.div`
  margin-left: 10px;
  display: flex;
`;
