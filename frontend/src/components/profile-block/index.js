import React from 'react';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import UserPhoto from '../../components-ui/user-photo';
import {Colors, MediaViews} from "../../helpers/style-variables";
import PropTypes from "prop-types";

const renderStatisticColumns = ({countGames, rated, rating, isMobile, longestWinningStreak, longestLossesStreak,
                                  winningPercentage, renderColumns}) => {
  if (!isMobile) return (
    <React.Fragment>
      <Statistics>{longestWinningStreak}</Statistics>
      <Statistics>{longestLossesStreak}</Statistics>
      <Statistics>{winningPercentage}</Statistics>
      <Statistics>{countGames}</Statistics>
      <Statistics>{rated}</Statistics>
      <Statistics>{rating}</Statistics>
    </React.Fragment>
  )
  if (renderColumns === 'firstPart') return (
    <React.Fragment>
      <Statistics>{longestWinningStreak}</Statistics>
      <Statistics>{longestLossesStreak}</Statistics>
      <Statistics>{winningPercentage}</Statistics>
    </React.Fragment>
  )
  if (renderColumns === 'secondPart') return (
    <React.Fragment>
      <Statistics>{countGames}</Statistics>
      <Statistics>{rated}</Statistics>
      <Statistics>{rating}</Statistics>
    </React.Fragment>
  )
}

export const ProfileBlock = ({index, id, fullName, countGames, rated, rating, iconPath, isMobile, longestWinningStreak,
                               longestLossesStreak, winningPercentage, renderColumns}) => (
  <Content to={`/players/${id}`}>
    <Index>{index}</Index>
    <User>
      {
        !isMobile &&
        <Photo>
          <UserPhoto photo={iconPath}/>
        </Photo>
      }
      <Username>{fullName}</Username>
    </User>


    {
      renderStatisticColumns({countGames, rated, rating, isMobile, longestWinningStreak, longestLossesStreak, winningPercentage, renderColumns})
    }
  </Content>
)

ProfileBlock.propTypes = {
  index: PropTypes.number.isRequired,
  id: PropTypes.number.isRequired,
  fullName: PropTypes.string.isRequired,
  countGames: PropTypes.number.isRequired,
  rated: PropTypes.number.isRequired,
  rating: PropTypes.number.isRequired,
  iconPath: PropTypes.string,
  isMobile: PropTypes.bool.isRequired,
  longestWinningStreak: PropTypes.number.isRequired,
  longestLossesStreak: PropTypes.number.isRequired,
  winningPercentage: PropTypes.number.isRequired,
  renderColumns: PropTypes.string.isRequired,
}

const Content = styled(Link)`
  display: flex;
	border-bottom: #efefef solid 1px;
	color: black;
	text-decoration: none;
	align-items: center;
	padding: 15px 20px;
	min-height: 80px;
	box-sizing: border-box;
	span {
	  margin-right: 30px;
	}
  &:hover {
    background-color: ${Colors.HOVER_COLOR};
  }
  
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 0.8em;
    padding: 10px;
  }
`;

const Template = styled.div`
  padding-left: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  box-sizing: border-box;
`;

const Index = styled.div`
  max-width: 50px;
  min-width: 50px;
  overflow: hidden;
  text-overflow: ellipsis;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 10%;
    min-width: 10%;
  }
`;

const User = styled.div`
  max-width: 220px;
  min-width: 220px;
  overflow: hidden;
  display: flex;
  align-items: center;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 25%;
    min-width: 25%;
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
  border-radius: 100%;
`;

const Username = styled(Template)`
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding-left: 0;
  }
`;

const Statistics = styled(Template)`
  max-width: 98px;
  min-width: 98px;
  text-align: right;
  @media (max-width: ${MediaViews.MOBILE}px) {
    max-width: 22%;
    min-width: 22%;
  }
`;

