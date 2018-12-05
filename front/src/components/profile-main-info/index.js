import React, {Component} from 'react';
import styled from 'styled-components';
import UsernameBlock from '../username-block';
import ProfilePhotoBlock from '../profile-photo-block';
import PasswordBlock from '../password-block';
import {Colors, MediaViews} from '../../helpers/style-variables';
import {getPlayerStatus} from '../../helpers/get-player-status';

class ProfileMainInfo extends Component {

  constructor(props) {
    super(props);
    this.state = {
      uploadPhotoError: null
    }
  }

  setPhotoError = (message) => {
    this.setState({uploadPhotoError: message})
  }

  render() {
    const {
      rating, countGames, rated, isCurrent, countLosses, countWins, goalsAgainst, goalsFor, currentLossStreak,
      currentWinStreak, longestLossStreak, longestWinStreak, winningPercentage
    } = this.props;
    const {uploadPhotoError} = this.state;

    return (
      <Content>
        <div>
          <ProfilePhotoBlock isCurrent={isCurrent} setPhotoError={this.setPhotoError}/>
          <ErrorPhotoUpload>{uploadPhotoError}</ErrorPhotoUpload>
        </div>
        <Info>
          <EditingBlock>
            <UsernameBlock isCurrent={isCurrent}/>
            {
              isCurrent && <PasswordBlock/>
            }
          </EditingBlock>
          <Status>{getPlayerStatus(rating)}</Status>
          <StatisticsContent>
            <Statistics>
              <Field>Rating: <span>{rating}</span></Field>
              <Field>Games played: <span>{countGames}</span></Field>
              <Field>Games rated: <span>{rated}</span></Field>
              <Field>Games won: <span>({winningPercentage}%) {countWins}</span></Field>
              <Field>Games lost: <span>({100 - winningPercentage}%) {countLosses}</span></Field>
              <Field>Goals for: <span>{goalsFor}</span></Field>
              <Field>Goals against: <span>{goalsAgainst}</span></Field>
              <Field>Current loss streak: <span>{currentLossStreak}</span></Field>
              <Field>Longest loss streak: <span>{longestLossStreak}</span></Field>
              <Field>Current win streak: <span>{currentWinStreak}</span></Field>
              <Field>Longest win streak: <span>{longestWinStreak}</span></Field>
            </Statistics>
          </StatisticsContent>
        </Info>
      </Content>
    )
  }
}

export default ProfileMainInfo

const Content = styled.div`
  display: flex;
  width: 900px;
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
    flex-direction: column;
    align-items: center;
  }
`;

const Info = styled.div`
  width: 100%;
`;

const StatisticsContent = styled.div`
  padding: 20px 20px 0 20px;
  display: flex;
  justify-content: space-between;
  @media (max-width: ${MediaViews.MOBILE}px) {
    padding: 10px;
  }
`;
const Statistics = styled.div`
  width: 72%;
  @media (max-width: ${MediaViews.MOBILE}px) {
    width: 100%;
  }
`;
const Status = styled.div`
  width: 100%;
  color: ${Colors.MAIN_COLOR};
  font-size: 1.6em;
  display: flex;
  padding: 20px 20px 0 20px;
  
  @media (max-width: ${MediaViews.MOBILE}px) {
    box-sizing: border-box;
    justify-content: center;
  }
`;
const Field = styled.div`
  font-size: 1.3em;
  padding-bottom: 10px;
  display: flex;
  justify-content: space-between;
  &:last-child {
    padding-bottom: 0;
  }
  span {
    padding-left: 10px;
    color: ${Colors.MAIN_COLOR};
  }
  @media (max-width: ${MediaViews.MOBILE}px) {
    font-size: 1.1em;
  }
`;

const ErrorPhotoUpload = styled.div`
  color: ${Colors.ERROR_COLOR};
  max-width: 200px;
  margin-top: 10px;
`;

const EditingBlock = styled.div`
  display: flex;
  justify-content: space-between;
  @media (max-width: ${MediaViews.MOBILE}px) {
    flex-direction: column;
    align-items: center;
}
`;
