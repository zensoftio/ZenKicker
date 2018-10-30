import React, {Component} from 'react';
import styled from 'styled-components';
import UsernameBlock from '../username-block';
import ProfilePhotoBlock from '../profile-photo-block';
import PasswordBlock from '../password-block';
import {Colors} from '../../helpers/style-variables';

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
    const {rating, countGames, rated, isCurrent, countLosses, countWins, goalsAgainst, goalsFor} = this.props;
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
              isCurrent && <PasswordBlock />
            }
          </EditingBlock>
          <StatisticsContent>
            <Statistics>Rating: <span>{rating}</span></Statistics>
            <Statistics>Games played: <span>{countGames}</span></Statistics>
            <Statistics>Games rated: <span>{rated}</span></Statistics>
            <Statistics>Games won: <span>{countWins}</span></Statistics>
            <Statistics>Games lost: <span>{countLosses}</span></Statistics>
            <Statistics>Goals for: <span>{goalsFor}</span></Statistics>
            <Statistics>Goals against: <span>{goalsAgainst}</span></Statistics>
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
`;

const Info = styled.div`
  width: 100%;
`;

const StatisticsContent = styled.div`
  padding: 40px 20px 0 20px;
`;

const Statistics = styled.div`
  font-size: 1.3em;
  padding-bottom: 10px;
  &:last-child {
    padding-bottom: 0;
  }
  span {
    padding-left: 10px;
    color: ${Colors.MAIN_COLOR}
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
`;
