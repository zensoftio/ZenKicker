import React, {Component} from 'react';
import styled from 'styled-components';
import UsernameBlock from '../username-block';
import ProfilePhotoBlock from '../profile-photo-block';

const Content = styled.div`
  display: flex;
  width: 100%;
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

class ProfileMainInfo extends Component {

  render() {
    const {rating, countGames, rated, isCurrent} = this.props;

    return (
      <Content>
        <ProfilePhotoBlock isCurrent={isCurrent}/>
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