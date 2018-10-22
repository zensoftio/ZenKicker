import React, {Component} from 'react';
import styled from 'styled-components';
import UsernameBlock from '../username-block';
import ProfilePhotoBlock from '../profile-photo-block';
import PasswordBlock from '../password-block';

const Content = styled.div`
  display: flex;
  width: 100%;
  position: relative;
`;

const Info = styled.div`
  width: 100%;
`;

const StatisticsContent = styled.div`
  padding: 20px 0 0 20px;
`;

const Statistics = styled.div`
  font-size: 1.3em;
  padding-bottom: 10px;
  
  &:last-child {
    padding-bottom: 0;
  }

  span {
    font-weight: 100;
  }
`;

const ErrorPhotoUpload = styled.div`
  position: absolute;
  color: #C74242;
  bottom: 60px;
`;

const ChangePasswordContainer = styled.div`
  margin-top: 70px;
`;

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
    const {rating, countGames, rated, isCurrent} = this.props;
    const {uploadPhotoError} = this.state;

    return (
      <Content>
        <div>
          <ProfilePhotoBlock isCurrent={isCurrent} setPhotoError={this.setPhotoError}/>
          <ChangePasswordContainer>
            {
              isCurrent && <PasswordBlock />
            }
          </ChangePasswordContainer>
          <ErrorPhotoUpload>{uploadPhotoError}</ErrorPhotoUpload>
        </div>

        <Info>
          <UsernameBlock isCurrent={isCurrent}/>
          <StatisticsContent>
            <Statistics>Rating: <span>{rating}</span></Statistics>
            <Statistics>Games played: <span>{countGames}</span></Statistics>
            <Statistics>Games rated: <span>{rated}</span></Statistics>
          </StatisticsContent>
        </Info>
      </Content>
    )
  }
}

export default ProfileMainInfo