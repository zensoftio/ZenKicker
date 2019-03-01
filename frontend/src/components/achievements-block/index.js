import React from 'react';
import styled from "styled-components";
import AchievementBlock from "../achievement-block";
import {mappedAchievementsList} from "../../helpers/achievements-list";

const AchievementsBlock = ({achievements}) => (

  <Content>
    {mappedAchievementsList(achievements).map(i => <AchievementBlock key={i.type} achievement={i}/>)}
  </Content>

);

export default AchievementsBlock;

const Content = styled.div`
  width: 100%;
  display: flex;
  margin-top: 10px;
  justify-content: center;
`;