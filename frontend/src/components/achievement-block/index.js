import React from 'react';
import PropTypes from "prop-types";
import styled from "styled-components";
import {AchievementModel} from "../../common/global-prop-types";
import {Colors} from "../../helpers/style-variables";

const AchievementBlock = ({achievement}) => (

  <Content>
    <Icon src={achievement.icon}/>
    <Count>{achievement.list.length}</Count>
  </Content>

);

export default AchievementBlock;

AchievementBlock.propTypes = {
  achievement: PropTypes.shape({
    type: PropTypes.string.isRequired,
    icon: PropTypes.string.isRequired,
    list: PropTypes.arrayOf(AchievementModel).isRequired,
  })
};

const Content = styled.div`
  width: max-content;
  position: relative;
  cursor: pointer;
`;

const Count = styled.div`
  position: absolute;
  top: -4px;
  right: 0;
  background-color: ${Colors.MAIN_COLOR};
  border-radius: 100%;
  width: 20px;
  height: 20px;
  color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 0.6em;
  font-weight: bold;
`;

const Icon = styled.img`
  display: flex;
  height: 35px;
  margin: 5px;
`;
